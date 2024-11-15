package com.splitthebill.services

import com.splitthebill.data.models.bill.Bill
import com.splitthebill.data.models.event.Debt
import javax.inject.Inject

class CashFlowOptimizationService @Inject constructor() {
    fun optimize(bill: Bill, earlierDebts: List<Debt>) : List<Debt> {
        val nodes = initNodes(bill, earlierDebts)

        val outputDebts = minimizeCashFlow(nodes)
        return outputDebts
    }

    private fun minimizeCashFlowRec(nodes: MutableMap<String, Double>, outputDebts: MutableList<Debt>) {
        val maxCreditor = nodes.maxBy { it.value }.key
        val maxCredit = nodes[maxCreditor]!!

        val maxDebtor = nodes.minBy { it.value }.key
        val maxDebt = nodes[maxDebtor]!!

        if(maxCredit == 0.0 && maxDebt == 0.0) return

        val min = minOf(-maxDebt, maxCredit)

        nodes[maxCreditor] = nodes[maxCreditor]!! - min
        nodes[maxDebtor] = nodes[maxDebtor]!! + min

        outputDebts.add(
            Debt(
            fromUserId = maxDebtor,
            toUserId = maxCreditor,
            amount = min
        )
        )
        minimizeCashFlowRec(nodes,outputDebts)
    }

    private fun minimizeCashFlow(nodes: MutableMap<String, Double>) : List<Debt> {
        val outputDebts = mutableListOf<Debt>()
        minimizeCashFlowRec(nodes,outputDebts)
        return outputDebts
    }

    private fun initNodes(bill: Bill, debts: List<Debt>) : MutableMap<String, Double> {
        val nodes = mutableMapOf<String, Double>()
        bill.payers.forEach { payer ->
            nodes[payer.userId] = nodes.getOrDefault(payer.userId, 0.0) + payer.amount
        }
        bill.items.forEach { item ->
            nodes[item.userId] = nodes.getOrDefault(item.userId, 0.0) - item.price
        }
        debts.forEach { debt ->
            nodes[debt.fromUserId] = nodes.getOrDefault(debt.fromUserId, 0.0) - debt.amount
            nodes[debt.toUserId] = nodes.getOrDefault(debt.toUserId, 0.0) + debt.amount
        }
        return nodes
    }
}