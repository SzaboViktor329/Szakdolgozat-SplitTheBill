package com.splitthebill.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.splitthebill.data.enums.EventStatus
import com.splitthebill.data.models.User
import com.splitthebill.data.models.bill.Bill
import com.splitthebill.data.models.event.Debt
import com.splitthebill.data.models.event.Event
import com.splitthebill.data.repositories.EventRepository
import com.splitthebill.data.repositories.GroupRepository
import com.splitthebill.services.CashFlowOptimizationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(private val eventRepository: EventRepository, private val groupRepository: GroupRepository, private val cashFlowOptimizationService: CashFlowOptimizationService) : ViewModel() {
    private val _event = MutableLiveData(Event())
    val event: LiveData<Event> = _event

    fun setEvent(newEvent: Event) {
        _event.value = newEvent
    }

    fun setEventStatus(eventStatus: EventStatus) {
        //_event.value.status = eventStatus
    }

    fun updateEventStatus(eventId: String, newEventStatus: EventStatus, onSuccess: () -> Unit) {
        eventRepository.updateEventStatus(eventId, newEventStatus, onSuccess)
    }

    fun updateDebts(eventId: String, debts: List<Debt>, onSuccess: () -> Unit) {
        eventRepository.updateDebts(eventId, debts, onSuccess)
    }

    fun updateFinishDate(eventId: String, finishDate: String, onSuccess: () -> Unit) {
        eventRepository.updateFinishDate(eventId, finishDate, onSuccess)
    }

    fun optimizeCashFlow(bill: Bill, earlierDebts: List<Debt>) : List<Debt> {
        return cashFlowOptimizationService.optimize(bill, earlierDebts)
    }

    fun getUsersFromEvent(event: Event, onSuccess: (users: List<User>) -> Unit){
        groupRepository.getUsersFromGroup(event.groupId, onSuccess)
    }

    fun refreshEvent() {
        _event.value?.let {
            eventRepository.getEvent(it.eventId) { eventResult->
                _event.value = eventResult
            }
        }
    }

}