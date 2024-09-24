package com.splitthebill.ui.common.eventcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CompactEventListItem(eventName: String, eventStatus: Int) {
    Box(modifier = Modifier.fillMaxWidth()){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = eventName,
                fontSize = 18.sp
            )
            Icon(
                imageVector = when(eventStatus){
                    0 -> Icons.Default.Check
                    1 -> Icons.Default.Payments
                    2 -> Icons.Default.PendingActions
                    else -> Icons.Default.Android
                },
                contentDescription = "Status Icon"
            )
        }
    }


}



