package com.splitthebill.ui.common.friendcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.DarkGreen
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun FriendRequestListItem() {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text("FullName", style = typography.titleLarge)
                Text("@UserName")
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                    .background(Color.LightGray, shape = RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "AD")
            }
            Row {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.border(2.dp, DarkGreen, RoundedCornerShape(15.dp))
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = DarkGreen, modifier = Modifier.size(30.dp))
                }
                Spacer(Modifier.width(6.dp))
                IconButton(
                    onClick = { /*TODO*/ },
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
        FriendRequestListItem()
    }
}

