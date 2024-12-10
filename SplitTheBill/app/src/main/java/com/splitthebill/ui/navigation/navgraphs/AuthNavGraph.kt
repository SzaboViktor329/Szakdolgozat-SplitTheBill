package com.splitthebill.ui.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.splitthebill.ui.navigation.navscreens.AuthNavScreen
import com.splitthebill.ui.viewmodels.AuthViewModel

//This navGraph contains all the auth related screens. On a successful login it proceeds to the mainNavGraph.
@Composable
fun AuthNavGraph(navController: NavHostController) {
    val authViewModel : AuthViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "AuthGraph") {
        navigation(
            startDestination = if(authViewModel.currentUserAuth.value == null) AuthNavScreen.Login.route else AuthNavScreen.Main.route,
            route = "AuthGraph"
        ) {
            AuthNavScreen.entries.forEach { authNavScreen ->
                composable(authNavScreen.route) { authNavScreen.content(navController) }
            }
        }
        composable(AuthNavScreen.Main.route) { AuthNavScreen.Main.content(navController) }
    }
}
