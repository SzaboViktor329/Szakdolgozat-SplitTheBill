package com.splitthebill.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splitthebill.data.models.FriendRequest
import com.splitthebill.data.models.UserWithRequest
import com.splitthebill.data.repositories.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(private val friendRepository: FriendRepository) : ViewModel() {
    private val _usersWithRequests = MutableLiveData<List<UserWithRequest>>(emptyList())
    val usersWithRequests: LiveData<List<UserWithRequest>> = _usersWithRequests

    init {
        viewModelScope.launch {
            fetchUsersWithFriendRequests()
        }
    }

    fun createFriendRequest(friendRequest: FriendRequest, onComplete: (friendRequestExist: Boolean)-> Unit) {
        friendRepository.createFriendRequest(friendRequest, onComplete)
    }

    private fun fetchUsersWithFriendRequests() {
        friendRepository.fetchUsersWithFriendRequests { usersWithRequestsResult->
            _usersWithRequests.value = usersWithRequestsResult
        }
    }


}