package com.splitthebill.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.splitthebill.data.models.Event
import com.splitthebill.data.models.Group
import com.splitthebill.data.repositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val eventRepository: EventRepository) : ViewModel() {
    private val _events = MutableLiveData<List<Event>>(emptyList())
    val events: LiveData<List<Event>> = _events

    fun createEvent(event: Event, onSuccess: () -> Unit) {
        eventRepository.createEvent(event, onSuccess)
    }

    fun fetchEvents() {
        eventRepository.getEvents { eventsResults ->
            _events.value = eventsResults
        }
    }
}