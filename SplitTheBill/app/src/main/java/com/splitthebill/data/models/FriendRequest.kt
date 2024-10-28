package com.splitthebill.data.models

data class FriendRequest(
    val senderUid: String = "",
    val receiverUid: String = "",
    val status: String = "pending"
)
