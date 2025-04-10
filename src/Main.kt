import entity.CategoryEntity
import entity.MoneyEntity
import entity.TransactionEntity
import transaction.FileTransaction
import java.time.LocalDateTime
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val transactionManager = TransactionManager(FileTransaction())

    println("Welcome to the Transaction Manager")

    while (true) {
        println("\nMenu:")
        println("1. Add a new transaction")
        println("2. Show monthly summary")
        println("3. Exit")
        print("Please choose an option (1/2/3): ")

        when (scanner.nextInt()) {
            1 -> {

                scanner.nextLine()
                print("Enter transaction ID (Int): ")
                val id = scanner.nextInt()
                scanner.nextLine()

                print("Enter amount (Double): ")
                val amount = scanner.nextDouble()
                scanner.nextLine()

                print("Enter currency (e.g., USD): ")
                val currency = scanner.nextLine()

                print("Enter category name: ")
                val categoryName = scanner.nextLine()

                print("Enter description: ")
                val description = scanner.nextLine()

                print("Enter type (INCOME or EXPENSE): ")
                val typeInput = scanner.nextLine().uppercase()
                val type = try {
                    TransactionEntity.Type.valueOf(typeInput)
                } catch (e: IllegalArgumentException) {
                    println("Invalid type. Please enter INCOME or EXPENSE.")
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

                val added = transactionManager.add(transaction)
                println(if (added) "✅ Transaction added." else "❌ Transaction ID already exists!")

            }
            2 -> {

                println("Enter the month (1-12): ")
                val month = scanner.nextInt()
                println("Enter the year (e.g., 2025): ")
                val year = scanner.nextInt()


            }
            3 -> {

                println("Goodbye! 👋")
                break
            }
            else -> {
                println("Invalid option. Please try again.")
            }
        }
    }
}
