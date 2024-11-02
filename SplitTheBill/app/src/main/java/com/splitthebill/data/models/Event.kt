package com.splitthebill.data.models

import com.splitthebill.data.enums.EventStatus

data class Event(
    var eventId: String = "",
    val eventName: String = "",
    var groupId: String = "",
    val startDate: String = "",
    val finishDate: String = "",
    var status: EventStatus = EventStatus.PENDING,
    var billIds: List<String> = emptyList()
)
