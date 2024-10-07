package com.splitthebill.ui.common.billcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.splitthebill.ui.common.eventcomponents.BillListItem
import com.splitthebill.ui.theme.DarkGreen
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun PayerListItem() {
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
                .padding(32.dp, 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                    .background(Color.LightGray, shape = RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "AD")
            }
            Text(text = "10000 Ft", style = typography.titleLarge, color = DarkGreen)
        }
    }
}

@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun PayerListItemPreview(){
    SplitTheBillTheme {
        PayerListItem()
    }
}