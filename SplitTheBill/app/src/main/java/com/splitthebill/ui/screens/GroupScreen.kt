package com.splitthebill.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.common.BottomWhiteStrip
import com.splitthebill.ui.components.common.FriendNameWithIcon
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.navigation.navscreens.MainNavScreen
import com.splitthebill.ui.screens.eventcollectionscreen.listitems.DetailedEventListItem
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import kotlin.random.Random

@Composable
fun GroupScreen(navController: NavHostController){
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = { Text("Groups", style = typography.headlineLarge, color = Color.White, modifier = Modifier.padding(20.dp, 16.dp)) }
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 10.dp)
            ) {
                items(20) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable {
                                navController.navigate(MainNavScreen.GroupDetails.route)
                            },
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Text("Group name", style = typography.titleLarge, modifier = Modifier.padding(16.dp, 8.dp))
                    }
                }
            }
            BottomWhiteStrip {
                BlueButton(
                    onClick = { navController.navigate(MainNavScreen.CreateGroup.route) },
                    text = "Create group",
                    iconFontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth().padding(16.dp,0.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun GroupScreenPreview(){
    SplitTheBillTheme {
        GroupScreen(rememberNavController())
    }
}