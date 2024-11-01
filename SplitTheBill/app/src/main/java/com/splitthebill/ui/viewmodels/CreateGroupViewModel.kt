package com.splitthebill.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.splitthebill.data.models.Group
import com.splitthebill.data.repositories.FriendRepository
import com.splitthebill.data.repositories.GroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateGroupViewModel @Inject constructor(private val groupRepository: GroupRepository) : ViewModel() {

    fun createGroup(group: Group, onSuccess: () -> Unit) {
        groupRepository.createGroup(group, onSuccess)
    }
}