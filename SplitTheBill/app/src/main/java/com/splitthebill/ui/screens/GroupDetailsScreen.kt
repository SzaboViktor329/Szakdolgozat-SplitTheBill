package com.splitthebill.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.common.BottomWhiteStrip
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.components.templates.HeaderWithBackButton
import com.splitthebill.ui.screens.userscreen.listitems.FriendListItem
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun GroupDetailsScreen(navController: NavHostController) {
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            HeaderWithBackButton(navController) {
                Text("GroupName", style = typography.headlineLarge, color = Color.White, modifier = Modifier.padding(20.dp, 10.dp))
            }
        }
    ) {
        Column {
            Text(
                text = "Group members",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().background(Color.White).padding(top = 8.dp, bottom = 8.dp, start = 16.dp)
            )
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 10.dp)
            ) {
                items(6) { FriendListItem() }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun GroupDetailsScreenPreview(){
    SplitTheBillTheme {
        GroupDetailsScreen(rememberNavController())
    }
}