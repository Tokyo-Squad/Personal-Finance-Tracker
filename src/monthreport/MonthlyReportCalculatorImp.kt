package monthreport

import entity.MonthReportEntity
import entity.TransactionEntity
import monthreport.filter.TransactionFilter

class MonthlyReportCalculatorImp(
    private val transactions: List<TransactionEntity>,
    private val filter: TransactionFilter
) : MonthlyReportCalculator {

    override fun calculateMonthlyReport(month: Int): MonthReportEntity {
        val monthlyTransaction = filter.byMonth(transactions = transactions, month = month)
        val totalIncome = calculateTotalMonthlyIncomeOfTransaction(monthlyTransaction = monthlyTransaction?: emptyList())
        val totalExpense = calculateTotalMonthlyExpenseOfTransaction(monthlyTransaction = monthlyTransaction?: emptyList())
        val balance = calculateBalance(totalIncome, totalExpense)

        return MonthReportEntity(
            incomeTotal = totalIncome,
            expenseTotal = totalExpense,
            balance = balance,
            month = month
        )
    }


    private fun calculateTotalMonthlyIncomeOfTransaction(monthlyTransaction: List<TransactionEntity>): Double {
        return filter.byIncomeType(monthlyTransaction).sumOf { it.money.amount }
    }

    private fun calculateTotalMonthlyExpenseOfTransaction(monthlyTransaction: List<TransactionEntity>): Double {
        return filter.byExpenseType(monthlyTransaction).sumOf { it.money.amount }
    }

    private fun calculateBalance(totalIncome: Double, totalExpense: Double): Double {
        val balance =
            if(totalIncome - totalExpense < 0 ) 0.0
            else totalIncome - totalExpense
        return balance
    }


}