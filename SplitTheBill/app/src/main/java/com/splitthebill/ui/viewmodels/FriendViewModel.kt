package com.splitthebill.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splitthebill.data.models.User
import com.splitthebill.data.repositories.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(private val friendRepository: FriendRepository) : ViewModel() {
    private val _friends = MutableStateFlow<List<User>>(emptyList())
    val friends: StateFlow<List<User>> = _friends

    init {
        viewModelScope.launch {
            friendRepository.fetchFriends().collect { friendsList->
                _friends.value = friendsList
            }
        }
    }
}