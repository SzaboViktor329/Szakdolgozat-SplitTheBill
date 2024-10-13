package com.splitthebill.ui.common.sharedcomponents

import android.accounts.AuthenticatorDescription
import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlin.random.Random

@Composable
fun DialogIconButton(
    modifier: Modifier,
    iconImageVector: ImageVector,
    iconContentDescription: String? = null,
    iconColor: Color = Color.White,
    iconModifier: Modifier,
    dialog: @Composable (onDismiss: () -> Unit) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    IconButton(
        onClick = { showDialog = true },
        modifier = modifier

    ) {
        Icon(
            imageVector = iconImageVector,
            contentDescription = iconContentDescription,
            tint = iconColor,
            modifier = iconModifier
        )
    }
    if (showDialog) {
        dialog { showDialog = false }
    }
}