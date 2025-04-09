package entity


data class MonthReportEntity(
    val incomeTotal: Double,
    val expenseTotal: Double,
    val balance: Double,
    val month: Int,
)
