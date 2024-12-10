package com.splitthebill.ui.screens.billscreen.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.splitthebill.ui.components.templates.HeaderWithBackButton

@Composable
fun BillScreenHeader(billName: String, date: String, total: Double, navController: NavHostController){
    HeaderWithBackButton(navController) {
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
                Text(text = "Total: $total Ft", style = typography.headlineMedium, color = Color.White)
            }
        }
    }
}