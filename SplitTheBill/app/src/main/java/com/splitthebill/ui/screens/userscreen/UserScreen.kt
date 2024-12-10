package com.splitthebill.ui.screens.userscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.data.enums.FriendRequestStatus
import com.splitthebill.ui.screens.userscreen.modals.findfriendmodal.FindFriendModal
import com.splitthebill.ui.screens.userscreen.listitems.FriendListItem
import com.splitthebill.ui.screens.userscreen.listitems.FriendRequestListItem
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.common.BottomWhiteStrip
import com.splitthebill.ui.components.navbars.TabNavBar
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.components.navbars.TabNavBarOptionLabel
import com.splitthebill.ui.screens.userscreen.header.UserScreenHeader
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.FriendRequestViewModel
import com.splitthebill.ui.viewmodels.FriendViewModel
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider


enum class UserScreenTabNavBarOption(override val label: String) : TabNavBarOptionLabel {
    FRIEND_REQUESTS("Friend Requests"),
    FRIENDS("Friends")
}

@Composable
fun UserScreen(authNavController: NavHostController){
    val friendRequestViewModel: FriendRequestViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val friendViewModel: FriendViewModel = hiltViewModel(ViewModelScopeProvider.mainNavStoreOwner!!)
    val usersWithRequests by friendRequestViewModel.usersWithRequests.collectAsState()
    val friends by friendViewModel.friends.collectAsState()

    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = { UserScreenHeader(authNavController) }
    ) {
        var selectedOption by remember { mutableStateOf(UserScreenTabNavBarOption.FRIEND_REQUESTS) }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            TabNavBar(
                options = UserScreenTabNavBarOption.entries.toTypedArray(),
                selectedOption = selectedOption,
                onOptionSelected = { option -> selectedOption = option }
            )
            when(selectedOption) {
                UserScreenTabNavBarOption.FRIEND_REQUESTS -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
                    ) {
                        items(usersWithRequests) { userWithRequest ->
                            FriendRequestListItem(
                                userWithRequest,
                                onAccept = { friendRequest ->
                                    friendRequest.status = FriendRequestStatus.ACCEPTED
                                    friendRequestViewModel.updateFriendRequest(friendRequest) { success->
                                        if(success) {
                                            println("update was successful")
                                        }
                                    }
                                },
                                onReject = { friendRequest ->
                                    friendRequest.status = FriendRequestStatus.REJECTED
                                    friendRequestViewModel.updateFriendRequest(friendRequest) { success->
                                        if(success) {
                                            println("update was successful")
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
                UserScreenTabNavBarOption.FRIENDS -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
                    ) {
                        items(friends) { friend ->
                            FriendListItem(friend)
                        }
                    }
                }
            }

            var showFindFriendDialog by remember { mutableStateOf(false) }
            BottomWhiteStrip {
                BlueButton(
                    onClick = { showFindFriendDialog = !showFindFriendDialog },
                    text = "Find friend ",
                    iconImageVector = Icons.Default.Search,
                    iconModifier = Modifier.size(20.dp)
                )
            }
            if(showFindFriendDialog) {
                FindFriendModal { showFindFriendDialog = false }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun UserScreenPreview(){
    SplitTheBillTheme {
        UserScreen(rememberNavController())
    }
}