package com.splitthebill.ui.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

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