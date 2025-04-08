package entity.monthreport

import java.sql.Date

data class MonthReport(
    val incomeTotal: Float,
    val expenseTotal: Float,
    val balance: Float,
    val month: Date,
)
