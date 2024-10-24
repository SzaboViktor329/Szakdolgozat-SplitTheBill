package com.splitthebill.ui.screens.eventscreen.listitems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.navigation.navscreens.MainNavScreen
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun BillListItem(navController: NavHostController) {
    val isChecked = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable { navController.navigate(MainNavScreen.Bill.route) },
        colors = CardDefaults.cardColors(containerColor = Color.White)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Bill name", style = typography.titleLarge)
            Text(text = "2022.02.02", style = typography.titleMedium)
        }
    }
}

@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun BillListItemPreview(){
    SplitTheBillTheme {
        BillListItem(rememberNavController())
    }
}