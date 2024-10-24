package com.splitthebill.ui.screens.billscreen.listitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.splitthebill.ui.components.icons.ProfilePicture
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun ItemsListItem() {
    val isChecked = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    )
    {
        Column {
            Text(text = "RandomPerson's part", style = typography.titleMedium, modifier = Modifier.padding(top = 16.dp, start = 16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProfilePicture(
                    placeholderText = "AD",
                    size = 40.dp
                )
                Text(text = "5000 Ft", style = typography.titleLarge, color = Color.Red)
            }
        }
    }
}

@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun ItemListItemPreview(){
    SplitTheBillTheme {
        ItemsListItem()
    }
}