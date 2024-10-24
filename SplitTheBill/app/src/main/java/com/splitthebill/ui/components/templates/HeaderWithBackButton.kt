package com.splitthebill.ui.components.templates

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun HeaderWithBackButton(
    navController: NavHostController,
    contents: @Composable RowScope.() ->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Top).border(2.dp,
                Color.White, RoundedCornerShape(5.dp)
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Row(modifier = Modifier.wrapContentHeight().weight(1f), verticalAlignment = Alignment.CenterVertically) {
            contents()
        }

    }
}

@Preview(showBackground = false, widthDp = 360, heightDp = 640)
@Composable
fun TitleBarWithBackButtonPreview(){
    SplitTheBillTheme {
        HeaderWithBackButton(rememberNavController()){

        }
    }
}