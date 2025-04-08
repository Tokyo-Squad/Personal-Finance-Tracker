package entity

import java.math.BigDecimal

data class MonthReport(
    val incomeTotal: BigDecimal,
    val expenseTotal: BigDecimal,
    val balance: BigDecimal,
    val month: Int,
)
