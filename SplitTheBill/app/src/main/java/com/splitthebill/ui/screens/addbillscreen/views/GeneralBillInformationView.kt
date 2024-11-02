package com.splitthebill.ui.screens.addbillscreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.dialogs.DatePickerDialog
import com.splitthebill.ui.components.textfields.TextFieldWithDatePicker
import com.splitthebill.ui.components.textfields.TextFieldWithDropdown
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun GeneralBillInformationView(
    onProceed: () -> Unit
){
    val dateFormater = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormater.format(Calendar.getInstance().time)) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
        Column {
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                label = { Text("Bill name") },
                modifier = Modifier.fillMaxWidth()
            )
            TextFieldWithDatePicker(selectedDate, dateFormater) { newDate->
                selectedDate = newDate
            }
        }
        BlueButton(
            onClick = { onProceed() },
            text = "Continue",
            modifier = Modifier.padding(bottom = 30.dp)
        )
    }
}