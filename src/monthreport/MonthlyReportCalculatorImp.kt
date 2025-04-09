package monthreport
import entity.MonthReport
import entity.Transaction
import monthreport.filter.TransactionFilter

class MonthlyReportCalculatorImp(
    private val transactions: List<Transaction>,
    private val filter: TransactionFilter
) : MonthlyReportCalculator, TransactionFilter by filter {

    override fun calculateMonthlyReport(month: Int): MonthReport {
        val monthlyTransaction = filter.byMonth(transactions = transactions, month = month)
        val totalIncome = calculateTotalMonthlyIncomeOfTransaction(monthlyTransaction = monthlyTransaction)
        val totalExpense = calculateTotalMonthlyExpenseOfTransaction(monthlyTransaction = monthlyTransaction)
        val balance = calculateBalance(totalIncome, totalExpense)

        return MonthReport(
            incomeTotal = totalIncome,
            expenseTotal = totalExpense,
            balance = balance,
            month = month
        )
    }

    private fun calculateTotalMonthlyIncomeOfTransaction(monthlyTransaction: List<Transaction>): Double {
        return monthlyTransaction.filter { it.type == Transaction.Type.INCOME }.sumOf { it.money.amount }
    }

    private fun calculateTotalMonthlyExpenseOfTransaction(monthlyTransaction: List<Transaction>): Double {
        return monthlyTransaction.filter { it.type == Transaction.Type.EXPENSE }.sumOf { it.money.amount }
    }

    private fun calculateBalance(totalIncome: Double, totalExpense: Double): Double {
        val balance = totalIncome - totalExpense
        return balance
    }


}