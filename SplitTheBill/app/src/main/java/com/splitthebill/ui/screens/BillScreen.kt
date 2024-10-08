package com.splitthebill.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.common.billcomponents.ItemListItem
import com.splitthebill.ui.common.billcomponents.PayerListItem
import com.splitthebill.ui.common.sharedcomponents.TitleBarWithBackButton
import com.splitthebill.ui.common.sharedcomponents.HeaderContentLayout
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun BillScreen(navController: NavHostController) {
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            BillTitleBar("Bill name", "2022.02.02", navController)
        }
    ) {
        var showPayers by remember { mutableStateOf(true) }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.fillMaxWidth().background(Color.White).padding(5.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                Text("Payers", modifier = Modifier.then(if(showPayers) Modifier.border(2.dp, Color.Black, RoundedCornerShape(10.dp)) else Modifier).padding(5.dp)
                    .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {
                        showPayers = true
                    })
                )
                Text("Items", modifier = Modifier.then(if(!showPayers) Modifier.border(2.dp, Color.Black, RoundedCornerShape(10.dp)) else Modifier).padding(5.dp)
                    .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {
                        showPayers = false
                    })
                )
            }
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
            ) {
                if (showPayers) {
                    items(20) {
                        PayerListItem()
                    }
                } else {
                    items(20) {
                        ItemListItem()
                    }
                }

            }
        }
    }
}

@Composable
fun BillTitleBar(billName: String, date: String, navController: NavHostController){
    TitleBarWithBackButton(navController) {
        Box(modifier =  Modifier.weight(1f).wrapContentHeight()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = billName, style = typography.headlineLarge, color = Color.White)
                Text(text = date, style = typography.titleMedium, color = Color.White)
                Text(text = "Total: 10000 Ft", style = typography.headlineMedium, color = Color.White)
            }
        }
    }

}



@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun BillScreenPreview(){
    SplitTheBillTheme {
        BillScreen(rememberNavController())
    }
}