package com.splitthebill.ui.screens.userscreen.listitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.components.common.FriendNameWithIcon
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