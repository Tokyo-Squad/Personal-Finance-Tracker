package console

import entity.CategoryEntity
import entity.MoneyEntity
import entity.TransactionEntity
import java.util.Scanner

class ReaderConsole(private val scanner: Scanner, private val console: PrinterConsole) {

    fun readDouble(message: String): Double {
        console.printTitleMessage(message)
        val amount = scanner.nextDouble()
        scanner.nextLine()
        return amount
    }

    fun readInt(message: String): Int {
        console.printTitleMessage(message)
        val integerValue = scanner.nextInt()
        scanner.nextLine()
        return integerValue
    }

    fun readCurrency(): MoneyEntity.Currency {
        val currencies = mapOf(
            1 to MoneyEntity.Currency.USD,
            2 to MoneyEntity.Currency.EUR,
            3 to MoneyEntity.Currency.EGP,
            4 to MoneyEntity.Currency.EGP,

            )
        while (true) {
            console.printTitleMessage(
                "Enter number of currency:\n" +
                        "1- USD, 2- EUR, 3- EGP, 4- IQD: "
            )
            val input = scanner.nextLine().trim()
            val currencyInput = input.toIntOrNull()
            currencies[currencyInput]?.let { return it }
            console.printErrorMessage("❌ Invalid currency. Please try again.")
        }
    }

    fun readCategory(): CategoryEntity {
        val categories = mapOf(
            1 to CategoryEntity.Food,
            2 to CategoryEntity.Rent,
            3 to CategoryEntity.Taxes,
            4 to CategoryEntity.Salary,
            5 to CategoryEntity.PhoneBill,
            6 to CategoryEntity.DentalInsurance,
            7 to CategoryEntity.Electricity,
            8 to CategoryEntity.HealthInsurance,
            9 to CategoryEntity.Internet,
            10 to CategoryEntity.Water
        )
        while (true) {
            console.printTitleMessage(
                "Enter category name:{" +
                        "   1- Food,\n" +
                        "   2- Rent,\n" +
                        "   3- Salary,\n" +
                        "   4- Taxes,\n" +
                        "   5- HealthInsurance,\n" +
                        "   6- DentalInsurance,\n" +
                        "   7- Electricity,\n" +
                        "   8- Water,\n" +
                        "   9- Internet,\n" +
                        "   10- PhoneBill," +
                        "} "
            )

            val category = scanner.nextInt()
            scanner.nextLine()
            categories[category]?.let { return it  }
            console.printErrorMessage(
                "❌ Invalid Category.")
                }
            }


    fun readDescription(): String {
        console.printTitleMessage("Enter description: ")
        val description = scanner.nextLine()
        return description
    }

    fun readTransactionType(): TransactionEntity.Type {
        while (true) {
            console.printTitleMessage(
                "Enter type number op type  (\n" +
                        "1- INCOME or \n" +
                        "2- EXPENSE\n" +
                        "): \n"
            )
            val typeInput = scanner.nextInt()
            return when (typeInput) {
                1 -> TransactionEntity.Type.INCOME
                2 -> TransactionEntity.Type.EXPENSE
                else -> {
                    console.printErrorMessage("❌ Invalid type. Please enter INCOME or EXPENSE.")
                    continue
                }
            }
        }
    }
}