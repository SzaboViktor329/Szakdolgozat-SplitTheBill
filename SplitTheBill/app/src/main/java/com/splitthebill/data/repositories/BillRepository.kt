package com.splitthebill.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.splitthebill.data.models.Event
import com.splitthebill.data.models.bill.Bill
import java.util.UUID
import javax.inject.Inject

class BillRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {
    private val billCollection: String = "bills"

    fun addBill(bill: Bill, onSuccess: () -> Unit) {
        val billId = UUID.randomUUID().toString()
        bill.billId = billId
        firestore.collection(billCollection).document(billId).set(bill).addOnSuccessListener {
            onSuccess()
        }
    }

    fun getBills(event: Event, onSuccess: (bills: List<Bill>) -> Unit) {
        firestore.collection(billCollection).whereEqualTo("eventId", event.eventId).get().addOnSuccessListener { billDocuments ->
            if(!billDocuments.isEmpty){
                val bills: List<Bill> = billDocuments.toObjects(Bill::class.java)
                onSuccess(bills)
            }
            else onSuccess(emptyList())
        }
    }

}