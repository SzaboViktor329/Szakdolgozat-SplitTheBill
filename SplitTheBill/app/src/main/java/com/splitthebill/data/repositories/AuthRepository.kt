package com.splitthebill.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    suspend fun login(email: String, password: String): FirebaseUser? {
        return firebaseAuth.signInWithEmailAndPassword(email,password).await().user
    }

    fun register(email: String, password: String, onSuccess: (FirebaseUser) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            val currentUserAuth = firebaseAuth.currentUser
            if (currentUserAuth != null) {
                onSuccess(currentUserAuth)
            }
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun logout() {
        firebaseAuth.signOut()
    }



}