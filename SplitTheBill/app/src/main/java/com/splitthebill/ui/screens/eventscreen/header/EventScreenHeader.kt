package com.splitthebill.ui.screens.eventscreen.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.splitthebill.data.enums.EventStatus
import com.splitthebill.data.models.event.Event
import com.splitthebill.ui.components.buttons.DialogIconButton
import com.splitthebill.ui.components.templates.HeaderWithBackButton
import com.splitthebill.ui.screens.eventscreen.dialogs.ProceedToPayDialog
import com.splitthebill.ui.viewmodels.EventDetailViewModel

@Composable
fun EventScreenHeader(event: Event, navController: NavHostController){
    val eventDetailViewModel: EventDetailViewModel = hiltViewModel()
    val eventStatus = eventDetailViewModel.event.observeAsState(Event()).value.status

    HeaderWithBackButton(navController) {
        Box(modifier = Modifier.weight(1f).wrapContentHeight()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = event.eventName, style = typography.headlineLarge, color = Color.White)
                Text(text = "${event.startDate} - ${event.finishDate}", style = typography.titleMedium, color = Color.White)
            }
        }
        DialogIconButton(
            modifier = Modifier.padding(end = 4.dp).clip(RoundedCornerShape(15.dp)).background(Color.White),
            iconImageVector = when(eventStatus){
                EventStatus.FINISHED -> Icons.Default.Check
                EventStatus.PAYING -> Icons.Default.Payments
                EventStatus.PENDING -> Icons.Default.PendingActions
            },
            iconColor = Color.Black,
            iconContentDescription = "Event Status",
            iconModifier = Modifier.size(30.dp),
        ) { onDismiss ->
            if(event.status==EventStatus.PENDING) {
                ProceedToPayDialog(event, onDismiss) {
                    eventDetailViewModel.refreshEvent()
                }
            }
        }
    }
}