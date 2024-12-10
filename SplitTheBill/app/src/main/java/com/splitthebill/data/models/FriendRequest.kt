package com.splitthebill.data.models

import com.splitthebill.data.enums.FriendRequestStatus

data class FriendRequest(
    var friendRequestId: String = "",
    val senderUid: String = "",
    val receiverUid: String = "",
    var status: FriendRequestStatus = FriendRequestStatus.PENDING
)
