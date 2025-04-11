package console

import entity.MoneyEntity
import entity.MonthReportEntity
import entity.TransactionEntity
import utils.withGreenColor
import utils.withRedColor
import java.time.format.DateTimeFormatter

class PrinterConsole {

    fun printWelcomeMessage(){
        println("Welcome to the Personal Finance Tracker")
    }

    fun printMenuList(){
        println("\nMenu:")
        println("1. Add a new transaction")
        println("2. Show monthly summary")
        println("3. Update Specific Transaction")
        println("4. Delete Specific Transaction")
        println("5. Get All Transaction")
        println("6. Exit")
        print("Please choose an option (1/2/3/4/5/6): ")
    }

    fun printTitleMessage(message: String){
        print("$message : ")
    }

    fun printErrorMessage(errorMessage: String){
        println(errorMessage.withRedColor())
    }

    fun printSuccessOperation(descriptionOperation: String){
        println(descriptionOperation.withGreenColor())
    }

    fun printAllTransaction(transactions: List<TransactionEntity>) {
        if (transactions.isEmpty()) {
            println("No transactions found")
            return
        }

        println("\nTRANSACTION HISTORY")
        println("----------------------------------------------------------------")
        println("ID  Date            Amount        Category    Description")
        println("----------------------------------------------------------------")

        transactions.forEach { transaction ->
            val sign = if (transaction.type == TransactionEntity.Type.INCOME) "+" else "-"
            val date = transaction.date.format(DateTimeFormatter.ofPattern("MM/dd HH:mm"))
            val amount = formatMoney(transaction.money, sign)
            val desc = transaction.description ?: ""

            println("%-3d %-15s %-13s %-11s %s".format(
                transaction.id,
                date,
                amount,
                transaction.category,
                desc
            ))
        }

        println("----------------------------------------------------------------")


    }

    private fun formatMoney(money: MoneyEntity, sign: String = ""): String {
        return when (money.currencyType) {
            MoneyEntity.Currency.EUR -> "$sign${"%.2f".format(money.amount)} €"
            MoneyEntity.Currency.EGP -> "$sign${"%.2f".format(money.amount)} EGP"
            MoneyEntity.Currency.IQD -> "$sign${"%.0f".format(money.amount)} IQD"
            else -> "$sign${"%.2f".format(money.amount)} $"  // Default to USD
        }
    }

    fun printMonthlyReport(monthlyReportSummary: MonthReportEntity) {
        val currency = monthlyReportSummary ?: MoneyEntity.Currency.USD

        fun formatAmount(amount: Double): String {
            return when (currency) {
                MoneyEntity.Currency.EUR -> "€${"%.2f".format(amount)}"
                MoneyEntity.Currency.EGP -> "EGP ${"%.2f".format(amount)}"
                MoneyEntity.Currency.IQD -> "IQD ${"%.0f".format(amount)}"
                else -> "$${"%.2f".format(amount)}"
            }
        }
        println("\n╔══════════════════════════════════════╗")
        println("║        MONTHLY FINANCIAL REPORT      ║")
        println("╟──────────────────────────────────────╢")
        println("║  Month: ${monthlyReportSummary.month}                   ")
        println("╟──────────────────────────────────────╢")
        println("║  Total Income:  ${formatAmount(monthlyReportSummary.incomeTotal)}              ")
        println("║  Total Expenses: ${formatAmount(monthlyReportSummary.expenseTotal)}            ")
        println("╟──────────────────────────────────────╢")
        println("║  Final Balance: ${formatAmount(monthlyReportSummary.balance)}            ")
        println("╚══════════════════════════════════════╝")
    }

}