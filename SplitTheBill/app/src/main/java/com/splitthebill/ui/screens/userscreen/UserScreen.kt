package com.splitthebill.ui.screens.userscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.splitthebill.data.enums.FriendRequestStatus
import com.splitthebill.data.models.UserWithRequest
import com.splitthebill.ui.screens.userscreen.modals.findfriendmodal.FindFriendModal
import com.splitthebill.ui.screens.userscreen.listitems.FriendListItem
import com.splitthebill.ui.screens.userscreen.listitems.FriendRequestListItem
import com.splitthebill.ui.components.buttons.BlueButton
import com.splitthebill.ui.components.common.BottomWhiteStrip
import com.splitthebill.ui.components.navbars.ComponentNavBar
import com.splitthebill.ui.components.buttons.DialogIconButton
import com.splitthebill.ui.components.layouts.HeaderContentLayout
import com.splitthebill.ui.components.icons.ProfilePicture
import com.splitthebill.ui.screens.userscreen.modals.UserSettingsModal
import com.splitthebill.ui.interfaces.ComponentNavBarOptionLabel
import com.splitthebill.ui.screens.userscreen.header.UserScreenHeader
import com.splitthebill.ui.theme.BlueTheme
import com.splitthebill.ui.theme.SplitTheBillTheme
import com.splitthebill.ui.viewmodels.FriendViewModel


enum class UserScreenComponentNavBarOption(override val label: String) : ComponentNavBarOptionLabel {
    FRIEND_REQUESTS("Friend Requests"),
    FRIENDS("Friends")
}

@Composable
fun UserScreen(authNavController: NavHostController, ){
    val friendViewModel: FriendViewModel = hiltViewModel()
    val usersWithRequests by friendViewModel.usersWithRequests.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        friendViewModel.fetchUsersWithFriendRequests()
    }

    HeaderContentLayout(
        topColor = BlueTheme,
        bottomColor = Color.LightGray,
        mainColor = Color.LightGray,
        topContent = { UserScreenHeader(authNavController) }
    ) {
        var selectedOption by remember { mutableStateOf(UserScreenComponentNavBarOption.FRIEND_REQUESTS) }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            ComponentNavBar(
                options = UserScreenComponentNavBarOption.entries.toTypedArray(),
                selectedOption = selectedOption,
                onOptionSelected = { option -> selectedOption = option }
            )
            when(selectedOption) {
                UserScreenComponentNavBarOption.FRIEND_REQUESTS -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
                    ) {
                        items(usersWithRequests) { userWithRequest ->
                            FriendRequestListItem(
                                userWithRequest,
                                onAccept = { friendRequest ->
                                    friendRequest.status = FriendRequestStatus.ACCEPTED
                                    friendViewModel.updateFriendRequest(friendRequest) { success->
                                        if(success) {
                                            println("update was successful")
                                            friendViewModel.fetchUsersWithFriendRequests()
                                        }
                                    }

                                },
                                onReject = { friendRequest ->
                                    friendRequest.status = FriendRequestStatus.REJECTED
                                    friendViewModel.updateFriendRequest(friendRequest) { success->
                                        if(success) {
                                            println("update was successful")
                                            friendViewModel.fetchUsersWithFriendRequests()
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
                UserScreenComponentNavBarOption.FRIENDS -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f).padding(top = 0.dp, start = 10.dp, end = 10.dp)
                    ) {
                        items(20) { FriendListItem() }
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