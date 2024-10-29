package com.splitthebill.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splitthebill.data.models.FriendRequest
import com.splitthebill.data.models.User
import com.splitthebill.data.models.UserWithRequest
import com.splitthebill.data.repositories.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(private val friendRepository: FriendRepository) : ViewModel() {
    private val _usersWithRequests = MutableStateFlow<List<UserWithRequest>>(emptyList())
    val usersWithRequests: StateFlow<List<UserWithRequest>> = _usersWithRequests

    private val _friends = MutableStateFlow<List<User>>(emptyList())
    val friends: StateFlow<List<User>> = _friends



    init {
        viewModelScope.launch {
            /*
            friendRepository.fetchUsersWithFriendRequests().collect { usersWithRequestsList->
                _usersWithRequests.value = usersWithRequestsList
            }

             */
            friendRepository.fetchFriends().collect { friendsList->
                _friends.value = friendsList

            }

        }
    }

    fun createFriendRequest(friendRequest: FriendRequest, onComplete: (friendRequestExist: Boolean)-> Unit) {
        friendRepository.createFriendRequest(friendRequest, onComplete)
    }

    fun updateFriendRequest(friendRequest: FriendRequest, onComplete: (Boolean)-> Unit) {
        friendRepository.updateFriendRequest(friendRequest, onComplete)
    }

    fun stopListening() {
        friendRepository.stopListening()
    }


}