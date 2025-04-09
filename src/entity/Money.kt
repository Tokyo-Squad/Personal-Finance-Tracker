package entity

import java.io.Serializable


data class Money(
    val amount: Double,
    val currencyType: Currency = Currency.USD
): Serializable {
enum class Currency: Serializable {
    USD,
    EUR,
    EGP,
    IQD
}
}
