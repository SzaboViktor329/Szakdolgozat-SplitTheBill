package com.splitthebill.ui.common.addbillcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.common.sharedcomponents.BlueButton
import com.splitthebill.ui.common.sharedcomponents.DatePickerModal
import com.splitthebill.ui.theme.BlueTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicBillInformationComponent(
    onProceed: () -> Unit
){
    val calendar = Calendar.getInstance()
    val dateFormater = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormater.format(calendar.time)) }
    var showDatePicker by remember { mutableStateOf(false) }

    var isExpanded by remember { mutableStateOf(false) }
    var selectedGroup by remember { mutableStateOf("Select a Group") }
    val options = listOf("Group 1", "Group 2", "Group 3", "Group 4", "Group 5", "Group 6", "Group 7")


    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
        Column {
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                label = { Text("Bill name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = selectedDate,
                onValueChange = {  },
                readOnly = true,
                label = { Text("Date") },
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            if(showDatePicker){
                DatePickerModal(
                    onDateSelected = { dateInLong ->
                        if(dateInLong != null){
                            selectedDate = dateFormater.format(Date(dateInLong))
                        }
                    },
                    onDismiss = { showDatePicker = false }
                )
            }


            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded },
            ) {
                OutlinedTextField(
                    value = selectedGroup,
                    onValueChange = {},
                    label = { Text("Group") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                    options.forEachIndexed { index, group ->
                        DropdownMenuItem(
                            text = { Text(group) },
                            onClick = {
                                selectedGroup = options[index]
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }
        BlueButton(
            onClick = { onProceed() },
            text = "Continue",
            modifier = Modifier.padding(bottom = 30.dp)
        )
    }
}