package com.splitthebill.ui.screens.addbillscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.data.models.User
import com.splitthebill.ui.components.templates.DialogWithTitle
import com.splitthebill.ui.screens.AddEventModal
import com.splitthebill.ui.screens.userscreen.listitems.FriendListItem
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun UserPickerModal(users: List<User>, onDismiss: (User) -> Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    DialogWithTitle("Pick a user", { onDismiss(User()) }) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = screenHeight.dp / 2)){
            HorizontalDivider(modifier = Modifier.padding(10.dp,0.dp),thickness = 2.dp)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 10.dp, end = 10.dp)
            ) {
                items(users) { user ->
                    FriendListItem(user, modifier = Modifier.clickable {
                        onDismiss(user)
                    })
                    HorizontalDivider(thickness = 2.dp)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun UserPickerModalPreview(){
    SplitTheBillTheme {
        UserPickerModal(listOf(User(), User())) {}
    }
}