package com.splitthebill.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splitthebill.data.models.Group
import com.splitthebill.data.repositories.GroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(private val groupRepository: GroupRepository) : ViewModel() {
    private val _groups = MutableLiveData<List<Group>>(emptyList())
    val groups: LiveData<List<Group>> = _groups

    fun fetchGroups(onComplete: () -> Unit) {
        groupRepository.getGroups { groupsResults->
            _groups.value = groupsResults
            onComplete()
        }
    }
}