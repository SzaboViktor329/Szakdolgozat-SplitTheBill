package com.splitthebill.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.splitthebill.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    private val _currentUserAuth = mutableStateOf(authRepository.getCurrentUser())
    val currentUserAuth: State<FirebaseUser?> = _currentUserAuth

    fun login(email: String, password: String, onComplete: (message: String) ->Unit) {
        authRepository.login(email, password, onComplete)
    }

    fun logout() {
        authRepository.logout()
        _currentUserAuth.value = null
    }

    fun getEmail() : String {
        return _currentUserAuth.value?.email ?: "empty"
    }

}