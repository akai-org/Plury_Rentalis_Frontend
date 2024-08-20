package org.akai.pluryrenatlisapp.navhost

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.akai.pluryrenatlisapp.apiclient.Authorization
import org.akai.pluryrenatlisapp.apiclient.AuthorizationViewModel
import org.akai.pluryrenatlisapp.data.User
import org.akai.pluryrenatlisapp.ui.compositions.HomeComposition
import org.akai.pluryrenatlisapp.ui.compositions.LoginComposition
import org.akai.pluryrenatlisapp.ui.compositions.RegisterComposition
import org.akai.pluryrenatlisapp.ui.compositions.WelcomeComposition

@Composable
fun MainNavHost(
    preferences: SharedPreferences = LocalContext.current.getSharedPreferences(
        "authorization",
        Context.MODE_PRIVATE
    ),
    startDestination: String = "welcome",
    modifier: Modifier,
) {
    var authorization by remember { mutableStateOf<Authorization?>(null) }
    var currentUser by remember { mutableStateOf<User?>(null) }

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("home") {
            HomeComposition(
                user = currentUser!!,
                rentButtonOnClick = { navController.navigate("rent") }
            )
        }
        composable("login") {
            LoginComposition {
                authorization = it
                TODO("API client call")
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
        composable("register") {
            RegisterComposition {
                authorization = it
                navController.navigate("home") {
                    popUpTo("register") { inclusive = true }
                }
            }
        }
        composable("welcome") {
            if (preferences.contains("email")) {
                val authorizationVM: AuthorizationViewModel = viewModel()
                authorizationVM.authorize(
                    identification = preferences.getString("email", "")!!,
                    onSuccess = {
                        val token = authorizationVM.token.value
                        if (token?.isNotEmpty() == true)
                            preferences.edit().putString("token", token).apply()

                        authorization = Authorization(token!!)
                        navController.navigate("home") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    }
                )
            }

            WelcomeComposition(
                loginOnClick = { navController.navigate("login") },
                registerOnClick = { navController.navigate("register") }
            )
        }
        composable("rent") {
            TODO("Create rent screen")
        }
    }
}