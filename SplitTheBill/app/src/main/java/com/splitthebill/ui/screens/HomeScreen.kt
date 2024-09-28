package com.splitthebill.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.common.eventcomponents.CompactEventListItem
import com.splitthebill.ui.common.sharedcomponents.WeightedBoxLayout
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import kotlin.random.Random

@Composable
fun HomeScreen(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val paddingStart = screenWidth / 12

    WeightedBoxLayout(
        topWeight = 1f,
        bottomWeight = 4f,
        topColor = BlueTheme,
        bottomColor = Color.White,
        mainColor = BlueTheme,
        topContent = {
            Text(
                text = "Hi AverageJoe!",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = paddingStart)
            )
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
                Box(modifier =  Modifier.fillMaxWidth().padding(top = 16.dp), contentAlignment = Alignment.Center) {
                    TextButton(onClick = {
                        navController.navigate("EventCollectionScreen")
                    }) {
                        Text(
                            text = "Show More",
                            color = Color.Blue,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Box(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), contentAlignment = Alignment.Center) {
                    Button(onClick = {},
                        modifier = Modifier.fillMaxWidth().padding(16.dp,0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlueTheme
                        )
                    ) {
                        Text("Add event", fontSize = 16.sp)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenPreview(){
    SplitTheBillTheme {
        HomeScreen(rememberNavController())
    }
}