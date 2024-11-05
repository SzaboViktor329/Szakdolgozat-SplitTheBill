package com.splitthebill.ui.screens.eventcollectionscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.screens.eventcollectionscreen.listitems.DetailedEventListItem
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.common.BottomWhiteStrip
import com.splitthebill.ui.components.buttons.DialogIconButton
import com.splitthebill.ui.components.dialogs.FilterOptionsDialog
import com.splitthebill.ui.components.templates.DialogWithTitle
import com.splitthebill.ui.components.templates.HeaderWithBackButton
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.screens.AddEventModal
import com.splitthebill.ui.screens.eventcollectionscreen.header.EventCollectionScreenHeader
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.EventDetailViewModel
import com.splitthebill.ui.viewmodels.EventsViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider
import kotlin.random.Random

@Composable
fun EventCollectionScreen(navController: NavHostController) {
    val eventsViewModel : EventsViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val events by eventsViewModel.events.observeAsState(initial = emptyList())

    val eventDetailViewModel: EventDetailViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)

    LaunchedEffect(Unit) {
        eventsViewModel.fetchEvents()
    }

    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            EventCollectionScreenHeader(navController)
        },
        bottomContent = {
            Column {
                LazyColumn(
                    modifier = Modifier.weight(1f).padding(top = 10.dp, start = 10.dp, end = 10.dp)
                ) {
                    items(events) { event ->
                        DetailedEventListItem(
                            event,
                            navController
                        ) { eventDetailViewModel.setEvent(event) }
                    }
                }
                BottomWhiteStrip {
                    var showAddEventDialog by remember { mutableStateOf(false) }
                    BlueButton(
                        onClick = { showAddEventDialog = !showAddEventDialog },
                        text = "Add event",
                        iconFontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth().padding(16.dp,0.dp)
                    )
                    if(showAddEventDialog) AddEventModal { showAddEventDialog = false }
                }
            }
        }
    )
}



@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EventCollectionScreenPreview(){
    SplitTheBillTheme {
        EventCollectionScreen(rememberNavController())
    }
}