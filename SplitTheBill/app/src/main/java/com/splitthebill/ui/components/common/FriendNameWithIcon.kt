package com.splitthebill.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.components.icons.ProfilePicture
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun FriendNameWithIcon(modifier: Modifier, horizontal: Arrangement.Horizontal) {
    Row(modifier = modifier, horizontalArrangement = horizontal, verticalAlignment = Alignment.CenterVertically) {
        Text("FullName", style = typography.titleLarge)
        ProfilePicture(
            placeholderText = "AD",
            size = 40.dp
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun FriendNameWithIconPreview(){
    SplitTheBillTheme {
        FriendNameWithIcon(Modifier.fillMaxWidth().wrapContentHeight(),Arrangement.SpaceBetween)
    }
}