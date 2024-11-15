package com.splitthebill.data.models.event

import com.splitthebill.data.enums.DebtStatus

data class Debt(
    var fromUserId: String = "",
    var toUserId: String = "",
    var amount: Double = 0.0,
    var status: DebtStatus = DebtStatus.PENDING
)
