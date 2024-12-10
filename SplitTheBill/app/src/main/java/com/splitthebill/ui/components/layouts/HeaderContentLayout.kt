package com.splitthebill.ui.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/*
    It is the main layout on most of the screens. It consist a header which is responsible for holding basic or meta information related to the given screen,
    and a holder for the bottom content, which is the body of the screen, and takes most part of it.
*/
@Composable
fun HeaderContentLayout(
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
                    .wrapContentHeight()
                    .background(topColor)
                    .statusBarsPadding(),
                contentAlignment = Alignment.CenterStart
            ) {
                topContent()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(bottomColor),
            ) {
                bottomContent()
            }
        }
    }
}