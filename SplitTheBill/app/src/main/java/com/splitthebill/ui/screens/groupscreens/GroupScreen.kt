package com.splitthebill.ui.screens.groupscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.common.BottomWhiteStrip
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.navigation.navscreens.MainNavScreen
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.GroupDetailsViewModel
import com.splitthebill.ui.viewmodels.GroupViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider

@Composable
fun GroupScreen(navController: NavHostController){
    val groupViewModel : GroupViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val groupDetailsViewModel : GroupDetailsViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val groups by groupViewModel.groups.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        groupViewModel.fetchGroups{}
    }

    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = { Text("Groups", style = typography.headlineLarge, color = Color.White, modifier = Modifier.padding(20.dp, 16.dp)) }
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 10.dp)
            ) {
                items(groups) { group ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable {
                                groupDetailsViewModel.groupId = group.groupId
                                groupDetailsViewModel.groupName = group.groupName
                                navController.navigate(MainNavScreen.GroupDetails.route)
                            },
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Text(group.groupName, style = typography.titleLarge, modifier = Modifier.padding(16.dp, 8.dp))
                    }
                }
            }
            BottomWhiteStrip {
                BlueButton(
                    onClick = { navController.navigate(MainNavScreen.CreateGroup.route) },
                    text = "Create group",
                    iconFontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth().padding(16.dp,0.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun GroupScreenPreview(){
    SplitTheBillTheme {
        GroupScreen(rememberNavController())
    }
}