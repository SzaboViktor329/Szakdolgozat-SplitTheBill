package com.splitthebill.ui.screens.eventscreen.listitems

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.splitthebill.data.enums.DebtStatus
import com.splitthebill.data.enums.EventStatus
import com.splitthebill.data.models.User
import com.splitthebill.data.models.event.Debt
import com.splitthebill.ui.components.icons.ProfilePicture
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.utils.createMonogram
import com.splitthebill.ui.viewmodels.AuthViewModel


@Composable
fun DebtListItem(eventStatus: EventStatus, debt: Debt, users: List<User>, onDebtStatusChange: (DebtStatus)-> Unit) {
    val isChecked = remember { mutableStateOf(debt.status == DebtStatus.PAYED) }
    val authViewModel: AuthViewModel = hiltViewModel()
    val currentUID = remember { authViewModel.currentUserAuth.value?.uid ?: "" }

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),

        colors = CardDefaults.cardColors(containerColor = Color.White)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            ProfilePicture(
                placeholderText = createMonogram(users.find { it.uid == debt.fromUserId }?.fullName ?: ""),
                size = 40.dp
            )

            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))

            ProfilePicture(
                placeholderText = createMonogram(users.find { it.uid == debt.toUserId }?.fullName ?: ""),
                size = 40.dp
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(text = "${debt.amount} Ft", modifier = Modifier.align(Alignment.CenterVertically), fontSize = 24.sp)

            //Spacer(modifier = Modifier.width(8.dp))

            if(eventStatus == EventStatus.PAYING) {
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                        val debtStatus = if(isChecked.value) DebtStatus.PAYED else DebtStatus.PENDING
                        onDebtStatusChange(debtStatus)
                    },
                    modifier = Modifier.align(Alignment.CenterVertically),
                    enabled = (debt.fromUserId == currentUID || debt.toUserId == currentUID)
                )
            }
        }
    }
}




@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun DebtListItemPreview(){
    SplitTheBillTheme {
        DebtListItem(eventStatus = EventStatus.PENDING, Debt(), emptyList(), {})
    }
}