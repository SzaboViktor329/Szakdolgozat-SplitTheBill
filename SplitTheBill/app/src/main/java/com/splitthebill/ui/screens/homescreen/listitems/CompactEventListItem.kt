package com.splitthebill.ui.screens.homescreen.listitems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.navigation.NavHostController
import com.splitthebill.data.enums.EventStatus
import com.splitthebill.data.models.event.Event
import com.splitthebill.ui.navigation.navscreens.MainNavScreen

@Composable
fun CompactEventListItem(event: Event, navController: NavHostController, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
                navController.navigate(MainNavScreen.Event.route)
            }
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = event.eventName,
                fontSize = 18.sp
            )
            Icon(
                imageVector = when(event.status){
                    EventStatus.FINISHED -> Icons.Default.Check
                    EventStatus.PAYING -> Icons.Default.Payments
                    EventStatus.PENDING -> Icons.Default.PendingActions
                },
                contentDescription = "Status Icon"
            )
        }
    }
}