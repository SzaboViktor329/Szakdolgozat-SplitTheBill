package com.splitthebill.ui.screens.authscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.buttons.BlueTextButton
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.components.textfields.TextFieldWithValidation
import com.splitthebill.ui.navigation.navscreens.AuthNavScreen
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun RegistrationScreen(navController: NavHostController){
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.White,
        mainColor = Color.White,
        topContent = {
            Text(
                text = "Sign up",
                modifier = Modifier.align(Alignment.Center).padding(0.dp, 26.dp),
                fontSize = 40.sp,
                color = Color.White
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithValidation(value = "", onValueChange = {}, "Enter email", "email Error")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithValidation(value = "", onValueChange = {}, "Enter username", "username Error")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithValidation(value = "", onValueChange = {}, "Enter password", "password Error")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithValidation(value = "", onValueChange = {}, "Confirm password ", "password Error")

            Spacer(modifier = Modifier.height(16.dp))

            BlueButton(
                onClick = {},
                text = "Register",
                iconFontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))

            BlueTextButton(
                onClick = { navController.navigate(AuthNavScreen.Login.route) },
                text = "Already have an account? Log in",
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun RegisterPreview() {
    SplitTheBillTheme {
        RegistrationScreen(rememberNavController())
    }
}

