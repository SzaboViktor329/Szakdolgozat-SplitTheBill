package com.splitthebill.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.splitthebill.data.models.Event
import com.splitthebill.data.models.User
import com.splitthebill.data.models.bill.Bill
import com.splitthebill.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _bill = mutableStateOf(Bill())
    val bill: State<Bill> = _bill

    fun setBill(newBill: Bill) {
        _bill.value = newBill
    }

    suspend fun getUserById(uid: String) : User {
        return userRepository.getUser(uid) ?: User()
    }
}