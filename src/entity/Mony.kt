package entity

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal,
    val currencyType: Currency = Currency.USD
){
enum class Currency {
    USD,
    EUR,
    EGP,
    IQD
}
}
