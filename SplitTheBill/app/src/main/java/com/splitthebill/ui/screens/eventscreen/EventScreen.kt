package com.splitthebill.ui.screens.eventscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.splitthebill.data.enums.DebtStatus
import com.splitthebill.data.enums.EventStatus
import com.splitthebill.data.models.User
import com.splitthebill.data.models.event.Debt
import com.splitthebill.data.models.event.Event
import com.splitthebill.ui.screens.eventscreen.listitems.BillListItem
import com.splitthebill.ui.screens.eventscreen.listitems.DebtListItem
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.common.BottomWhiteStrip
import com.splitthebill.ui.components.navbars.TabNavBar
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.components.navbars.TabNavBarOptionLabel
import com.splitthebill.ui.navigation.navscreens.MainNavScreen
import com.splitthebill.ui.screens.eventscreen.header.EventScreenHeader
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.BillListViewModel
import com.splitthebill.ui.viewmodels.EventDetailViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

enum class EventScreenTabNavBarOption(override val label: String) : TabNavBarOptionLabel {
    DEBTS("Debts"),
    BILLS("Bills")
}

@Composable
fun EventScreen(navController: NavHostController) {
    val eventDetailViewModel: EventDetailViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val event by eventDetailViewModel.event.observeAsState(Event())

    val users = remember { mutableStateListOf<User>() }

    val billListViewModel: BillListViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val bills by billListViewModel.bills.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        billListViewModel.fetchBills(event)
        eventDetailViewModel.getUsersFromEvent(event) { usersListResult->
            users.addAll(usersListResult)
        }
    }

    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            EventScreenHeader(event, navController)
        }
    ) {
        var selectedOption by remember { mutableStateOf(EventScreenTabNavBarOption.DEBTS) }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            TabNavBar(
                EventScreenTabNavBarOption.entries.toTypedArray(),
                selectedOption = selectedOption,
                onOptionSelected = { option -> selectedOption = option }
            )
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
            ) {
                when(selectedOption) {
                    EventScreenTabNavBarOption.DEBTS -> { items(event.debts) { debt->
                        DebtListItem(event.status, debt, users) { newDebtStatus ->
                            debt.status = newDebtStatus
                            val shouldProceedToFinish = isAllDebtsPayed(event.debts)

                            eventDetailViewModel.updateDebts(event.eventId, event.debts) {
                                if(shouldProceedToFinish){
                                    eventDetailViewModel.updateEventStatus(event.eventId, EventStatus.FINISHED) {
                                        val dateFormater = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                        val finishDate = dateFormater.format(Calendar.getInstance().time)
                                        eventDetailViewModel.updateFinishDate(event.eventId,finishDate) {
                                            eventDetailViewModel.refreshEvent()
                                        }
                                    }
                                }
                            }
                        } }
                    }
                    EventScreenTabNavBarOption.BILLS -> { items(bills) { bill->
                        BillListItem(bill, navController) }
                    }
                }
            }
            BottomWhiteStrip {
                BlueButton(
                    onClick = { navController.navigate(MainNavScreen.AddBill.route) },
                    text = "Add Bill"
                )
            }

        }
    }
}

fun isAllDebtsPayed(debts: List<Debt>): Boolean {
    debts.forEach { debt ->
        if(debt.status != DebtStatus.PAYED) return false
    }
    return true
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EventScreenPreview(){
    SplitTheBillTheme {
        EventScreen(rememberNavController())
    }
}
