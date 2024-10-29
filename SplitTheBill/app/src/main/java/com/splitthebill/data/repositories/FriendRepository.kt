package com.splitthebill.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.splitthebill.data.enums.FriendRequestStatus
import com.splitthebill.data.models.FriendRequest
import com.splitthebill.data.models.User
import com.splitthebill.data.models.UserWithRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FriendRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {
    private val friendRequestCollection: String = "friendRequests"
    private val userCollection: String = "users"

    private fun isFriendRequestExist(friendRequest: FriendRequest, onComplete: (friendRequestExist: Boolean)-> Unit) {
        val firstQuery = firestore.collection(friendRequestCollection)
            .whereEqualTo("senderUid", friendRequest.senderUid)
            .whereEqualTo("receiverUid", friendRequest.receiverUid)
        val secondQuery = firestore.collection(friendRequestCollection)
            .whereEqualTo("senderUid", friendRequest.receiverUid)
            .whereEqualTo("receiverUid", friendRequest.senderUid)

        firstQuery.get().addOnSuccessListener { result1->
            if(result1.isEmpty){
                secondQuery.get().addOnSuccessListener { result2 ->
                    if(result2.isEmpty){
                        onComplete(false)
                    }
                    else{
                        onComplete(true)
                    }
                }
            }
            else{
                onComplete(true)
            }
        }
    }

    fun fetchUsersWithFriendRequests(onComplete: (List<UserWithRequest>) -> Unit) {
        firestore.collection(friendRequestCollection)
            .whereEqualTo("receiverUid", firebaseAuth.uid)
            .whereEqualTo("status", FriendRequestStatus.PENDING)
            .get()
            .addOnSuccessListener { friendRequestDocuments ->
                val friendRequests = friendRequestDocuments.toObjects(FriendRequest::class.java)
                val senderUids = friendRequests.map { it.senderUid }
                if(senderUids.isNotEmpty()) {
                    firestore.collection(userCollection).whereIn(FieldPath.documentId(), senderUids)
                        .get()
                        .addOnSuccessListener { userDocuments->
                            val users = userDocuments.toObjects(User::class.java)

                            val usersWithRequests = users.map { user->
                                val matchingRequest = friendRequests.firstOrNull() { it.senderUid == user.uid }
                                UserWithRequest(user, matchingRequest!!)
                            }
                            onComplete(usersWithRequests)
                        }
                }
            }
    }

    fun createFriendRequest(friendRequest: FriendRequest, onComplete: (Boolean)-> Unit){
        isFriendRequestExist(friendRequest) { friendRequestExist->
            if(!friendRequestExist){
                firestore.collection(friendRequestCollection).add(friendRequest).addOnSuccessListener {
                    onComplete(true)
                }
            }
            else{
                onComplete(false)
            }
        }
    }
}