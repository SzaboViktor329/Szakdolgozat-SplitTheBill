package com.splitthebill.ui.utils

import com.splitthebill.data.models.bill.Item
import com.splitthebill.data.models.bill.Payer

fun isAdBillItemsValid(items: List<Item>): Boolean {
    items.forEach { item->
        if(item.itemName=="" || item.userId=="" || item.price==0.0) return false
    }
    return true
}

fun isAdBillPayersValid(payers: List<Payer>, total: Double): Boolean {
    var countedTotal = 0.0
    payers.forEach { payer->
        if(payer.userId=="") return false
        countedTotal += payer.amount
    }
    return countedTotal == total
}