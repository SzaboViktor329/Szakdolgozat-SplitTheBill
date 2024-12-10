package com.splitthebill.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.splitthebill.data.enums.FriendRequestStatus
import com.splitthebill.data.models.FriendRequest
import com.splitthebill.data.models.User
import com.splitthebill.data.models.UserWithRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID
import javax.inject.Inject

class FriendRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {
    private val friendRequestCollection: String = "friendRequests"
    private val userCollection: String = "users"

    private var friendRequestListener: ListenerRegistration? = null
    private var friendsListListener: ListenerRegistration? = null

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

    fun createFriendRequest(friendRequest: FriendRequest, onComplete: (Boolean)-> Unit){
        isFriendRequestExist(friendRequest) { friendRequestExist->
            if(!friendRequestExist){
                val friendRequestId = UUID.randomUUID().toString()
                friendRequest.friendRequestId = friendRequestId
                firestore.collection(friendRequestCollection).document(friendRequestId).set(friendRequest).addOnSuccessListener {
                    onComplete(true)
                }
            }
            else{
                onComplete(false)
            }
        }
    }

    fun fetchUsersWithFriendRequests() : Flow<List<UserWithRequest>> = callbackFlow {
        friendRequestListener = firestore.collection(friendRequestCollection)
            .whereEqualTo("receiverUid", firebaseAuth.uid)
            .whereEqualTo("status", FriendRequestStatus.PENDING)
            .addSnapshotListener { snapshot, error ->
                if(error != null){
                    close(error)
                    return@addSnapshotListener
                }
                println("firendrequests fetch happened")
                val friendRequests = snapshot?.toObjects(FriendRequest::class.java) ?: emptyList()
                val senderUids = friendRequests.map { it.senderUid }
                if(senderUids.isNotEmpty()) {
                    println("user fetch happened")
                    firestore.collection(userCollection).whereIn(FieldPath.documentId(), senderUids)
                        .get()
                        .addOnSuccessListener { userDocuments->
                            val users = userDocuments.toObjects(User::class.java)

                            val usersWithRequests = users.map { user->
                                val matchingRequest = friendRequests.firstOrNull { it.senderUid == user.uid }
                                UserWithRequest(user, matchingRequest!!)
                            }
                            trySend(usersWithRequests).isSuccess
                        }
                }
                else trySend(emptyList()).isSuccess
            }
        awaitClose { friendRequestListener?.remove() }
    }

    fun fetchFriends() : Flow<List<User>> = callbackFlow {
        println("Does this even called?")
        val currentUid = firebaseAuth.uid
        friendsListListener = firestore.collection(friendRequestCollection)
            .whereEqualTo("status", FriendRequestStatus.ACCEPTED)
            .where(Filter.or(
                Filter.equalTo("senderUid", currentUid),
                Filter.equalTo("receiverUid", currentUid)
            )).addSnapshotListener { snapshot, error ->
                if(error != null){
                    close(error)
                    return@addSnapshotListener
                }
                val friendRequests = snapshot?.toObjects(FriendRequest::class.java) ?: emptyList()
                val friendUIDs = friendRequests.map { if(it.senderUid == currentUid) it.receiverUid else it.senderUid }
                if(friendUIDs.isNotEmpty()){
                    firestore.collection(userCollection).whereIn("uid", friendUIDs).get().addOnSuccessListener { userDocuments->
                        val users = userDocuments.toObjects(User::class.java)
                        trySend(users).isSuccess
                    }
                }
                else trySend(emptyList()).isSuccess
            }
        awaitClose { friendsListListener?.remove() }
    }

    fun updateFriendRequest(friendRequest: FriendRequest, onComplete: (Boolean)-> Unit) {
        firestore.collection(friendRequestCollection).document(friendRequest.friendRequestId).set(friendRequest).addOnSuccessListener {
            onComplete(true)
        }
    }

    fun stopListening() {
        friendRequestListener?.remove()
        friendRequestListener = null

        friendsListListener?.remove()
        friendsListListener = null
    }
}