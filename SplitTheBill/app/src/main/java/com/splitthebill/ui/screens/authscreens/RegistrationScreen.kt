package com.splitthebill.ui.screens.authscreens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.data.models.User
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.buttons.BlueTextButton
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.components.textfields.TextFieldWithValidation
import com.splitthebill.ui.navigation.navscreens.AuthNavScreen
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.utils.isValidEmail
import com.splitthebill.ui.utils.isValidPassword
import com.splitthebill.ui.viewmodels.RegistrationViewModel

@Composable
fun RegistrationScreen(navController: NavHostController){
    val registrationViewModel: RegistrationViewModel = hiltViewModel()

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var validFieldCounter by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

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
            TextFieldWithValidation(value = email, { email = it },"Enter email","Not a valid email address",
                { validCounterIncrementValue -> validFieldCounter+=validCounterIncrementValue }, ::isValidEmail)
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithValidation(value = username, onValueChange = { username = it }, "Enter username", "username Error",
                { validCounterIncrementValue -> validFieldCounter+=validCounterIncrementValue },{true})
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithValidation(value = fullName, onValueChange = { fullName = it }, "Enter full name", "full name Error",
                { validCounterIncrementValue -> validFieldCounter+=validCounterIncrementValue },{true})
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithValidation(value = password,{ password = it },"Enter password","Password must be at least 6 character",
                { validCounterIncrementValue -> validFieldCounter+=validCounterIncrementValue }, ::isValidPassword)
            Spacer(modifier = Modifier.height(16.dp))

            BlueButton(
                onClick = {
                    registrationViewModel.register(
                        email,
                        password,
                        User(
                            userName = username,
                            fullName = fullName
                        )) { errorMessage ->
                        if(errorMessage != "") { Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show() }
                        else {
                            navController.navigate(AuthNavScreen.Main.route) {
                                popUpTo("AuthGraph") { inclusive = true }
                            }
                        }
                    }
                },
                text = "Register",
                iconFontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                enabled = validFieldCounter == 4
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

