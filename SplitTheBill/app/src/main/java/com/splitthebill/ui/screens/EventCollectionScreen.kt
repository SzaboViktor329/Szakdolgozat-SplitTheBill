package com.splitthebill.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.splitthebill.ui.common.eventcomponents.CompactEventListItem
import com.splitthebill.ui.common.sharedcomponents.WeightedBoxLayout
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import kotlin.random.Random

@Composable
fun EventCollectionScreen() {
    WeightedBoxLayout(
        topWeight = 1f,
        bottomWeight = 4f,
        topColor = BlueTheme,
        bottomColor = Color.White,
        mainColor = Color.LightGray,
        topContent = {
            EventsTitleBar()
        },
        bottomContent = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
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
                    items(items = listOf("Event 1", "Event 2", "Event 3", "Event 4")) { eventName ->
                        CompactEventListItem(eventName, Random.nextInt(0, 3))
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
    )
}


@Composable
fun EventsTitleBar() {
    Row(
        modifier = Modifier
            .fillMaxSize().statusBarsPadding()
            .padding(10.dp)
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier.align(Alignment.Top).border(2.dp,Color.White, RoundedCornerShape(5.dp))
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Box(modifier =  Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(
                text = "Events",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
        }

    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EventCollectionScreenPreview(){
    SplitTheBillTheme {
        EventCollectionScreen()
    }
}