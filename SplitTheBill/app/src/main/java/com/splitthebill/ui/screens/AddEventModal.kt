package com.splitthebill.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.splitthebill.data.models.Event
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.dialogs.DatePickerDialog
import com.splitthebill.ui.components.templates.DialogWithTitle
import com.splitthebill.ui.components.textfields.TextFieldWithDatePicker
import com.splitthebill.ui.components.textfields.TextFieldWithDropdown
import com.splitthebill.ui.screens.userscreen.modals.UserSettingsModal
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.EventsViewModel
import com.splitthebill.ui.viewmodels.GroupViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun AddEventModal(onDismiss: () -> Unit) {
    val groupViewModel : GroupViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val eventsViewModel : EventsViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val groups by groupViewModel.groups.observeAsState(initial = emptyList())

    var eventName by remember { mutableStateOf("") }

    val dateFormater = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormater.format(Calendar.getInstance().time)) }

    val groupsMap = remember { mutableMapOf<String, String>() }
    val groupNames = remember { mutableListOf<String>() }
    var selectedGroup by remember { mutableStateOf("Select a Group") }

    var initialized by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        groupViewModel.fetchGroups {
            groups.forEach { group ->
                groupsMap[group.groupName] = group.groupId
            }
            groupsMap.keys.forEach{ groupName->
                groupNames.add(groupName)
            }
            initialized = true
        }
    }

    if(initialized){
        DialogWithTitle("Add event", onDismiss) {
            Column(Modifier.fillMaxWidth().wrapContentHeight().padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                OutlinedTextField(
                    value = eventName,
                    onValueChange = { eventName = it },
                    label = { Text("Event name") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextFieldWithDatePicker(selectedDate, dateFormater, "Start date") { newDate->
                    selectedDate = newDate
                }
                TextFieldWithDropdown(groupNames, selectedGroup, "Groups") { newSelectedGroup->
                    selectedGroup = newSelectedGroup
                }
                Spacer(Modifier.height(10.dp))
                BlueButton(
                    onClick = {
                        eventsViewModel.createEvent(
                          Event(
                              eventName = eventName,
                              groupId = groupsMap[selectedGroup]!!,
                              startDate = selectedDate,
                          )
                        ) { onDismiss() }
                    },
                    text = "Add event",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    enabled = eventName.isNotEmpty() && (selectedGroup != "Select a Group")
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun AddEventModalPreview(){
    SplitTheBillTheme {
        AddEventModal {}
    }
}