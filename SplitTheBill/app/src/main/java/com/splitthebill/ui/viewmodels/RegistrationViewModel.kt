package com.splitthebill.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.splitthebill.data.models.User
import com.splitthebill.data.repositories.RegistrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val registrationRepository: RegistrationRepository) : ViewModel() {
    fun register(email: String, password: String, user: User, onComplete: (message: String) ->Unit) {
        registrationRepository.register(email, password, user, onComplete)
    }
}