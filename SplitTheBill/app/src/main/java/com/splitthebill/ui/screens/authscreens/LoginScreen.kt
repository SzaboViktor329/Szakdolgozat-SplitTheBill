package com.splitthebill.ui.screens.authscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.R
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.buttons.BlueTextButton
import com.splitthebill.ui.components.textfields.TextFieldWithValidation
import com.splitthebill.ui.navigation.navscreens.AuthNavScreen
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.utils.isValidEmail
import com.splitthebill.ui.utils.isValidPassword


@Composable
fun LoginScreen(navController: NavHostController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var validFieldCounter by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize().background(BlueTheme)){
        Column(modifier = Modifier.fillMaxSize()) {
            BoxWithConstraints(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(BlueTheme)
            ) {
                val boxWidth = maxWidth
                Image(
                    painter = painterResource(id = R.drawable.logo2), // Your logo resource
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(boxWidth/2) // Adjust size as needed
                        .align(Alignment.Center)
                        .padding(bottom = 16.dp)
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithValidation(value = email,{ email = it },"Email","Not a valid email address",
                        { validCounterIncrementValue -> validFieldCounter+=validCounterIncrementValue }, ::isValidEmail)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithValidation(value = password,{ password = it },"Password","Password must be at least 6 character",
                        { validCounterIncrementValue -> validFieldCounter+=validCounterIncrementValue }, ::isValidPassword)

                    Spacer(modifier = Modifier.height(16.dp))

                    BlueButton(
                        onClick = {
                            if(validFieldCounter == 2){
                                navController.navigate(AuthNavScreen.Main.route) {
                                    popUpTo("AuthGraph") { inclusive = true }
                                }
                            }
                        },
                        text = "Login",
                        iconFontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = validFieldCounter == 2
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    BlueTextButton(
                        onClick = { navController.navigate(AuthNavScreen.Registration.route) },
                        text = "Create a new account",
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DefaultPreview() {
    SplitTheBillTheme {
        LoginScreen(rememberNavController())
    }
}