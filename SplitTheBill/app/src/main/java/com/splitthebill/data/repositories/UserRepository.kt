package com.splitthebill.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.splitthebill.data.models.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(private val firestore: FirebaseFirestore) {
    private val userCollection: String = "users"

    fun getUserByUsername(userName: String, onComplete: (User?) -> Unit) {
        firestore.collection(userCollection).whereEqualTo("userName", userName).get().addOnSuccessListener { result ->
            if(!result.isEmpty){
                val user = result.documents.first().toObject(User::class.java)
                onComplete(user)
            }
            else onComplete(null)
        }
    }

    suspend fun getUser(uid: String) : User? {
        return try {
            val user = firestore.collection(userCollection).document(uid).get().await()
            if (user.exists()) {
                user.toObject(User::class.java)
            } else {
                null
            }
        } catch (e: Exception){
            null
        }
    }
}