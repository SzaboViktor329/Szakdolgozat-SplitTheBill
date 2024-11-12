package com.splitthebill.ui.screens.billscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.screens.billscreen.listitems.ItemsListItem
import com.splitthebill.ui.screens.billscreen.listitems.PayerListItem
import com.splitthebill.ui.components.navbars.ComponentNavBar
import com.splitthebill.ui.components.templates.HeaderWithBackButton
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.interfaces.ComponentNavBarOptionLabel
import com.splitthebill.ui.screens.billscreen.header.BillScreenHeader
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.BillViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider

enum class BillScreenComponentNavBarOption(override val label: String) : ComponentNavBarOptionLabel {
    PAYERS("Payers"),
    ITEMS("Items")
}

@Composable
fun BillScreen(navController: NavHostController) {
    val billViewModel: BillViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val bill = remember { billViewModel.bill.value }

    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            BillScreenHeader(bill.billName, bill.date, bill.total, navController)
        }
    ) {
        var selectedOption by remember { mutableStateOf(BillScreenComponentNavBarOption.PAYERS) }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            ComponentNavBar(
                BillScreenComponentNavBarOption.entries.toTypedArray(),
                selectedOption = selectedOption,
                onOptionSelected = { option -> selectedOption = option }
            )
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
            ) {
                when(selectedOption) {
                    BillScreenComponentNavBarOption.PAYERS -> { items(bill.payers) { payer ->
                        PayerListItem(payer) }
                    }
                    BillScreenComponentNavBarOption.ITEMS -> { items(bill.items) { item ->
                        ItemsListItem(item) }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun BillScreenPreview(){
    SplitTheBillTheme {
        BillScreen(rememberNavController())
    }
}