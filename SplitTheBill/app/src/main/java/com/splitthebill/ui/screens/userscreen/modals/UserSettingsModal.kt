package com.splitthebill.ui.screens.userscreen.modals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.components.templates.DialogWithTitle
import com.splitthebill.ui.navigation.navscreens.AuthNavScreen
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.AuthViewModel
import com.splitthebill.ui.viewmodels.UserViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider

@Composable
fun UserSettingsModal(onDismiss: () -> Unit, authNavController: NavHostController) {
    val authViewModel : AuthViewModel = hiltViewModel()
    val userViewModel : UserViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)

    val userName = userViewModel.currentUser.username
    val fullName = userViewModel.currentUser.fullname
    val email = userViewModel.email

    DialogWithTitle("",onDismiss) {
        Column(Modifier.fillMaxWidth().wrapContentHeight().padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Text(fullName, style = typography.titleLarge)
            Text("Username: $userName")
            Spacer(Modifier.height(4.dp))
            Text("Email:")
            Text(email)
            Spacer(Modifier.height(4.dp))
            Button(
                onClick = {  },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueTheme
                )) {
                Text("Reset password")
            }
            Spacer(Modifier.height(4.dp))
            Button(
                onClick = {
                    authViewModel.logout()
                    onDismiss()
                    authNavController.navigate(AuthNavScreen.Login.route) {
                        popUpTo("MainGraph") { inclusive = true }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )) {
                Text("Log out ")
                Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = null, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun UserSettingsModalPreview(){
    SplitTheBillTheme {
        UserSettingsModal({},rememberNavController())
    }
}