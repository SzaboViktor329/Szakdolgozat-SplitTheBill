package com.splitthebill.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.common.eventcomponents.DetailedEventListItem
import com.splitthebill.ui.common.sharedcomponents.TitleBarWithBackButton
import com.splitthebill.ui.common.sharedcomponents.HeaderContentLayout
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import kotlin.random.Random

@Composable
fun EventCollectionScreen(navController: NavHostController) {
    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            EventsTitleBar(navController)
        },
        bottomContent = {
            Column {
                LazyColumn(
                    modifier = Modifier.weight(1f).padding(top = 10.dp, start = 10.dp, end = 10.dp)
                ) {
                    items(20) {
                        DetailedEventListItem("Event name","2022.02.20", "2022.04.04", Random.nextInt(0, 3), navController)
                    }
                }
                Box(modifier = Modifier.fillMaxWidth().padding(0.dp,16.dp), contentAlignment = Alignment.Center) {
                    Button(onClick = {},
                        modifier = Modifier.fillMaxWidth().padding(16.dp,0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlueTheme
                        )
                    ) {
                        Text("Add event", fontSize = 16.sp)
                    }
                }
            }
        }
    )
}


@Composable
fun EventsTitleBar(navController: NavHostController) {
    TitleBarWithBackButton(navController) {
        Box(modifier =  Modifier.weight(1f).wrapContentHeight().padding(0.dp,16.dp), contentAlignment = Alignment.Center){
            Text(
                text = "Events",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
        }
        var showDialog by remember { mutableStateOf(false) }
            IconButton(
                onClick = { showDialog = true },
                modifier = Modifier.padding(end = 4.dp).border(2.dp, Color.White)
            ) {
                Icon(Icons.Default.FilterList, contentDescription = "Filter", tint = Color.White, modifier = Modifier.size(30.dp))
            }
            if (showDialog) {
                FilterOptionsDialog(onDismiss = { showDialog = false })
            }

    }
}

@Composable
fun FilterOptionsDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Select Filter Options", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

                var selectedPending by remember { mutableStateOf(true) }
                var selectedPaying by remember { mutableStateOf(true) }
                var selectedSettled by remember { mutableStateOf(true) }

                // Example filter options
                FilterOption("Pending",Icons.Default.PendingActions,selectedPending) {
                    selectedPending = !selectedPending
                }
                FilterOption("Paying",Icons.Default.Payments,selectedPaying) {
                    selectedPaying = !selectedPaying
                }
                FilterOption("Settled",Icons.Default.Check,selectedSettled) {
                    selectedSettled = !selectedSettled
                }


                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BlueTheme
                    )
                ) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun FilterOption(label: String, icon: ImageVector, isChecked: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onClick() },
            colors = CheckboxDefaults.colors(
                checkedColor = BlueTheme
            )
        )
        Icon(icon, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
        Text(text = label, fontSize = 16.sp)
    }
}



@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EventCollectionScreenPreview(){
    SplitTheBillTheme {
        EventCollectionScreen(rememberNavController())
    }
}