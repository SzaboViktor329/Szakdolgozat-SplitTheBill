package com.splitthebill.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.navigation.navgraphs.MainNavGraph
import com.splitthebill.ui.navigation.navscreens.MainNavScreen
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme

@Composable
fun MainScreen(authNavController: NavHostController){
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
                            navController.navigate(MainNavScreen.Home.route)
                        },
                        modifier = Modifier.weight(1f)){
                        Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(36.dp),
                            tint = if(selected.value == Icons.Default.Home) BlueTheme else Color.DarkGray)
                    }
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Groups
                            navController.navigate(MainNavScreen.Group.route)
                        },
                        modifier = Modifier.weight(1f)){
                        Icon(Icons.Default.Groups, contentDescription = null, modifier = Modifier.size(36.dp),
                            tint = if(selected.value == Icons.Default.Groups) BlueTheme else Color.DarkGray)
                    }
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Person
                            navController.navigate(MainNavScreen.User.route)
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
        MainNavGraph(navController, authNavController, Modifier.padding(padding))
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun MainScreenPreview(){
    SplitTheBillTheme {
        MainScreen(rememberNavController())
    }
}

