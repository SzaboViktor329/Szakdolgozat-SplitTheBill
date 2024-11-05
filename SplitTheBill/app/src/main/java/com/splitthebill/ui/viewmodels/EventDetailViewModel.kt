package com.splitthebill.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.splitthebill.data.models.Event
import com.splitthebill.data.repositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(private val eventRepository: EventRepository) : ViewModel() {
    private val _event = mutableStateOf(Event())
    val event: State<Event> = _event

    fun setEvent(newEvent: Event) {
        _event.value = newEvent
    }
}