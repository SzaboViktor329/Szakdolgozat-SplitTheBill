package com.splitthebill.ui.navigation.navgraphs

import androidx.collection.emptyLongSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.splitthebill.ui.navigation.navscreens.AuthNavScreen
import com.splitthebill.ui.navigation.navscreens.MainNavScreen
import com.splitthebill.ui.screens.userscreen.UserScreen
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider

@Composable
fun MainNavGraph(mainNavController: NavHostController, authNavController: NavHostController, modifier: Modifier) {

    ViewModelScopeProvider.mainNavStoreOwner = LocalViewModelStoreOwner.current!!
    NavHost(navController = mainNavController, startDestination = "MainGraph", modifier = modifier){
        navigation(
            startDestination = MainNavScreen.Home.route,
            route = "MainGraph"
        ) {
            MainNavScreen.entries.forEach() { mainNavScreen ->
                if(mainNavScreen.route != MainNavScreen.User.route){
                    composable(mainNavScreen.route) { mainNavScreen.content(mainNavController) }
                }
                else {
                    composable(mainNavScreen.route) { mainNavScreen.content(authNavController) }
                }
            }
        }
    }
}