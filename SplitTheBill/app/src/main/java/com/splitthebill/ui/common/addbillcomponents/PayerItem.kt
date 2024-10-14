package com.splitthebill.ui.common.addbillcomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.common.sharedcomponents.ProfilePicture
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun PayerItem(
    onDestroy: () -> Unit
) {
    val isChecked = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(2.dp,Color.Black)
    )
    {

        Box(Modifier.fillMaxWidth().padding(top = 6.dp), contentAlignment = Alignment.TopEnd){
            IconButton(onClick = { onDestroy() }, modifier = Modifier.padding(end = 8.dp)) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            ProfilePicture(
                placeholderText = "",
                size = 50.dp
            )
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                label = { Text("Amount") },
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun PayerItemPreview(){
    SplitTheBillTheme {
        PayerItem({})
    }
}