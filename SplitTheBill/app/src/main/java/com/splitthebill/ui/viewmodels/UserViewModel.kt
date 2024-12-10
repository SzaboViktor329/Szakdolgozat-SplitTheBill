package com.splitthebill.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splitthebill.data.models.User
import com.splitthebill.data.repositories.AuthRepository
import com.splitthebill.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository, private val authRepository: AuthRepository) : ViewModel() {
    private val _currentUserState = mutableStateOf(User())
    val currentUser: User get() = _currentUserState.value
    private val _emailState = mutableStateOf("")
    val email: String get() = _emailState.value

    init {
        viewModelScope.launch {
            _currentUserState.value = initCurrentUser()
            _emailState.value = authRepository.getCurrentUser()?.email ?: ""
        }
    }

    private suspend fun initCurrentUser(): User {
        return userRepository.getUser(authRepository.getCurrentUser()!!.uid) ?: User()
    }

    fun searchUserByUsername(username: String, onComplete: (success: Boolean, resultUser: User?) -> Unit) {
        userRepository.getUserByUsername(username) { userResult->
            if(userResult == null) onComplete (false, null)
            else if (userResult.uid == currentUser.uid) onComplete(false, null)
            else onComplete(true, userResult)
        }
    }
}