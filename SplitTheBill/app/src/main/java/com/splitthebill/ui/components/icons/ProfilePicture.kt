package com.splitthebill.ui.components.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    placeholderText: String,
    size: Dp,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.Black,
    backgroundColor: Color = Color.LightGray,
    placeholderStyle: TextStyle = LocalTextStyle.current,

) {
    Box(
        modifier = modifier
            .size(size)
            .border(borderWidth, borderColor, shape = RoundedCornerShape(size/2))
            .background(backgroundColor, shape = RoundedCornerShape(size/2)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = placeholderText, style = placeholderStyle)
    }
}