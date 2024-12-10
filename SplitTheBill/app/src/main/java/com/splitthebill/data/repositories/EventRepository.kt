package com.splitthebill.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.splitthebill.data.enums.EventStatus
import com.splitthebill.data.models.event.Debt
import com.splitthebill.data.models.event.Event
import java.util.UUID
import javax.inject.Inject

class EventRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {
    private val eventCollection: String = "events"
    private val groupCollection: String = "groups"

    fun createEvent(event: Event, onSuccess: () -> Unit) {
        val eventId = UUID.randomUUID().toString()
        event.eventId = eventId
        firestore.collection(eventCollection).document(eventId).set(event).addOnSuccessListener {
            onSuccess()
        }
    }

    fun updateEventStatus(eventId: String, newEventStatus: EventStatus, onSuccess: () -> Unit) {
        firestore.collection(eventCollection).document(eventId).update("status", newEventStatus).addOnSuccessListener {
            onSuccess()
        }
    }

    fun updateDebts(eventId: String, debts: List<Debt> , onSuccess: () -> Unit) {
        firestore.collection(eventCollection).document(eventId).update("debts", debts).addOnSuccessListener {
            onSuccess()
        }
    }

    fun updateFinishDate(eventId: String, finishDate: String, onSuccess: () -> Unit) {
        firestore.collection(eventCollection).document(eventId).update("finishDate", finishDate).addOnSuccessListener {
            onSuccess()
        }
    }

    fun getEvent(eventId: String, onSuccess: (Event) -> Unit) {
        firestore.collection(eventCollection).document(eventId).get().addOnSuccessListener { eventDocument ->
            onSuccess(eventDocument.toObject(Event::class.java) ?: Event())
        }
    }

    fun getEvents(queryLimit: Long = -1, eventStatuses: MutableList<EventStatus> = mutableListOf(), onSuccess: (events: List<Event>) -> Unit) {
        val currentUid = firebaseAuth.uid ?: ""
        var eventQuery: Query  = firestore.collection(eventCollection)
        if(queryLimit.toInt() != -1) eventQuery = eventQuery.limit(queryLimit)
        if(eventStatuses.isNotEmpty()) eventQuery = eventQuery.whereIn("status", eventStatuses)
        eventQuery = eventQuery.orderBy("startDate", Query.Direction.DESCENDING)
        firestore.collection(groupCollection).whereArrayContains("userIds", currentUid).get().addOnSuccessListener { groupDocuments->
            if(!groupDocuments.isEmpty) {
                val groupIds = groupDocuments.documents.map { it.id }
                eventQuery.whereIn("groupId", groupIds).get().addOnSuccessListener { eventDocuments ->
                    if (!eventDocuments.isEmpty) {
                        val events: List<Event> = eventDocuments.toObjects(Event::class.java)
                        onSuccess(events)
                    }
                    else onSuccess(emptyList())
                }
            }
            else onSuccess(emptyList())
        }
    }
}