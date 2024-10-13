package com.splitthebill.ui.common.sharedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.interfaces.ComponentNavBarOptionLabel

@Composable
fun <T : ComponentNavBarOptionLabel> ComponentNavBar(
    options: Array<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit
) {

    Row(Modifier.fillMaxWidth().background(Color.White).padding(5.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        options.forEach { option ->
            val isSelected = (option == selectedOption)
            Text(
                text = option.label, // or option.label if you have a label in the enum
                modifier = Modifier
                    .then(if (isSelected) Modifier.border(2.dp, Color.Black, RoundedCornerShape(10.dp)) else Modifier)
                    .padding(5.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { onOptionSelected(option) }
                    )
            )
        }
        /*
        Text("Friend requests", modifier = Modifier.then(if(showFriendRequests) Modifier.border(2.dp, Color.Black, RoundedCornerShape(10.dp)) else Modifier).padding(5.dp)
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {
                showFriendRequests = true
            })
        )
        Text("Friends", modifier = Modifier.then(if(!showFriendRequests) Modifier.border(2.dp, Color.Black, RoundedCornerShape(10.dp)) else Modifier).padding(5.dp)
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {
                showFriendRequests = false
            })
        )

         */
    }
}