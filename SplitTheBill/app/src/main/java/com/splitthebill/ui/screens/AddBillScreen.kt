package com.splitthebill.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.common.addbillcomponents.AddItemsComponent
import com.splitthebill.ui.common.addbillcomponents.AddPayersComponent
import com.splitthebill.ui.common.addbillcomponents.BasicBillInformationComponent
import com.splitthebill.ui.common.sharedcomponents.DatePickerModal
import com.splitthebill.ui.common.sharedcomponents.TitleBarWithBackButton
import com.splitthebill.ui.common.sharedcomponents.WeightedBoxLayout
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun AddBillScreen(navController: NavHostController) {
    var progressCounter by remember { mutableIntStateOf(0) }
    WeightedBoxLayout(
        topWeight = 1f,
        bottomWeight = 5f,
        topColor = BlueTheme,
        bottomColor = Color.White,
        mainColor = Color.LightGray,
        topContent = {
            TitleBarWithBackButton(navController) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text("Add bill", style = typography.headlineLarge, color = Color.White)
                }
            }
        }
    ) {
        when(progressCounter) {
            0 -> BasicBillInformationComponent { progressCounter = 1 }
            1 -> AddItemsComponent { progressCounter = 2 }
            2 -> AddPayersComponent { navController.popBackStack() }
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