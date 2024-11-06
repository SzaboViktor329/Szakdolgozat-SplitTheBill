package com.splitthebill.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.splitthebill.data.models.User
import com.splitthebill.data.models.bill.Bill
import com.splitthebill.data.repositories.BillRepository
import com.splitthebill.data.repositories.GroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBillViewModel @Inject constructor(private val billRepository: BillRepository, private val groupRepository: GroupRepository) :ViewModel() {
    fun addBill(bill: Bill, onSuccess: () -> Unit) {
        billRepository.addBill(bill, onSuccess)
    }

    fun getUsersFromGroup(groupId: String, onSuccess: (users: List<User>) -> Unit){
        groupRepository.getUsersFromGroup(groupId, onSuccess)
    }
}