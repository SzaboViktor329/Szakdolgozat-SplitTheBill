package com.splitthebill.ui.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.TextUnit
import com.splitthebill.ui.theme.BlueTheme

@Composable
fun BlueButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    iconImageVector: ImageVector? = null,
    iconFontSize: TextUnit = TextUnit.Unspecified,
    iconModifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = BlueTheme
        ),
        modifier = modifier
    ) {
        Text(text, fontSize = iconFontSize, modifier = textModifier)
        if(iconImageVector != null){
            Icon(imageVector = iconImageVector, contentDescription = null, modifier = iconModifier)
        }
    }
}