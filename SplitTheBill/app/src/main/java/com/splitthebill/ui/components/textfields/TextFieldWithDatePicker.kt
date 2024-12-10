package com.splitthebill.ui.components.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.splitthebill.ui.components.dialogs.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TextFieldWithDatePicker(selectedDate: String, dateFormater: SimpleDateFormat, label: String = "Date", onDateSelected: (date: String) -> Unit) {
    var showDatePicker by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {  },
        readOnly = true,
        label = { Text(label) },
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
        DatePickerDialog(
            onDateSelected = { dateInLong ->
                if(dateInLong != null){
                    onDateSelected(dateFormater.format(Date(dateInLong)))
                }
            },
            onDismiss = { showDatePicker = false }
        )
    }
}