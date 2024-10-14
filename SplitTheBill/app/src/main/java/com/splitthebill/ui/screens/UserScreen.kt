package com.splitthebill.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.splitthebill.ui.common.friendcomponents.FindFriendModal
import com.splitthebill.ui.common.friendcomponents.FriendListItem
import com.splitthebill.ui.common.friendcomponents.FriendRequestListItem
import com.splitthebill.ui.common.sharedcomponents.BlueButton
import com.splitthebill.ui.common.sharedcomponents.BottomWhiteStrip
import com.splitthebill.ui.common.sharedcomponents.ComponentNavBar
import com.splitthebill.ui.common.sharedcomponents.DialogIconButton
import com.splitthebill.ui.common.sharedcomponents.HeaderContentLayout
import com.splitthebill.ui.common.sharedcomponents.ProfilePicture
import com.splitthebill.ui.common.usercomponents.UserSettingsModal
import com.splitthebill.ui.interfaces.ComponentNavBarOptionLabel
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme



enum class UserScreenComponentNavBarOption(override val label: String) : ComponentNavBarOptionLabel {
    FRIEND_REQUESTS("Friend Requests"),
    FRIENDS("Friends")
}

@Composable
fun UserScreen(){
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = { UserScreenHeader() }
    ) {
        var selectedOption by remember { mutableStateOf(UserScreenComponentNavBarOption.FRIEND_REQUESTS) }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            ComponentNavBar(
                options = UserScreenComponentNavBarOption.entries.toTypedArray(),
                selectedOption = selectedOption,
                onOptionSelected = { option -> selectedOption = option }
            )
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
            ) {
                when(selectedOption) {
                    UserScreenComponentNavBarOption.FRIEND_REQUESTS -> { items(20) { FriendRequestListItem() } }
                    UserScreenComponentNavBarOption.FRIENDS -> { items(20) { FriendListItem() } }
                }
            }
            var showFindFriendDialog by remember { mutableStateOf(false) }
            BottomWhiteStrip {
                BlueButton(
                    onClick = { showFindFriendDialog = !showFindFriendDialog },
                    text = "Find friend ",
                    iconImageVector = Icons.Default.Search,
                    iconModifier = Modifier.size(20.dp)
                )
            }
            if(showFindFriendDialog) {
                FindFriendModal { showFindFriendDialog = false }
            }

        }
    }
}

@Composable
fun UserScreenHeader() {
    val iconSize = LocalConfiguration.current.screenWidthDp.dp / 6

    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        ProfilePicture(
            placeholderText = "FU",
            size = iconSize,
            placeholderStyle = typography.titleLarge
        )
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f).padding()) {
            Text(text = "FullName", style = typography.headlineLarge, color = Color.White)
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "@Username", style = typography.titleLarge, color = Color.White)
                DialogIconButton(
                    modifier = Modifier.padding(end = 4.dp).border(2.dp, Color.White, RoundedCornerShape(15.dp)),
                    iconImageVector = Icons.Default.Settings,
                    iconContentDescription = "User settings",
                    iconModifier = Modifier.size(30.dp)
                ) { onDismiss ->
                    UserSettingsModal(onDismiss)
                }
            }
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