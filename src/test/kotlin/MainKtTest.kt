import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculationCommission_noCommissionVKpayDefault() {
        val amountTransfer: Int = 10_000

        val result = calculationCommission(amountTransfer = amountTransfer)

        assertEquals(0, result)
    }

    @Test
    fun calculationCommission_noCommissionVKpay() {
        val amountTransfer: Int = 10_000
        val cardType: String = "VK Pay"
        val amountOfTransfersMonth: Int = 0

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals(0, result)
    }

    @Test
    fun calculationCommission_excessPaymentDayVKpay() {
        val amountTransfer: Int = 20_000
        val cardType: String = "VK Pay"
        val amountOfTransfersMonth: Int = 0

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals("Превышен лимит перевода", result)
    }

    @Test
    fun calculationCommission_excessPaymentMonthVKpay() {
        val amountTransfer: Int = 10_000
        val cardType: String = "VK Pay"
        val amountOfTransfersMonth: Int = 40_000

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals("Превышен лимит перевода за месяц", result)
    }

    @Test
    fun calculationCommission_noCommissionMasterCard() {
        val amountTransfer: Int = 10_000
        val cardType: String = "MasterCard"
        val amountOfTransfersMonth: Int = 0

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals(0, result)
    }

    @Test
    fun calculationCommission_noCommissionMaestro() {
        val amountTransfer: Int = 10_000
        val cardType: String = "Maestro"
        val amountOfTransfersMonth: Int = 0

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals(0, result)
    }

    @Test
    fun calculationCommission_CommissionMasterCard() {
        val amountTransfer: Int = 10_000
        val cardType: String = "MasterCard"
        val amountOfTransfersMonth: Int = 75_001

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals(80, result)
    }

    @Test
    fun calculationCommission_CommissionMaestro() {
        val amountTransfer: Int = 10_000
        val cardType: String = "Maestro"
        val amountOfTransfersMonth: Int = 75_001

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals(80, result)
    }

    @Test
    fun calculationCommission_MinCommissionVisa() {
        val amountTransfer: Int = 10
        val cardType: String = "Visa"
        val amountOfTransfersMonth: Int = 0

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals(35, result)
    }

    @Test
    fun calculationCommission_MinCommissionMir() {
        val amountTransfer: Int = 10
        val cardType: String = "Мир"
        val amountOfTransfersMonth: Int = 0

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals(35, result)
    }

    @Test
    fun calculationCommission_CommissionVisa() {
        val amountTransfer: Int = 10_000
        val cardType: String = "Visa"
        val amountOfTransfersMonth: Int = 0

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals(75, result)
    }

    @Test
    fun calculationCommission_CommissionMir() {
        val amountTransfer: Int = 10_000
        val cardType: String = "Мир"
        val amountOfTransfersMonth: Int = 0

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals(75, result)
    }

    @Test
    fun calculationCommission_excessPaymentDayMaestro() {
        val amountTransfer: Int = 160_000
        val cardType: String = "Maestro"
        val amountOfTransfersMonth: Int = 0

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals("Превышен лимит перевода", result)
    }

    @Test
    fun calculationCommission_excessPaymentMonthMir() {
        val amountTransfer: Int = 15_000
        val cardType: String = "Мир"
        val amountOfTransfersMonth: Int = 590_000

        val result = calculationCommission(amountTransfer = amountTransfer, cardType = cardType, amountOfTransfersMonth)

        assertEquals("Превышен лимит перевода за месяц", result)
    }
}