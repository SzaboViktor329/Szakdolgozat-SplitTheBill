package com.splitthebill.ui.screens.addbillscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.screens.addbillscreen.views.additemsview.AddItemsView
import com.splitthebill.ui.screens.addbillscreen.views.addpayersview.AddPayersView
import com.splitthebill.ui.screens.addbillscreen.views.GeneralBillInformationView
import com.splitthebill.ui.components.templates.HeaderWithBackButton
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun AddBillScreen(navController: NavHostController) {
    var progressCounter by remember { mutableIntStateOf(0) }
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.White,
        mainColor = Color.LightGray,
        topContent = {
            HeaderWithBackButton(navController) {
                Box(Modifier.fillMaxWidth().padding(0.dp, 16.dp), contentAlignment = Alignment.Center){
                    Text("Add bill", style = typography.headlineLarge, color = Color.White)
                }
            }
        }
    ) {
        when(progressCounter) {
            0 -> GeneralBillInformationView { progressCounter = 1 }
            1 -> AddItemsView { progressCounter = 2 }
            2 -> AddPayersView { navController.popBackStack() }
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