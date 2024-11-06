package com.splitthebill.ui.screens.addbillscreen.views.additemsview

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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.data.models.User
import com.splitthebill.data.models.bill.Item
import com.splitthebill.ui.screens.addbillscreen.views.additemsview.listitems.AddBillListItem
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun AddItemsView(
    users: List<User>,
    onProceed: (Double, List<Item>) -> Unit
) {
    var itemCount by remember { mutableIntStateOf(1) }
    val items = remember { mutableStateListOf(Item()) }
    val listState = rememberLazyListState()

    var total by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(itemCount) {
        if(itemCount > 0){
            listState.animateScrollToItem(itemCount-1)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.Start) {
        Text("Add items", style = typography.titleLarge)
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f), state = listState) {
            items(items) { item ->
                AddBillListItem(item, users) {
                    items.remove(item)
                    itemCount--
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp ,bottom = 0.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            BlueButton(
                onClick = {
                    items.add(Item())
                    itemCount++
                },
                text = "New item"
            )
            BlueButton(
                onClick = {
                    total = 0.0
                    items.forEach { item ->
                        total += item.price
                    }
                    onProceed(total ,items)
                },
                text = "Continue"
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun AddItemsComponentPreview(){
    SplitTheBillTheme {
        AddItemsView(listOf(),{Total, list ->})
    }
}