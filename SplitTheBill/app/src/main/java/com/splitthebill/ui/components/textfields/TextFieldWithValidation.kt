package com.splitthebill.ui.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun TextFieldWithValidation(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    validationMessage: String,
    onValidation: (Int) -> Unit,
    validationFunction: (String) -> Boolean

){
    var isValid by remember { mutableStateOf(false) }
    var isValidOnFocusChange by remember { mutableStateOf(false) }
    var valueOnFocusChange by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = value,
            onValueChange = { newValue->
                onValueChange(newValue)
                val previousIsValid = isValid
                isValid = validationFunction(newValue) && newValue != ""
                if((!previousIsValid && !isValid) || (previousIsValid && isValid)) onValidation(0)
                else if(isValid) onValidation(1)
                else onValidation(-1)
            },
            placeholder = { Text(placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if(!focusState.isFocused){
                        valueOnFocusChange = value
                        isValidOnFocusChange = validationFunction(value)
                    }
                }
        )
        Text(
            text = validationMessage,
            color = Color.Red,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(5.dp, 5.dp, 0.dp, 0.dp)
                .alpha (if(!isValidOnFocusChange && valueOnFocusChange != "") 1f else 0f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    SplitTheBillTheme {
        TextFieldWithValidation("Test",{},"Test email","email error",{},{true})
    }
}