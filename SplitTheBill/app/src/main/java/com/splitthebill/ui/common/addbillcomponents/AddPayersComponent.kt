package com.splitthebill.ui.common.addbillcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun AddPayersComponent(
    onProceed: () -> Unit
) {
    var payerCount by remember { mutableIntStateOf(1) }
    val listState = rememberLazyListState()

    LaunchedEffect(payerCount) {
        if(payerCount > 0){
            listState.animateScrollToItem(payerCount-1)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.Start) {
        Text("Add payers", style = typography.titleLarge)
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f), state = listState) {
            items(payerCount) {
                PayerItem { payerCount-- }
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp ,bottom = 0.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { payerCount++ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueTheme
                )
            ) {
                Text("New payer")
            }
            Button(
                onClick = { onProceed() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueTheme
                )
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun AddPayersComponentPreview(){
    SplitTheBillTheme {
        AddPayersComponent {}
    }
}