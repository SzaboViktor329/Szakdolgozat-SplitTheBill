package com.splitthebill.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme


@Composable
fun MainScreen(){
    val selected = remember { mutableStateOf(Icons.Default.Home) }
    val navController = rememberNavController()

    Scaffold(containerColor = BlueTheme,
        bottomBar = {
            Column {
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                BottomAppBar(containerColor = Color.White) {
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Home
                            navController.navigate("HomeScreen")
                        },
                        modifier = Modifier.weight(1f)){
                        Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(36.dp),
                            tint = if(selected.value == Icons.Default.Home) BlueTheme else Color.DarkGray)
                    }
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Person
                            navController.navigate("UserScreen")
                        },
                        modifier = Modifier.weight(1f)){
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(36.dp),
                            tint = if(selected.value == Icons.Default.Person) BlueTheme else Color.DarkGray)
                    }
                }
            }

        }
    ) { paddingValues ->
        val padding = PaddingValues(
            top = 0.dp,
            bottom = paddingValues.calculateBottomPadding(),
            start = 0.dp, end = 0.dp
        )
        NavHost(navController = navController, startDestination = "HomeScreen", modifier = Modifier.padding(padding)){
            composable("HomeScreen") { HomeScreen(navController) }
            composable("UserScreen") { UserScreen() }
            composable("EventCollectionScreen") { EventCollectionScreen(navController) }
            composable("EventScreen") { EventScreen(navController) }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun MainScreenPreview(){
    SplitTheBillTheme {
        MainScreen()
    }
}

