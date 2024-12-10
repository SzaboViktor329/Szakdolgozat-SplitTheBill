package com.splitthebill.ui.screens.addbillscreen.views.addpayersview.listitems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.data.models.User
import com.splitthebill.data.models.bill.Payer
import com.splitthebill.ui.components.icons.ProfilePicture
import com.splitthebill.ui.screens.addbillscreen.UserPickerModal
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.utils.createMonogram

@Composable
fun AddPayerListItem(
    payer: Payer,
    users: List<User>,
    onDestroy: () -> Unit
) {

    var amountText by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableDoubleStateOf(0.0) }

    var user by remember { mutableStateOf(User()) }
    var userId by rememberSaveable { mutableStateOf("") }

    var showUserPickerModal by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        users.forEach { userInList ->
            if(userInList.uid == userId) user = userInList
        }
    }
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(2.dp,Color.Black)
    )
    {

        Box(Modifier.fillMaxWidth().padding(top = 6.dp), contentAlignment = Alignment.TopEnd){
            IconButton(onClick = { onDestroy() }, modifier = Modifier.padding(end = 8.dp)) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            ProfilePicture(
                modifier = Modifier.clickable {
                    showUserPickerModal = true
                },
                placeholderText = createMonogram(user.fullName),
                size = 50.dp
            )
            OutlinedTextField(
                value = amountText,
                onValueChange = {
                    val parsedAmount = it.toDoubleOrNull()
                    if(parsedAmount != null) {
                        amountText = it
                        amount = parsedAmount
                        payer.amount = parsedAmount
                    }
                },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
    if(showUserPickerModal) UserPickerModal(users) { selectedUser->
        userId = selectedUser.uid
        payer.userId = selectedUser.uid
        user = selectedUser
        showUserPickerModal = false
    }
}

@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun PayerItemPreview(){
    SplitTheBillTheme {
        AddPayerListItem(Payer(), listOf(),{})
    }
}