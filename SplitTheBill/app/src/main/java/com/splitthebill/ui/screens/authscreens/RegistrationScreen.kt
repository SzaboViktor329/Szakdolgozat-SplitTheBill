package com.splitthebill.ui.screens.authscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.common.sharedcomponents.TextFieldWithValidation
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun RegistrationScreen(navController: NavHostController){
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(BlueTheme)
        ) {
            Text(
                text = "Sign up",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 40.sp,
                color = Color.White
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
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


                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BlueTheme
                    )
                ) {
                    Text(text = "Register", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))


                TextButton(onClick = {
                    navController.navigate("LoginScreen")
                }) {
                    Text(
                        text = "Already have an account? Log in",
                        color = Color.Blue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
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

