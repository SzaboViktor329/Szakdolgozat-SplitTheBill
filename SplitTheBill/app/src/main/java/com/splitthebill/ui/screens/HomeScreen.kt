package com.splitthebill.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.splitthebill.ui.common.eventcomponents.CompactEventListItem
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import kotlin.random.Random

@Composable
fun HomeScreen(){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val paddingStart = screenWidth / 12

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(BlueTheme),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Hi AverageJoe!",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = paddingStart) // Dynamic padding
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .background(Color.White)
                ,contentAlignment = Alignment.TopCenter
            ) {

                    Column(
                        modifier = Modifier.padding(16.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = "Recent events",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )


                        LazyColumn(
                            modifier = Modifier.wrapContentHeight()
                        ) {
                            items(items = listOf("Balatoni körút", "London hétvége", "Event 3", "Event 4")) { eventName ->
                                CompactEventListItem(eventName, Random.nextInt(0,3))
                                HorizontalDivider(thickness = 2.dp)
                            }
                        }

                        Text(
                            text = "Show More",
                            color = Color.Blue,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 16.dp)

                        )
                    }
                }

        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenPreview(){
    SplitTheBillTheme {
        HomeScreen()
    }
}