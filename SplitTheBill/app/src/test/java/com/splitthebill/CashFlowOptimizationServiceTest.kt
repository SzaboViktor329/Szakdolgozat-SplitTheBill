package com.splitthebill

import com.splitthebill.data.models.bill.Bill
import com.splitthebill.data.models.bill.Item
import com.splitthebill.data.models.bill.Payer
import com.splitthebill.data.models.event.Debt
import com.splitthebill.services.CashFlowOptimizationService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CashFlowOptimizationServiceTest {
    private lateinit var service: CashFlowOptimizationService
    private lateinit var mockBill: Bill
    private lateinit var mockDebts: List<Debt>

    @Before
    fun setup() {
        service = CashFlowOptimizationService()

        mockBill = Bill(
            billId = "1",
            eventId = "event1",
            billName = "Dinner",
            date = "2024.12.14",
            total = 5000.0,
            payers = listOf(Payer("a",1000.0), Payer("b",1000.0), Payer("c",3000.0)),
            items = listOf(Item("a","",500.0), Item("b","",2500.0), Item("c","",2000.0))
        )

        mockDebts = listOf(
            Debt("c","b",1000.0),
            Debt("a","c",1500.0)
        )

    }

    @Test
    fun optimize_without_earlier_debts() {
        val debts = service.optimize(mockBill, emptyList())
        val sortedDebts = debts.sortedWith(compareBy({it.fromUserId}, {it.toUserId}))

        val expectedDebts = listOf(Debt("b","a",500.0), Debt("b","c",1000.0))
        val sortedExpectedDebts = expectedDebts.sortedWith(compareBy({it.fromUserId}, {it.toUserId}))

        assertEquals(sortedDebts, sortedExpectedDebts)
    }

    @Test
    fun optimize_with_earlier_debts() {
        val debts = service.optimize(mockBill, mockDebts)
        val sortedDebts = debts.sortedWith(compareBy({it.fromUserId}, {it.toUserId}))

        val expectedDebts = listOf(Debt("b","c",500.0), Debt("a","c",1000.0))
        val sortedExpectedDebts = expectedDebts.sortedWith(compareBy({it.fromUserId}, {it.toUserId}))
        assertEquals(sortedDebts, sortedExpectedDebts)
    }
}