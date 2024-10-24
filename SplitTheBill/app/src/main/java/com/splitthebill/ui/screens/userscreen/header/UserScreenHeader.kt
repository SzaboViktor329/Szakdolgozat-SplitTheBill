package com.splitthebill.ui.screens.userscreen.header

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.splitthebill.ui.components.buttons.DialogIconButton
import com.splitthebill.ui.components.icons.ProfilePicture
import com.splitthebill.ui.screens.userscreen.modals.UserSettingsModal

@Composable
fun UserScreenHeader(authNavController: NavHostController) {
    val iconSize = LocalConfiguration.current.screenWidthDp.dp / 6

    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        ProfilePicture(
            placeholderText = "FU",
            size = iconSize,
            placeholderStyle = typography.titleLarge
        )
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f).padding()) {
            Text(text = "FullName", style = typography.headlineLarge, color = Color.White)
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "@Username", style = typography.titleLarge, color = Color.White)
                DialogIconButton(
                    modifier = Modifier.padding(end = 4.dp).border(2.dp, Color.White, RoundedCornerShape(15.dp)),
                    iconImageVector = Icons.Default.Settings,
                    iconContentDescription = "User settings",
                    iconModifier = Modifier.size(30.dp)
                ) { onDismiss ->
                    UserSettingsModal(onDismiss, authNavController)
                }
            }
        }
    }
}