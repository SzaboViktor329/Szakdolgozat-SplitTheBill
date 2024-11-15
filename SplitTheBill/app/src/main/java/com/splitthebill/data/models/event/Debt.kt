package com.splitthebill.data.models.event

data class Debt(
    var fromUserId: String = "",
    var toUserId: String = "",
    var amount: Double = 0.0
)
