package org.akai.pluryrenatlisapp.navhost

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ktor.http.isSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.akai.pluryrenatlisapp.apiclient.Authorization
import org.akai.pluryrenatlisapp.apiclient.RentalSearchToken
import org.akai.pluryrenatlisapp.apiclient.RentalisApiServiceService
import org.akai.pluryrenatlisapp.apiclient.UserSearchToken
import org.akai.pluryrenatlisapp.data.Rent
import org.akai.pluryrenatlisapp.data.User
import org.akai.pluryrenatlisapp.ui.components.RentableVerticalList
import org.akai.pluryrenatlisapp.ui.components.RequestResultNotification
import org.akai.pluryrenatlisapp.ui.compositions.ChoosingRentsComposition
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
                rentButtonOnClick = { navController.navigate("rent") },
                returnButtonOnClick = { navController.navigate("return") },
                registerButtonOnClick = { navController.navigate("fullRegister") },
                myRegisterButtonOnClick = { navController.navigate("myRegister") }
            )
        }
        composable("login") {
            var email by remember { mutableStateOf(preferences.getString("email", "admin@test.com")!!) }
            val message by remember { mutableStateOf("Nie udało się zalogować. Spróbuj ponownie później") }
            var showComposable by remember { mutableStateOf(false) }
            LoginComposition(
                email = email,
                onEmailChange = { email = it },
                preferences = preferences,
            ) {
                val service = RentalisApiServiceService()
                try {
                    authorization = service.authorize(mapOf("email" to email))
                    currentUser = service.getUser(
                        authorization!!,
                        mapOf(UserSearchToken.EMAIL to email)
                    )

                    navController.navigate("home") {
                        popUpTo("welcome") { inclusive = true }
                    }
                } catch (e: Exception) {
                    showComposable = true
                }
            }

            if (showComposable) {
                LaunchedEffect(key1 = message) {
                    delay(2000)
                    showComposable = false
                    navController.navigate("welcome") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    RequestResultNotification(
                        text = message,
                    )
                }
            }
        }
        composable("register") {
            val service = RentalisApiServiceService()
            val message by remember { mutableStateOf("Nie udało się zarejestrować. Spróbuj ponownie później") }
            var showComposable by remember { mutableStateOf(false) }
            RegisterComposition { email, name ->
                try {
                    authorization = service.register(mapOf("email" to email, "name" to name))
                    currentUser = service.getUser(authorization!!, mapOf(UserSearchToken.EMAIL to email))

                    navController.navigate("home") {
                        popUpTo("welcome") { inclusive = true }
                    }
                } catch (e: Exception) {
                    showComposable = true
                }
            }

            if (showComposable) {
                LaunchedEffect(key1 = message) {
                    delay(2000)
                    showComposable = false
                    navController.navigate("welcome") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    RequestResultNotification(
                        text = message,
                    )
                }
            }
        }
        composable("welcome") {
            if (preferences.contains("email")) {
                val email = preferences.getString("email", "")
                if (email != null && email != "") {
                    LaunchedEffect(email) {
                        val service = RentalisApiServiceService()
                        try {
                            authorization = service.authorize(mapOf("email" to email))
                            currentUser = service.getUser(
                                authorization!!,
                                mapOf(UserSearchToken.EMAIL to email)
                            )

                            navController.navigate("home") {
                                popUpTo("welcome") { inclusive = true }
                            }
                        } catch (e: Exception) {
                            preferences.edit().remove("email").apply()
                        }
                    }
                }
                else
                    preferences.edit().remove("email").apply()
            }

            WelcomeComposition(
                loginOnClick = { navController.navigate("login") },
                registerOnClick = { navController.navigate("register") }
            )
        }
        composable("rent") {
            val rents = remember { mutableStateListOf<Rent>() }
            val rentableCoroutine = rememberCoroutineScope()
            val service = RentalisApiServiceService()
            LaunchedEffect(authorization) {
                rentableCoroutine.launch {
                    rents.addAll(
                        service.getRentalsWithCurrentRenters(
                            auth = authorization!!,
                            searchTokens = listOf(RentalSearchToken.ALL),
                            userUUID = null
                        )
                    )
                }
            }
            var message by remember { mutableStateOf("") }
            var showComposable by remember { mutableStateOf(false) }
            ChoosingRentsComposition(
                rents = rents,
                buttonText = "Wypożycz",
                onBack = {
                    navController.popBackStack()
                },
                onAccept = {
                    try {
                        it.forEach { rent ->
                            val response = service.rent(authorization!!, rent)

                            if (response.status.isSuccess().not())
                                throw Exception()
                        }
                        message = "Wybrane przedmioty zostały pomyślnie wyporzyczone!"
                    } catch (e: Exception) {
                        message = "Nie udało się wyporzyczyć wszystkich wybranych przedmiotów. Spróbuj ponownie później"
                    }
                    showComposable = true
                }
            ) {
                RentableVerticalList(it)
            }
            if (showComposable) {
                LaunchedEffect(key1 = message) {
                    delay(2000) // Wait for 5 seconds
                    showComposable = false
                    navController.navigate("home") {
                        popUpTo("rent") { inclusive = true }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    RequestResultNotification(
                        text = message,
                    )
                }
            }
        }
        composable("return") {
            val rentableCoroutine = rememberCoroutineScope()
            val rents = remember { mutableStateListOf<Rent>() }
            val service = RentalisApiServiceService()
            LaunchedEffect(authorization) {
                rentableCoroutine.launch {
                    rents.addAll(
                        service.getRentalsWithCurrentRenters(
                            auth = authorization!!,
                            searchTokens = listOf(RentalSearchToken.ALL),
                            userUUID = currentUser?.uuid
                        )
                    )
                }
            }
            var message by remember { mutableStateOf("") }
            var showComposable by remember { mutableStateOf(false) }
            ChoosingRentsComposition(
                rents = rents,
                buttonText = "Zwróć",
                onBack = {
                    navController.popBackStack()
                },
                onAccept = {
                    try {
                        it.forEach { rent ->
                            val response = service.returnRent(authorization!!, rent)
                            if (response.status.isSuccess().not())
                                throw Exception()
                        }
                        message = "Wybrane przedmioty zostały pomyślnie zwrócone!"
                    } catch (e: Exception) {
                        message = "Nie udało się zwrócić wszystkich wybranych przedmiotów. Spróbuj ponownie później"
                    }
                    showComposable = true
                }
            )
            {
                RentableVerticalList(
                    rents = it
                )
            }
            if (showComposable) {
                LaunchedEffect(key1 = message) {
                    delay(2000) // Wait for 5 seconds
                    showComposable = false
                    navController.navigate("home") {
                        popUpTo("rent") { inclusive = true }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    RequestResultNotification(
                        text = message,
                    )
                }

            }
        }
        composable("myRegister") {
            val rentableCoroutine = rememberCoroutineScope()
            val rents = remember { mutableStateListOf<Rent>() }
            val service = RentalisApiServiceService()
            LaunchedEffect(authorization) {
                rentableCoroutine.launch {
                    rents.addAll(
                        service.getRentalsWithCurrentRenters(
                            auth = authorization!!,
                            searchTokens = listOf(RentalSearchToken.ALL),
                            userUUID = currentUser?.uuid
                        )
                    )
                }
            }
            ChoosingRentsComposition(
                rents = rents,
                onBack = {
                    navController.popBackStack()
                },
                choosable = false
            )
            {
                RentableVerticalList(
                    rents = it,
                    toCheck = false
                )
            }
        }
        composable("fullRegister") {
            val rentableCoroutine = rememberCoroutineScope()
            val rents = remember { mutableStateListOf<Rent>() }
            val service = RentalisApiServiceService()
            LaunchedEffect(authorization) {
                rentableCoroutine.launch {
                    rents.addAll(
                        service.getRentalsWithCurrentRenters(
                            auth = authorization!!,
                            searchTokens = listOf(RentalSearchToken.ALL),
                            userUUID = null
                        )
                    )
                }
            }
            ChoosingRentsComposition(
                rents = rents,
                onBack = {
                    navController.popBackStack()
                },
                choosable = false
            )
            {
                RentableVerticalList(
                    rents = it,
                    toCheck = false
                )
            }
        }

    }
}