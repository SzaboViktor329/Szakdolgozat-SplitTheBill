package com.splitthebill.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.splitthebill.data.models.bill.Bill
import com.splitthebill.data.repositories.BillRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(private val billRepository: BillRepository) :ViewModel() {


    fun addBill(bill: Bill, onSuccess: () -> Unit) {
        billRepository.addBill(bill, onSuccess)
    }
}