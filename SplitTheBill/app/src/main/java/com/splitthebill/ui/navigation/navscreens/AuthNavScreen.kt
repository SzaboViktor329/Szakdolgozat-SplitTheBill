package com.splitthebill.ui.navigation.navscreens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.splitthebill.ui.screens.MainScreen
import com.splitthebill.ui.screens.authscreens.LoginScreen
import com.splitthebill.ui.screens.authscreens.RegistrationScreen

//Stores the routes to the auth screens and the actual screens.
enum class AuthNavScreen(val route: String, val content: @Composable (NavHostController) -> Unit) {
    Login("Login", { navHostController -> LoginScreen(navHostController) }),
    Registration("Registration", { navHostController -> RegistrationScreen(navHostController) }),
    Main("MainGraph", { navHostController -> MainScreen(navHostController) })
}
