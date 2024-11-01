package com.splitthebill.data.repositories

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.splitthebill.data.models.Group
import com.splitthebill.data.models.User
import java.util.UUID
import javax.inject.Inject

class GroupRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore) {
    private val groupCollection: String = "groups"
    private val userCollection: String = "users"

    fun createGroup(group: Group, onSuccess: () -> Unit) {
        val groupId = UUID.randomUUID().toString()
        group.groupId = groupId
        firestore.collection(groupCollection).document(groupId).set(group).addOnSuccessListener {
            onSuccess()
        }
    }

    fun getGroups(onSuccess: (groups: List<Group>) -> Unit) {
        val currentUid = firebaseAuth.uid ?: ""
        firestore.collection(groupCollection).whereArrayContains("userIds", currentUid).get().addOnSuccessListener { groupDocuments->
            if(!groupDocuments.isEmpty) {
                val groups: List<Group> = groupDocuments.toObjects(Group::class.java)
                onSuccess(groups)
            }
        }
    }

    fun getUsersFromGroup(groupId: String, onSuccess: (users: List<User>) -> Unit) {
        firestore.collection(groupCollection).document(groupId).get().addOnSuccessListener { groupDocument ->
            val group = groupDocument.toObject(Group::class.java) ?: Group()
            val userIds = group.userIds
            if(userIds.isNotEmpty()){
                firestore.collection(userCollection).whereIn("uid", userIds).get().addOnSuccessListener { userDocuments ->
                    if(!userDocuments.isEmpty) {
                        val users : List<User> = userDocuments.toObjects(User::class.java)
                        onSuccess(users)
                    }
                }
            }
        }
    }


}