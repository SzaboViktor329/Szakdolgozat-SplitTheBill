package com.splitthebill.ui.screens.userscreen.listitems

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.data.models.FriendRequest
import com.splitthebill.data.models.User
import com.splitthebill.data.models.UserWithRequest
import com.splitthebill.ui.components.icons.ProfilePicture
import com.splitthebill.ui.theme.DarkGreen
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.utils.createMonogram

@Composable
fun FriendRequestListItem(
    userWithRequest: UserWithRequest,
    onAccept: (FriendRequest) ->Unit,
    onReject: (FriendRequest) -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(userWithRequest.user.fullname, style = typography.titleLarge)
                Text(userWithRequest.user.username)
            }
            ProfilePicture(
                placeholderText = createMonogram(userWithRequest.user.fullname),
                size = 40.dp
            )
            Row {
                IconButton(
                    onClick = { onAccept(userWithRequest.friendRequest) },
                    modifier = Modifier.border(2.dp, DarkGreen, RoundedCornerShape(15.dp))
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = DarkGreen, modifier = Modifier.size(30.dp))
                }
                Spacer(Modifier.width(6.dp))
                IconButton(
                    onClick = { onReject(userWithRequest.friendRequest) },
                    modifier = Modifier.border(2.dp, Color.Red, RoundedCornerShape(15.dp))
                ) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.Red, modifier = Modifier.size(30.dp))
                }
            }
        }
    }
}

@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun FriendRequestListItemPreview(){
    SplitTheBillTheme {
        FriendRequestListItem(UserWithRequest(User("","Username","FullName")),{},{})
    }
}

