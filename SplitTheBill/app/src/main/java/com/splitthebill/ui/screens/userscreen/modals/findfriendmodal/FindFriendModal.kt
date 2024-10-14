package com.splitthebill.ui.screens.userscreen.modals.findfriendmodal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.screens.userscreen.modals.findfriendmodal.listitems.FindFriendListItem
import com.splitthebill.ui.components.templates.DialogWithTitle
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun FindFriendModal(onDismiss: () -> Unit) {
    DialogWithTitle("",onDismiss) {
        var showResults by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth().wrapContentHeight().padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                placeholder = { Text("Friend's @username") }
            )
            Spacer(Modifier.height(4.dp))
            if(showResults) {
                LazyColumn {
                    items(1) {
                        FindFriendListItem { onDismiss() }
                    }
                }
                Spacer(Modifier.height(4.dp))
            }
            Button(
                onClick = { showResults = !showResults },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueTheme
                )) {
                Text("Search ")
                Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun FindFriendModalPreview(){
    SplitTheBillTheme {
        FindFriendModal {}
    }
}