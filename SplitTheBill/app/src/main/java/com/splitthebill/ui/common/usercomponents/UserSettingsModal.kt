package com.splitthebill.ui.common.usercomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.common.sharedcomponents.DialogWithTitle
import com.splitthebill.ui.common.sharedcomponents.FriendNameWithIcon
import com.splitthebill.ui.screens.EventScreen
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun UserSettingsModal(onDismiss: () -> Unit) {
    DialogWithTitle("",onDismiss) {
        Column(Modifier.fillMaxWidth().wrapContentHeight().padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Text("FullName", style = typography.titleLarge)
            Text("@UserName")
            Spacer(Modifier.height(4.dp))
            Text("Email:")
            Text("user@randomeamail.com")
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
                onClick = {  },
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
        UserSettingsModal({})
    }
}