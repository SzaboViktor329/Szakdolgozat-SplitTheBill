package com.splitthebill.ui.common.eventcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.splitthebill.ui.theme.SplitTheBillTheme


@Composable
fun DebtListItem() {
    val isChecked = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),

        colors = CardDefaults.cardColors(containerColor = Color.White)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                    .background(Color.LightGray, shape = RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "AD")
            }

            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                    .background(Color.LightGray, shape = RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "DA")
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(text = "$50", modifier = Modifier.align(Alignment.CenterVertically), fontSize = 24.sp)

            //Spacer(modifier = Modifier.width(8.dp))

            Checkbox(
                checked = isChecked.value,
                onCheckedChange = { isChecked.value = it },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}




@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun DebtListItemPreview(){
    SplitTheBillTheme {
        DebtListItem()
    }
}