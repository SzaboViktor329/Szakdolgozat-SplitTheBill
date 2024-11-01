package com.splitthebill.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.splitthebill.data.models.User
import com.splitthebill.data.repositories.GroupRepository
import com.splitthebill.ui.viewmodels.scopeprovider.ViewModelScopeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GroupDetailsViewModel @Inject constructor(private val groupRepository: GroupRepository) : ViewModel() {
    var groupId = ""
    var groupName = ""

    private val _usersFromGroup = MutableStateFlow<List<User>>(emptyList())
    val usersFromGroup: StateFlow<List<User>> = _usersFromGroup

    fun fetchUsersFromGroup() {
        groupRepository.getUsersFromGroup(groupId) { usersFromGroupResults->
            _usersFromGroup.value = usersFromGroupResults
        }
    }

}