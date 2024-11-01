package com.splitthebill.data.models

data class Group(
    var groupId: String = "",
    val groupName: String = "",
    var userIds: List<String> = emptyList()
)
