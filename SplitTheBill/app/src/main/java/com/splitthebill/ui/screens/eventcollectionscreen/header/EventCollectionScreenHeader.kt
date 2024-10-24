package com.splitthebill.ui.screens.eventcollectionscreen.header

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.splitthebill.ui.components.buttons.DialogIconButton
import com.splitthebill.ui.components.dialogs.FilterOptionsDialog
import com.splitthebill.ui.components.templates.HeaderWithBackButton

@Composable
fun EventCollectionScreenHeader(navController: NavHostController) {
    HeaderWithBackButton(navController) {
        Box(modifier =  Modifier.weight(1f).wrapContentHeight().padding(0.dp,16.dp), contentAlignment = Alignment.Center){
            Text(
                text = "Events",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
        }
        DialogIconButton(
            modifier = Modifier.padding(end = 4.dp).border(2.dp, Color.White),
            iconImageVector = Icons.Default.FilterList,
            iconContentDescription = "Filter",
            iconModifier = Modifier.size(30.dp)
        ) { onDismiss ->
            FilterOptionsDialog(onDismiss)
        }
    }
}