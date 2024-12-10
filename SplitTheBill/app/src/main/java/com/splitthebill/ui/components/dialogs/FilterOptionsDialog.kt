package com.splitthebill.ui.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.splitthebill.data.enums.EventStatus
import com.splitthebill.ui.components.common.FilterOption
import com.splitthebill.ui.components.templates.DialogWithTitle
import com.splitthebill.ui.viewmodels.EventsViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider

@Composable
fun FilterOptionsDialog(onDismiss: () -> Unit) {
    val eventsViewModel: EventsViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)

    var selectedPending by remember { mutableStateOf(false) }
    var selectedPaying by remember { mutableStateOf(false) }
    var selectedSettled by remember { mutableStateOf(false) }

    var eventStatuses: MutableList<EventStatus>

    DialogWithTitle(
        title = "Select Filter Options",
        onDismiss = {
            eventStatuses = mutableListOf()
            if(selectedPending) eventStatuses.add(EventStatus.PENDING)
            if(selectedPaying) eventStatuses.add(EventStatus.PAYING)
            if(selectedSettled) eventStatuses.add(EventStatus.FINISHED)
            eventsViewModel.setEventStatuses(eventStatuses)
            eventsViewModel.fetchEvents()
            onDismiss()
        }
    ) {
        LaunchedEffect(Unit) {
            eventStatuses = eventsViewModel.getEventStatuses().toMutableList()
            if(eventStatuses.contains(EventStatus.PENDING)) selectedPending = true
            if(eventStatuses.contains(EventStatus.PAYING)) selectedPaying = true
            if(eventStatuses.contains(EventStatus.FINISHED)) selectedSettled = true
            eventStatuses = mutableListOf()
        }

        Column(modifier = Modifier.padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            FilterOption("Pending", Icons.Default.PendingActions,selectedPending) {
                selectedPending = !selectedPending
            }
            FilterOption("Paying", Icons.Default.Payments,selectedPaying) {
                selectedPaying = !selectedPaying
            }
            FilterOption("Settled", Icons.Default.Check,selectedSettled) {
                selectedSettled = !selectedSettled
            }
        }
    }
}