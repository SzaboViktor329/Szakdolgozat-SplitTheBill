package com.splitthebill.ui.screens.addbillscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.data.models.User
import com.splitthebill.data.models.bill.Bill
import com.splitthebill.data.models.bill.Item
import com.splitthebill.data.models.bill.Payer
import com.splitthebill.data.models.event.Event
import com.splitthebill.ui.screens.addbillscreen.tabs.additemstab.AddItemsTab
import com.splitthebill.ui.screens.addbillscreen.tabs.addpayerstab.AddPayersTab
import com.splitthebill.ui.screens.addbillscreen.tabs.GeneralBillInformationTab
import com.splitthebill.ui.components.templates.HeaderWithBackButton
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.AddBillViewModel
import com.splitthebill.ui.viewmodels.EventDetailViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider

@Composable
fun AddBillScreen(navController: NavHostController) {
    var progressCounter by remember { mutableIntStateOf(0) }

    val addBillViewModel: AddBillViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val eventDetailViewModel: EventDetailViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)

    val users = remember { mutableStateListOf<User>() }

    val event by eventDetailViewModel.event.observeAsState(Event())
    var billName by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var total by remember { mutableDoubleStateOf(0.0) }
    val payers = remember { mutableListOf<Payer>() }
    val items = remember { mutableListOf<Item>() }

    LaunchedEffect(Unit) {
        eventDetailViewModel.getUsersFromEvent(event) { usersListResult->
            users.addAll(usersListResult)
        }
    }

    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.White,
        mainColor = Color.LightGray,
        topContent = {
            HeaderWithBackButton(navController) {
                Box(Modifier.fillMaxWidth().padding(0.dp, 16.dp), contentAlignment = Alignment.Center){
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Add bill", style = typography.headlineLarge, color = Color.White)
                        if(progressCounter == 2){
                            Text("Total: $total", style = typography.headlineMedium, color = Color.White)
                        }
                    }

                }
            }
        }
    ) {
        when(progressCounter) {
            0 -> GeneralBillInformationTab { newBillName, newSelectedDate ->
                billName = newBillName
                selectedDate = newSelectedDate
                progressCounter = 1
            }
            1 -> AddItemsTab(users) { totalResult, itemsResult ->
                items.clear()
                items.addAll(itemsResult)
                total = totalResult
                progressCounter = 2
            }
            2 -> AddPayersTab(users, total) { payersResult ->
                payers.clear()
                payers.addAll(payersResult)
                val bill = Bill(
                    eventId = event.eventId,
                    billName = billName,
                    date = selectedDate,
                    total = total,
                    payers = payers,
                    items = items
                )
                val newDebts = eventDetailViewModel.optimizeCashFlow(bill, event.debts)
                eventDetailViewModel.updateDebts(event.eventId, newDebts) {
                    event.debts = newDebts
                    eventDetailViewModel.setEvent(event)
                }
                addBillViewModel.addBill(bill) {}
                navController.popBackStack()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun AddBillScreenPreview(){
    SplitTheBillTheme {
        AddBillScreen(rememberNavController())
    }
}