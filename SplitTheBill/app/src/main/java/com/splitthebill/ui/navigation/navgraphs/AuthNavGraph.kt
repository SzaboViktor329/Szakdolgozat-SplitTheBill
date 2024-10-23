package com.splitthebill.ui.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.splitthebill.ui.navigation.navscreens.AuthNavScreen

@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "AuthGraph") {
        navigation(
            startDestination = AuthNavScreen.Login.route,
            route = "AuthGraph"
        ) {
            AuthNavScreen.entries.forEach { authNavScreen ->
                composable(authNavScreen.route) { authNavScreen.content(navController) }
            }
        }
    }
}