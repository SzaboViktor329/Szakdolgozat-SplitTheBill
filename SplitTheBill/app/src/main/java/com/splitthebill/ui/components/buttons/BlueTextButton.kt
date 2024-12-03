package com.splitthebill.ui.components.buttons

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.splitthebill.ui.theme.BlueTheme

@Composable
fun BlueTextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight? = null,
    enabled: Boolean = true
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ){
        Text(
            text = text,
            color = if(enabled) BlueTheme else Color.Gray,
            fontSize = fontSize,
            fontWeight = fontWeight
        )
    }
}