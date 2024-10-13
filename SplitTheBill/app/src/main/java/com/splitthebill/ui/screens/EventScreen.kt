package com.splitthebill.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.splitthebill.ui.common.eventcomponents.BillListItem
import com.splitthebill.ui.common.eventcomponents.DebtListItem
import com.splitthebill.ui.common.friendcomponents.FriendListItem
import com.splitthebill.ui.common.friendcomponents.FriendRequestListItem
import com.splitthebill.ui.common.sharedcomponents.ComponentNavBar
import com.splitthebill.ui.common.sharedcomponents.DialogIconButton
import com.splitthebill.ui.common.sharedcomponents.TitleBarWithBackButton
import com.splitthebill.ui.common.sharedcomponents.HeaderContentLayout
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
            Box(Modifier.fillMaxWidth().wrapContentHeight().background(Color.White)){
                Row(
                    modifier = Modifier.fillMaxWidth().padding(25.dp,10.dp,25.dp,10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { navController.navigate("AddBillScreen") },
                        colors = ButtonDefaults.buttonColors(
                        containerColor = BlueTheme
                    )) {
                        Text("Add Bill")
                    }
                }
            }

        }
    }
}

@Composable
fun EventTitleBar(eventName: String, startDate: String, endDate: String, navController: NavHostController){
    TitleBarWithBackButton(navController) {
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
