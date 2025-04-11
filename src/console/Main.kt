package console

import entity.MoneyEntity
import entity.TransactionEntity
import transaction.FileTransaction
import java.lang.Math.random
import java.time.LocalDateTime
import java.util.*



fun main() {
    val scanner = Scanner(System.`in`)
    val transactionManager = TransactionManager(FileTransaction())
    val console = PrinterConsole()
    val readerConsole = ReaderConsole(scanner= scanner, console = console)

    console.printWelcomeMessage()

    while (true) {
        console.printMenuList()
        when (scanner.nextInt()) {
            1 -> {
                val id = (random() * 1000).toInt()
                val amount = readerConsole.readDouble("enter amount")
                val currency = readerConsole.readCurrency()
                val categoryName = readerConsole.readCategory()
                val description = readerConsole.readDescription()
                val type = readerConsole.readTransactionType()
                val transaction = TransactionEntity(
                    id = id,
                    date = LocalDateTime.now(),
                    money = MoneyEntity(amount, currency),
                    category = categoryName,
                    description = description,
                    type = type
                )

                val added = transactionManager.add(transaction)
                if (added){
                    console.printSuccessOperation( "✅ Transaction added.")
                } else console.printErrorMessage("❌ Transaction ID already exists!")

            }
            2 -> {
                scanner.nextLine()
                val month = readerConsole.readInt("Enter the month (1-12)")
                val monthlyReportSummary = transactionManager.getMonthlyReport(month = month)
                console.printMonthlyReport(monthlyReportSummary)
                console.printSuccessOperation("✅ monthly report got")
            }
            3-> {
                scanner.nextLine()
                val id = (random() * 1000).toInt()
                val amount = readerConsole.readDouble("enter the amount ")
                val currency = readerConsole.readCurrency()
                val categoryName = readerConsole.readCategory()
                val description = readerConsole.readDescription()
                val type = readerConsole.readTransactionType()
                val transaction = TransactionEntity(
                    id = id,
                    date = LocalDateTime.now(),
                    money = MoneyEntity(amount, currency),
                    category = categoryName,
                    description = description,
                    type = type
                )
                    if(transactionManager.update(transaction))
                        console.printSuccessOperation(" ✅Transaction Updated ... ")
                    else console.printErrorMessage("❌ There is Some Wrong Not Value Updated ...  ")
            }
            4 -> {
                scanner.nextLine()
                val idValue = readerConsole.readInt("enter id value")
                if(transactionManager.delete(idValue))
                    console.printSuccessOperation(" ✅Transaction Deleted ... ")
                else console.printErrorMessage("❌ There is Some Wrong Not Value Deleted ... ")

            }
            5-> {
                console.printAllTransaction(transactionManager.getAll())
                console.printSuccessOperation("✅ all transaction got")
            }
            6-> {
                console.printSuccessOperation("Goodbye! 👋")
                break
            }
            else -> {
                console.printErrorMessage("Invalid option. Please try again.")
            }
        }
    }
}
