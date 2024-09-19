package com.splitthebill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.screens.MainScreen
import com.splitthebill.ui.screens.authscreens.LoginScreen
import com.splitthebill.ui.screens.authscreens.RegistrationScreen
import com.splitthebill.ui.theme.SplitTheBillTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            SplitTheBillTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "LoginScreen") {
                    composable("LoginScreen") {
                        LoginScreen(navController)
                    }
                    composable("RegistrationScreen") {
                        RegistrationScreen(navController)
                    }
                    composable("MainScreen") {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SplitTheBillTheme {
        Greeting("Android")
    }
}