import entity.CategoryEntity
import entity.MoneyEntity
import entity.TransactionEntity
import transaction.FileTransaction
import utils.checkCategoryType
import java.time.LocalDateTime
import java.util.*



fun main() {
    val scanner = Scanner(System.`in`)
    val transactionManager = TransactionManager(FileTransaction())
    val console = PrinterConsole()

    console.printWelcomeMessage()

    while (true) {
        console.printMenuList()
        when (scanner.nextInt()) {
            1 -> {

                scanner.nextLine()
                console.printTitleMessage("Enter transaction ID (Int): ")
                val id = scanner.nextInt()
                scanner.nextLine()
                console.printTitleMessage("Enter amount (Double): ")
                val amount = scanner.nextDouble()
                scanner.nextLine()

                console.printTitleMessage("Enter currency (e.g., USD): ")
                val currency = scanner.nextLine()

                console.printTitleMessage("Enter category name: ")
                val category = scanner.nextLine()
                val categoryName = checkCategoryType(category)


                console.printTitleMessage("Enter description: ")
                val description = scanner.nextLine()

                console.printTitleMessage("Enter type (INCOME or EXPENSE): ")
                val typeInput = scanner.nextLine().uppercase()
                val type = try {
                    TransactionEntity.Type.valueOf(typeInput)
                } catch (e: IllegalArgumentException) {
                    console.printErrorMessage("Invalid type. Please enter INCOME or EXPENSE.")
                    continue
                }

                val transaction = TransactionEntity(
                    id = id,
                    date = LocalDateTime.now(),
                    money = MoneyEntity(amount, MoneyEntity.Currency.valueOf(currency.uppercase())),
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
                console.printTitleMessage("Enter the month (1-12): ")
                val month = scanner.nextInt()
                val monthlyReportSummary = transactionManager.getMonthlyReport(month = month)
                println(monthlyReportSummary)
                console.printSuccessOperation("✅ monthly report got")

            }
            3-> {

                console.printTitleMessage("Enter transaction ID (Int): ")
                val id = scanner.nextInt()
                scanner.nextLine()

                console.printTitleMessage("Enter amount (Double): ")
                val amount = scanner.nextDouble()
                scanner.nextLine()

                console.printTitleMessage("Enter currency (e.g., USD): ")
                val currency = scanner.nextLine()

                console.printTitleMessage("Enter category name: ")
                val categoryName = scanner.nextLine()

                console.printTitleMessage("Enter description: ")
                val description = scanner.nextLine()

                console.printTitleMessage("Enter type (INCOME or EXPENSE): ")
                val typeInput = scanner.nextLine().uppercase()
                val type = try {
                    TransactionEntity.Type.valueOf(typeInput)
                } catch (e: IllegalArgumentException) {
                    console.printErrorMessage("Invalid type. Please enter INCOME or EXPENSE.")
                    continue
                }
                val transaction = TransactionEntity(
                    id = id,
                    date = LocalDateTime.now(),
                    money = MoneyEntity(amount, MoneyEntity.Currency.valueOf(currency.uppercase())),
                    category = CategoryEntity.Food,
                    description = description,
                    type = type
                )
                    if(transactionManager.update(transaction))
                        console.printSuccessOperation(" ✅Transaction Updated ... ")
                    else console.printErrorMessage("❌ There is Some Wrong Not Value Updated ...  ")
            }
            4 -> {
                scanner.nextLine()
                val idValue = scanner.nextInt()
                scanner.nextLine()
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
