package com.splitthebill.ui.navigation.navscreens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.splitthebill.ui.screens.CreateGroupScreen
import com.splitthebill.ui.screens.GroupDetailsScreen
import com.splitthebill.ui.screens.GroupScreen
import com.splitthebill.ui.screens.addbillscreen.AddBillScreen
import com.splitthebill.ui.screens.billscreen.BillScreen
import com.splitthebill.ui.screens.eventcollectionscreen.EventCollectionScreen
import com.splitthebill.ui.screens.eventscreen.EventScreen
import com.splitthebill.ui.screens.homescreen.HomeScreen
import com.splitthebill.ui.screens.userscreen.UserScreen

//Stores the routes to the main screens and the actual screens.
enum class MainNavScreen(val route: String, val content: @Composable (NavHostController) -> Unit) {
    Home("Home", { navHostController -> HomeScreen(navHostController) }),
    User("User", { navHostController -> UserScreen(navHostController) }),
    EventCollection("EventCollection", { navHostController -> EventCollectionScreen(navHostController) }),
    Event("Event", { navHostController -> EventScreen(navHostController) }),
    Bill("Bill", { navHostController -> BillScreen(navHostController) }),
    AddBill("AddBill", { navHostController -> AddBillScreen(navHostController) }),
    Group("Group", { navHostController -> GroupScreen(navHostController) }),
    GroupDetails("GroupDetails", { navHostController -> GroupDetailsScreen(navHostController) }),
    CreateGroup("CreateGroup", { navHostController -> CreateGroupScreen(navHostController) })
}