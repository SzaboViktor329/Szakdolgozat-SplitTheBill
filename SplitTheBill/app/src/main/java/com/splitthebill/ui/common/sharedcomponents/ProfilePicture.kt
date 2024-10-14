package com.splitthebill.ui.common.sharedcomponents

import android.icu.text.ListFormatter.Width
import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProfilePicture(
    placeholderText: String,
    size: Dp,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.Black,
    backgroundColor: Color = Color.LightGray,
    placeholderStyle: TextStyle = LocalTextStyle.current
) {
    Box(
        modifier = Modifier
            .size(size)
            .border(borderWidth, borderColor, shape = RoundedCornerShape(size/2))
            .background(backgroundColor, shape = RoundedCornerShape(size/2)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = placeholderText, style = placeholderStyle)
    }
}