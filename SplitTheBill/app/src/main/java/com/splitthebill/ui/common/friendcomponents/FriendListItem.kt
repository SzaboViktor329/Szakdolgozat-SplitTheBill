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
import com.splitthebill.ui.common.sharedcomponents.FriendNameWithIcon
import com.splitthebill.ui.theme.DarkGreen
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun FriendListItem() {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        FriendNameWithIcon(Modifier.fillMaxWidth().padding(10.dp),Arrangement.SpaceBetween)
    }
}

@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun FriendListItemPreview(){
    SplitTheBillTheme {
        FriendListItem()
    }
}