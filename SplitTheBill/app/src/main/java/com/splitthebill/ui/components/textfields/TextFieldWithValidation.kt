package com.splitthebill.ui.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun TextFieldWithValidation(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    validationMessage: String
){
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = validationMessage,
            color = Color.Red,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(5.dp, 5.dp, 0.dp, 0.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    SplitTheBillTheme {
        TextFieldWithValidation("Test",{},"Test email","email error")
    }
}