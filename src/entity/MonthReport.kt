package entity

import java.io.Serializable


data class MonthReport(
    val incomeTotal: Double,
    val expenseTotal: Double,
    val balance: Double,
    val month: Int,
): Serializable
