package com.splitthebill.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import com.splitthebill.data.models.User
import javax.inject.Inject

class RegistrationRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {
    private val userCollection: String = "users"

    fun register(email: String, password: String, user: User, onComplete: (message: String) ->Unit) {
        firestore.collection(userCollection).whereEqualTo("userName",user.userName).get().addOnCompleteListener{ task ->
            if(task.result?.isEmpty == true){
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ registrationResult ->
                    if(registrationResult.isSuccessful){
                        user.uid = firebaseAuth.uid!!
                        firestore.collection(userCollection).document(user.uid).set(user).addOnCompleteListener { userCreationResult ->
                            if(userCreationResult.isSuccessful) onComplete("")
                            else onComplete("User cannot be created")
                        }
                    } else {
                        when(registrationResult.exception) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                onComplete("Invalid email or password")
                            }
                            is FirebaseAuthUserCollisionException -> {
                                onComplete("Email already in use")
                            }
                            else -> {
                                onComplete("Registration failed")
                            }
                        }
                    }
                }
            } else {
                onComplete("Username already exists")
            }
        }
    }
}