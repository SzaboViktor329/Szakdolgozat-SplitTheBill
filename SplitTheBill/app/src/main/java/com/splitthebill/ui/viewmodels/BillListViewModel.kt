package com.splitthebill.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.splitthebill.data.models.event.Event
import com.splitthebill.data.models.bill.Bill
import com.splitthebill.data.repositories.BillRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BillListViewModel @Inject constructor(private val billRepository: BillRepository) : ViewModel() {
    private val _bills = MutableLiveData<List<Bill>>(emptyList())
    val bills: LiveData<List<Bill>> = _bills

    fun fetchBills(event: Event) {
        billRepository.getBills(event) { billsResults ->
            _bills.value = billsResults
        }
    }
}