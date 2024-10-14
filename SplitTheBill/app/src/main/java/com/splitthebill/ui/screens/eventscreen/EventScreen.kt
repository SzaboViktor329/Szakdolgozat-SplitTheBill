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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import kotlin.random.Random


enum class EventScreenComponentNavBarOption(override val label: String) : ComponentNavBarOptionLabel {
    DEBTS("Debts"),
    BILLS("Bills")
}


@Composable
fun EventScreen(navController: NavHostController) {
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            EventTitleBar("Event name", "2022.02.02", "2023.03.03", navController)
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
                    EventScreenComponentNavBarOption.BILLS -> { items(20) { BillListItem(navController) } }
                }
            }
            BottomWhiteStrip {
                BlueButton(
                    onClick = { navController.navigate("AddBillScreen") },
                    text = "Add Bill"
                )
            }

        }
    }
}

@Composable
fun EventTitleBar(eventName: String, startDate: String, endDate: String, navController: NavHostController){
    HeaderWithBackButton(navController) {
        Box(modifier = Modifier.weight(1f).wrapContentHeight()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = eventName, style = typography.headlineLarge, color = Color.White)
                Text(text = "$startDate - $endDate", style = typography.titleMedium, color = Color.White)
            }
        }
        DialogIconButton(
            modifier = Modifier.padding(end = 4.dp).clip(RoundedCornerShape(15.dp)).background(Color.White),
            iconImageVector = when(Random.nextInt(0,3)){
                0 -> Icons.Default.Check
                1 -> Icons.Default.Payments
                2 -> Icons.Default.PendingActions
                else -> Icons.Default.Android
            },
            iconColor = Color.Black,
            iconContentDescription = "Event Status",
            iconModifier = Modifier.size(30.dp),
        ) { }
    }

}



@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EventScreenPreview(){
    SplitTheBillTheme {
        EventScreen(rememberNavController())
    }
}
