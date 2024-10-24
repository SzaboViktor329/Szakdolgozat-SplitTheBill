package com.splitthebill.ui.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.components.common.FilterOption
import com.splitthebill.ui.components.templates.DialogWithTitle


@Composable
fun FilterOptionsDialog(onDismiss: () -> Unit) {
    DialogWithTitle("Select Filter Options", onDismiss) {
        var selectedPending by remember { mutableStateOf(true) }
        var selectedPaying by remember { mutableStateOf(true) }
        var selectedSettled by remember { mutableStateOf(true) }

        Column(modifier = Modifier.padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            // Example filter options
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