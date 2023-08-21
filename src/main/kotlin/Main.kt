fun main() {
    println(calculationCommission(10_000)) // Ожидаемый результат - 0
    println(calculationCommission(10_000, "MasterCard")) // Ожидаемый результат - 0
    println(calculationCommission(10_000, "MasterCard", 75_001)) // Ожидаемый результат - 80
    println(calculationCommission(10, "Visa")) // Ожидаемый результат - 35
    println(calculationCommission(10_000, "Mir")) // Ожидаемый результат - 75
}

fun calculationCommission(amountTransfer: Int, cardType: String = "VK Pay", amountOfTransfersMonth: Int = 0): Int =
    when (cardType) {
        "MasterCard", "Maestro" -> commissionMastercardMaestro(amountOfTransfersMonth, amountTransfer)
        "Visa", "Мир" -> commissionVisaMir(amountTransfer)
        else -> 0
    }


fun commissionMastercardMaestro(amountOfTransfersMonth: Int = 0, amountTransfer: Int): Int =
    if (amountOfTransfersMonth > 75_000) (amountTransfer * 0.006).toInt() + 20 else 0

fun commissionVisaMir(amount: Int): Int {
    val commission = 0.75
    val commissionMin = 35

    return if (amount * commission / 100 > commissionMin) (amount * commission / 100).toInt() else commissionMin
}
