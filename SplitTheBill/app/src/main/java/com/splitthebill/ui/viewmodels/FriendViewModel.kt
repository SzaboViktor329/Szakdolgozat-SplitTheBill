package com.splitthebill.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.splitthebill.data.models.FriendRequest
import com.splitthebill.data.repositories.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(private val friendRepository: FriendRepository) : ViewModel() {
    fun createFriendRequest(friendRequest: FriendRequest, onComplete: (friendRequestExist: Boolean)-> Unit) {
        friendRepository.createFriendRequest(friendRequest, onComplete)
    }
}