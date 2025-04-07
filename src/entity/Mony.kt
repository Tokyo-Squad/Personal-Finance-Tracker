package entity

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal,
    val currencyType: Currency = Currency.Dollars
)

enum class Currency {
    Dollars,
    Dinar,
    Euro,
    EgyptianPound
}