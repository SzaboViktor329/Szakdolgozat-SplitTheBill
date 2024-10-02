package com.splitthebill.ui.common.sharedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WeightedBoxLayout(
    topWeight: Float,
    bottomWeight: Float,
    topColor: Color,
    bottomColor: Color,
    mainColor: Color,
    topContent: @Composable BoxScope.() -> Unit,
    bottomContent: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(topWeight)
                    .background(topColor),
                contentAlignment = Alignment.CenterStart
            ) {
                topContent()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(bottomWeight)
                    .background(bottomColor),
                //contentAlignment = Alignment.TopCenter
            ) {
                bottomContent()
            }
        }
    }
}