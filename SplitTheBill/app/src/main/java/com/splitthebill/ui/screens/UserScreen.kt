package com.splitthebill.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.common.eventcomponents.DebtListItem
import com.splitthebill.ui.common.friendcomponents.FindFriendModal
import com.splitthebill.ui.common.friendcomponents.FriendListItem
import com.splitthebill.ui.common.friendcomponents.FriendRequestListItem
import com.splitthebill.ui.common.sharedcomponents.HeaderContentLayout
import com.splitthebill.ui.common.usercomponents.UserSettingsModal
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun UserScreen(){
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = { UserScreenHeader() }
    ) {
        var showFriendRequests by remember { mutableStateOf(true) }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.fillMaxWidth().background(Color.White).padding(5.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                Text("Friend requests", modifier = Modifier.then(if(showFriendRequests) Modifier.border(2.dp, Color.Black, RoundedCornerShape(10.dp)) else Modifier).padding(5.dp)
                    .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {
                        showFriendRequests = true
                    })
                )
                Text("Friends", modifier = Modifier.then(if(!showFriendRequests) Modifier.border(2.dp, Color.Black, RoundedCornerShape(10.dp)) else Modifier).padding(5.dp)
                    .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {
                        showFriendRequests = false
                    })
                )
            }
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
            ) {
                if(showFriendRequests){
                    items(20) {
                        FriendRequestListItem()
                    }
                }
                else {
                    items(20) {
                        FriendListItem()
                    }
                }

            }
            var showFindFriendDialog by remember { mutableStateOf(false) }
            Box(Modifier.fillMaxWidth().wrapContentHeight().background(Color.White)){
                Row(
                    modifier = Modifier.fillMaxWidth().padding(25.dp,10.dp,25.dp,10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { showFindFriendDialog = !showFindFriendDialog },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlueTheme
                        )) {
                        Text("Find friend")
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier.size(20.dp))
                    }
                }
            }
            if(showFindFriendDialog) {
                FindFriendModal { showFindFriendDialog = false }
            }

        }
    }
}

@Composable
fun UserScreenHeader() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val iconSize = screenWidth / 6

    var showUserSettingsDialog by remember { mutableStateOf(false) }

    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier

                .size(iconSize)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(iconSize/2))
                .background(Color.LightGray, shape = RoundedCornerShape(iconSize/2)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "FU", style = typography.titleLarge)
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f).padding()) {
            Text(text = "FullName", style = typography.headlineLarge, color = Color.White)
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "@Username", style = typography.titleLarge, color = Color.White)
                IconButton(
                    onClick = { showUserSettingsDialog = !showUserSettingsDialog },
                    modifier = Modifier.padding(end = 4.dp).border(2.dp, Color.White, RoundedCornerShape(15.dp))
                ) {
                    Icon(Icons.Default.Settings, contentDescription = "Filter", tint = Color.White, modifier = Modifier.size(30.dp))
                }
            }
        }
        if(showUserSettingsDialog){
            UserSettingsModal { showUserSettingsDialog = false }
        }


    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun UserScreenPreview(){
    SplitTheBillTheme {
        UserScreen()
    }
}

/*
Box(Modifier.border(2.dp, Color.Black, RoundedCornerShape(15.dp))){
    Icon(Icons.Default.GroupAdd, contentDescription = "Filter", modifier = Modifier.padding(5.dp).size(30.dp))
}

 */