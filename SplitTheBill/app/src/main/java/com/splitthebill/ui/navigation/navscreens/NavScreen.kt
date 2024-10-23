package com.splitthebill.ui.navigation.navscreens

import androidx.compose.runtime.Composable
import com.splitthebill.ui.screens.MainScreen

sealed class NavScreen(val route: String) {
    abstract val screen: @Composable () -> Unit
}