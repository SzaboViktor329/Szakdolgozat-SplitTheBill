package com.splitthebill.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun GroupScreen(){
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = { Text("Groups", style = typography.headlineLarge, color = Color.White, modifier = Modifier.padding(20.dp, 16.dp)) }
    ) {

    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun GroupScreenPreview(){
    SplitTheBillTheme {
        GroupScreen()
    }
}