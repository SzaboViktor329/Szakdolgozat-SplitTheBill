package com.splitthebill.data.models

import com.splitthebill.data.enums.FriendRequestStatus

data class FriendRequest(
    val senderUid: String = "",
    val receiverUid: String = "",
    val status: FriendRequestStatus = FriendRequestStatus.PENDING
)
