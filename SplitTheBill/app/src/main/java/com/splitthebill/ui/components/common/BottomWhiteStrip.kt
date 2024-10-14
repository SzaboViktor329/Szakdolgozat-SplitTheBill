package com.splitthebill.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//BottomBarLayout

@Composable
fun BottomWhiteStrip(
    content: @Composable () -> Unit,
) {
    Box(Modifier.fillMaxWidth().wrapContentHeight().background(Color.White).padding(25.dp,10.dp,25.dp,10.dp), contentAlignment = Alignment.Center) {
        content()
    }
}