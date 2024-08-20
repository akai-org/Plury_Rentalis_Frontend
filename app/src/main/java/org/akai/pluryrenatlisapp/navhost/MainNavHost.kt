package org.akai.pluryrenatlisapp.navhost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.akai.pluryrenatlisapp.apiclient.Authorization
import org.akai.pluryrenatlisapp.data.User
import org.akai.pluryrenatlisapp.ui.compositions.HomeComposition
import org.akai.pluryrenatlisapp.ui.compositions.LoginComposition
import org.akai.pluryrenatlisapp.ui.compositions.RegisterComposition

@Composable
fun MainNavHost(
    startDestination: String = "welcome",
) {
    var authorization by remember { mutableStateOf<Authorization?>(null) }
    var currentUser by remember { mutableStateOf<User?>(null) }

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            TODO("Create button actions")
            HomeComposition(
                user = currentUser!!,
                rentButtonOnClick = { navController.navigate("rent") }
            )
        }
        composable("login") {
            val userDetailsDownloadCoroutine = rememberCoroutineScope()
            LoginComposition {
                authorization = it
                userDetailsDownloadCoroutine.launch {
                    TODO("API client call")
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
        }
        composable("register") {
            val userDetailsDownloadCoroutine = rememberCoroutineScope()
            RegisterComposition {
                authorization = it
                userDetailsDownloadCoroutine.launch {
                    TODO("API client call")
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            }
        }
        composable("welcome") {
            TODO("Create welcome screen")
        }
        composable("rent") {
            TODO("Create rent screen")
        }
    }
}