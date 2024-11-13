package com.splitthebill.ui.screens.eventscreen.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.splitthebill.data.enums.EventStatus
import com.splitthebill.data.models.Event
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.templates.DialogWithTitle
import com.splitthebill.ui.viewmodels.EventDetailViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider

@Composable
fun ProceedToPayDialog(event: Event, onDismiss: () -> Unit, onStatusUpdated: (EventStatus) -> Unit) {
    val eventDetailViewModel: EventDetailViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)

    DialogWithTitle("Proceed to pay?",onDismiss) {
        Row(Modifier.fillMaxWidth().wrapContentHeight().padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Red), onClick = {
                onDismiss()
            }) {
                Text("No")
            }
            BlueButton(text = "Yes", onClick = {
                eventDetailViewModel.updateEventStatus(event.eventId) {
                    eventDetailViewModel.setEventStatus(EventStatus.PAYING)
                    onStatusUpdated(EventStatus.PAYING)
                    onDismiss()
                }
            })
        }
    }
}