package com.splitthebill.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.splitthebill.data.models.User
import javax.inject.Inject


class UserRepository @Inject constructor(private val firestore: FirebaseFirestore) {
    private val userCollection: String = "users"

    fun isUsernameAvailable(username: String, onComplete: (Boolean) -> Unit) {
        firestore.collection(userCollection)
            .whereEqualTo("username",username)
            .get()
            .addOnCompleteListener{ task ->
                if(task.isSuccessful) {
                    onComplete(task.result.isEmpty)
                } else {
                    onComplete(false)
                }
            }
    }

    fun createUser(user: User) {
        firestore.collection(userCollection).document(user.uid).set(user).addOnFailureListener{ task ->
            println(("A baj: " + task.message))
        }
    }

    /*
    fun getUser(uid: String) : User {

    }

     */
}