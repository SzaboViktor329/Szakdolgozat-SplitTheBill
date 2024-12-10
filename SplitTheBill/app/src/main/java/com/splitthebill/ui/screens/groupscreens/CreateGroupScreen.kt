package com.splitthebill.ui.screens.groupscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.data.models.Group
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.common.BottomWhiteStrip
import com.splitthebill.ui.components.common.FriendNameWithIcon
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.components.templates.HeaderWithBackButton
import com.splitthebill.ui.navigation.navscreens.MainNavScreen
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.AuthViewModel
import com.splitthebill.ui.viewmodels.CreateGroupViewModel
import com.splitthebill.ui.viewmodels.FriendViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider

@Composable
fun CreateGroupScreen(navController: NavHostController) {
    var groupName by remember { mutableStateOf("") }
    val selectedUIDs = remember { mutableListOf<String>() }
    val friendViewModel: FriendViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val createGroupViewModel: CreateGroupViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()
    val friends by friendViewModel.friends.collectAsState()

    LaunchedEffect(Unit) {
        selectedUIDs.add(authViewModel.currentUserAuth.value!!.uid)
    }

    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = {
            HeaderWithBackButton(navController) {
                Text("Create group", style = typography.headlineLarge, color = Color.White, modifier = Modifier.padding(20.dp, 10.dp))
            }
        }
    ) {
        Column {
            Box(Modifier.fillMaxWidth().background(Color.White)) {
                OutlinedTextField(
                    value = groupName,
                    onValueChange = { groupName = it },
                    placeholder = { Text("Group name") },
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                )
            }
            Spacer(Modifier.height(4.dp))
            LazyColumn(
                modifier = Modifier.weight(1f).padding(top = 10.dp)
            ) {
                items(friends) { friend ->
                    val isChecked = remember { mutableStateOf(false) }
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            FriendNameWithIcon(Modifier.weight(1f).padding(end = 20.dp), friend ,Arrangement.SpaceBetween)
                            Checkbox(
                                checked = isChecked.value,
                                onCheckedChange = {
                                    isChecked.value = it
                                    if(isChecked.value) selectedUIDs.add(friend.uid)
                                    else selectedUIDs.remove(friend.uid)
                                },
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
            BottomWhiteStrip {
                BlueButton(
                    onClick = {
                        if(groupName != "" && selectedUIDs.size > 0) {
                            createGroupViewModel.createGroup(
                                Group(
                                    groupName = groupName,
                                    userIds = selectedUIDs
                                )
                            ) {
                                navController.navigate(MainNavScreen.Group.route)
                            }

                        }
                    },
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
fun CreateGroupScreenPreview(){
    SplitTheBillTheme {
        CreateGroupScreen(rememberNavController())
    }
}