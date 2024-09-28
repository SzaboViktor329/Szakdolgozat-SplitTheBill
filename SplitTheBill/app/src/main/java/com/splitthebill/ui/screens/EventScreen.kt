package com.splitthebill.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.common.eventcomponents.DebtListItem
import com.splitthebill.ui.common.eventcomponents.DetailedEventListItem
import com.splitthebill.ui.common.sharedcomponents.WeightedBoxLayout
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import kotlin.random.Random


@Composable
fun EventScreen() {
    WeightedBoxLayout(
        topWeight = 1f,
        bottomWeight = 4f,
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            EventTitleBar("Event name", "2022.02.02", "2023.03.03")
        }
    ) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 10.dp, start = 10.dp, end = 10.dp)
            ) {
                items(20) {
                    DebtListItem()
                }
            }
            Box(Modifier.fillMaxWidth().wrapContentHeight().background(Color.White)){
                Row(
                    modifier = Modifier.fillMaxWidth().padding(25.dp,10.dp,25.dp,10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                        containerColor = BlueTheme
                    )) {
                        Text("View Bills")
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                        containerColor = BlueTheme
                    )) {
                        Text("Add Bill")
                    }
                }
            }

        }
    }
}

@Composable
fun EventTitleBar(eventName: String, startDate: String, endDate: String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).statusBarsPadding()
    ) {
        Text(text = eventName, style = typography.headlineLarge, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "$startDate - $endDate", style = typography.titleMedium, color = Color.White)
    }
}



@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EventScreenPreview(){
    SplitTheBillTheme {
        EventScreen()
    }
}
