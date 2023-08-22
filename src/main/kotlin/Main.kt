fun main() {
    println(calculationCommission(10_000)) // Ожидаемый результат - 0
    println(calculationCommission(20_000)) // Ожидаемый результат - Превышен лимит перевода
    println(calculationCommission(10_000, amountOfTransfersMonth = 40_000)) // Ожидаемый результат - Превышен лимит перевода за месяц
    println(calculationCommission(10_000, "MasterCard")) // Ожидаемый результат - 0
    println(calculationCommission(10_000, "MasterCard", 75_001)) // Ожидаемый результат - 80
    println(calculationCommission(10, "Visa")) // Ожидаемый результат - 35
    println(calculationCommission(10_000, "Мир")) // Ожидаемый результат - 75
    println(calculationCommission(160_000, "MasterCard"))  // Ожидаемый результат - Превышен лимит перевода
    println(calculationCommission(15_000, "Мир", 590_000)) // Ожидаемый результат - Превышен лимит перевода за месяц
}

fun calculationCommission(amountTransfer: Int, cardType: String = "VK Pay", amountOfTransfersMonth: Int = 0) =
    when (cardType) {
        "MasterCard", "Maestro" -> limitOtherCard(
            amountTransfer,
            amountOfTransfersMonth,
            commissionMastercardMaestro(amountTransfer, amountOfTransfersMonth)
        )

        "Visa", "Мир" -> limitOtherCard(amountTransfer, amountOfTransfersMonth, commissionVisaMir(amountTransfer))
        else -> limitVKPay(amountTransfer, amountOfTransfersMonth)
    }


fun commissionMastercardMaestro(amountTransfer: Int, amountOfTransfersMonth: Int): Int =
    if (amountOfTransfersMonth + amountTransfer > 75_000) (amountTransfer * 0.006).toInt() + 20 else 0

fun commissionVisaMir(amount: Int): Int {
    val commission = 0.75
    val commissionMin = 35

    return if (amount * commission / 100 > commissionMin) (amount * commission / 100).toInt() else commissionMin
}

fun limitVKPay(amountTransfer: Int, amountOfTransfersMonth: Int) = when {
    amountTransfer > 15_000 -> "Превышен лимит перевода"
    amountOfTransfersMonth + amountTransfer > 45_000 -> "Превышен лимит перевода за месяц"
    else -> 0
}

fun limitOtherCard(amountTransfer: Int, amountOfTransfersMonth: Int, commission: Int) = when {
    amountTransfer > 150_000 -> "Превышен лимит перевода"
    amountOfTransfersMonth + amountTransfer > 600_000 -> "Превышен лимит перевода за месяц"
    else -> commission
}
