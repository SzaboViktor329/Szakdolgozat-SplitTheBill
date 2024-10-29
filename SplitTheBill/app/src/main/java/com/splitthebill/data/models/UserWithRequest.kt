package com.splitthebill.data.models

data class UserWithRequest(
    val user: User = User(),
    val friendRequest: FriendRequest = FriendRequest()
)
