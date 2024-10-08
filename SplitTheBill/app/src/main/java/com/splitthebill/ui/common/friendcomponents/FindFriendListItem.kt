package com.splitthebill.ui.common.friendcomponents

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.DarkGreen
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun FindFriendListItem(onSelect: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(2.dp, Color.Black)
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
            IconButton(
                onClick = onSelect,
                modifier = Modifier.border(2.dp, BlueTheme, RoundedCornerShape(15.dp))
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = BlueTheme, modifier = Modifier.size(30.dp))
            }
        }
    }
}

@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun FindFriendListItemPreview(){
    SplitTheBillTheme {
        FindFriendListItem({})
    }
}