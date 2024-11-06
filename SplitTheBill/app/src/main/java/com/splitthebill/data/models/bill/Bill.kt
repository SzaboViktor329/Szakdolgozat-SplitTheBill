package com.splitthebill.data.models.bill

data class Bill(
    var billId: String = "",
    var eventId: String = "",
    val billName: String = "",
    val date: String = "",
    var total: Double = 0.0,
    val payers: List<Payer> = emptyList(),
    val items: List<Item> = emptyList()
)
