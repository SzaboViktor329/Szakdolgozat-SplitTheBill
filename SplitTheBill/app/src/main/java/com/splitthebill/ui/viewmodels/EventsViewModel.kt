package com.splitthebill.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.splitthebill.data.enums.EventStatus
import com.splitthebill.data.models.Event
import com.splitthebill.data.models.Group
import com.splitthebill.data.repositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val eventRepository: EventRepository) : ViewModel() {
    private val _events = MutableLiveData<List<Event>>(emptyList())
    val events: LiveData<List<Event>> = _events

    private var eventStatuses: MutableList<EventStatus> = mutableListOf(EventStatus.PENDING, EventStatus.PAYING, EventStatus.FINISHED)

    fun getEventStatuses(): List<EventStatus> {
        return eventStatuses
    }

    fun setEventStatuses (statuses: List<EventStatus>) {
        eventStatuses = statuses.toMutableList()
    }

    fun createEvent(event: Event, onSuccess: () -> Unit) {
        eventRepository.createEvent(event, onSuccess)
    }

    fun fetchRecentEvents() {
        val eventLimit : Long = 4
        eventRepository.getEvents(eventLimit) { eventsResults ->
            _events.value = eventsResults
        }
    }

    fun fetchEvents() {
        eventRepository.getEvents(eventStatuses = eventStatuses) { eventsResults ->
            _events.value = eventsResults
        }
    }
}