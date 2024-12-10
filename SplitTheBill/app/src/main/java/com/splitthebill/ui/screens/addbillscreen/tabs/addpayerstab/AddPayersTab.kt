package com.splitthebill.ui.screens.addbillscreen.tabs.addpayerstab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.data.models.User
import com.splitthebill.data.models.bill.Payer
import com.splitthebill.ui.screens.addbillscreen.tabs.addpayerstab.listitems.AddPayerListItem
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.utils.isAdBillPayersValid

@Composable
fun AddPayersTab(
    users: List<User>,
    total: Double,
    onProceed: (List<Payer>) -> Unit
) {
    var payerCount by remember { mutableIntStateOf(1) }
    val payers = remember { mutableStateListOf(Payer()) }
    val listState = rememberLazyListState()

    LaunchedEffect(payerCount) {
        if(payerCount > 0){
            listState.animateScrollToItem(payerCount-1)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.Start) {
        Text("Add payers", style = typography.titleLarge)
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f), state = listState) {
            items(payers) { payer ->
                AddPayerListItem(payer, users) {
                    payers.remove(payer)
                    payerCount--
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp ,bottom = 0.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            BlueButton(
                onClick = {
                    payers.add(Payer())
                    payerCount++
                },
                text = "New payer"
            )
            BlueButton(
                onClick = {
                    if(isAdBillPayersValid(payers,total)) onProceed(payers)
                },
                text = "Continue"
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun AddPayersComponentPreview(){
    SplitTheBillTheme {
        AddPayersTab(listOf(),2.0) {}
    }
}