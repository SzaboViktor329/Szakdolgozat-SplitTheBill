package com.splitthebill.ui.screens.eventscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.MaterialTheme.typography
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.screens.eventscreen.listitems.BillListItem
import com.splitthebill.ui.screens.eventscreen.listitems.DebtListItem
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.common.BottomWhiteStrip
import com.splitthebill.ui.components.navbars.ComponentNavBar
import com.splitthebill.ui.components.buttons.DialogIconButton
import com.splitthebill.ui.components.templates.HeaderWithBackButton
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.interfaces.ComponentNavBarOptionLabel
import com.splitthebill.ui.navigation.navscreens.MainNavScreen
import com.splitthebill.ui.screens.eventscreen.header.EventScreenHeader
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.BillListViewModel
import com.splitthebill.ui.viewmodels.EventDetailViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider
import kotlin.random.Random


enum class EventScreenComponentNavBarOption(override val label: String) : ComponentNavBarOptionLabel {
    DEBTS("Debts"),
    BILLS("Bills")
}


@Composable
fun EventScreen(navController: NavHostController) {
    val eventDetailViewModel: EventDetailViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val event = remember { eventDetailViewModel.event.value }

    val billListViewModel: BillListViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val bills by billListViewModel.bills.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        billListViewModel.fetchBills(event)
    }

    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            EventScreenHeader(event.eventName, event.status ,event.startDate, event.finishDate, navController)
        }
    ) {
        var selectedOption by remember { mutableStateOf(EventScreenComponentNavBarOption.DEBTS) }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            ComponentNavBar(
                EventScreenComponentNavBarOption.entries.toTypedArray(),
                selectedOption = selectedOption,
                onOptionSelected = { option -> selectedOption = option }
            )
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
            ) {
                when(selectedOption) {
                    EventScreenComponentNavBarOption.DEBTS -> { items(20) { DebtListItem() } }
                    EventScreenComponentNavBarOption.BILLS -> { items(bills) { bill->
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



@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EventScreenPreview(){
    SplitTheBillTheme {
        EventScreen(rememberNavController())
    }
}
