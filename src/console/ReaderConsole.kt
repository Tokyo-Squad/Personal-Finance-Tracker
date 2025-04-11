package console

import entity.CategoryEntity
import entity.MoneyEntity
import entity.TransactionEntity
import utils.checkCategoryType
import java.util.Scanner

class ReaderConsole(private val scanner: Scanner, private val console: PrinterConsole) {

    fun readDouble(message: String ): Double{
        console.printTitleMessage(message)
        val amount = scanner.nextDouble()
        scanner.nextLine()
        return amount
    }

    fun readInt(message: String): Int{
        console.printTitleMessage(message)
        val integerValue = scanner.nextInt()
        scanner.nextLine()
        return integerValue

    }

    fun readCurrency(): MoneyEntity.Currency {
        while (true) {
            console.printTitleMessage(
                "Enter currency (e.g.,\n" +
                        "    USD,\n" +
                        "    EUR,\n" +
                        "    EGP,\n" +
                        "    IQD): "
            )

            val currencyInput = scanner.nextLine().uppercase()
            try {
                return MoneyEntity.Currency.valueOf(currencyInput)
            } catch (e: IllegalArgumentException) {
                console.printErrorMessage(
                    "❌ Invalid currency. Please enter one of:\n" +
                            "    USD, EUR, EGP, IQD."
                )
            }
        }
    }

    fun readCategory(): CategoryEntity{

        console.printTitleMessage("Enter category name:{" +
                " Food,\n" +
                "    Rent,\n" +
                "    Salary,\n" +
                "    Taxes,\n" +
                "    HealthInsurance,\n" +
                "    DentalInsurance,\n" +
                "    Electricity,\n" +
                "    Water,\n" +
                "    Internet,\n" +
                "    PhoneBill," +
                "} ")

        val category = scanner.nextLine()
        val categoryName = checkCategoryType(category)
        return categoryName
    }

    fun readDescription(): String{
        console.printTitleMessage("Enter description: ")
        val description = scanner.nextLine()
        return description
    }

    fun readTransactionType(): TransactionEntity.Type{
        while (true) {
            console.printTitleMessage("Enter type (INCOME or EXPENSE): ")
            val typeInput = scanner.nextLine().uppercase()
            try {
                return TransactionEntity.Type.valueOf(typeInput)
            } catch (e: IllegalArgumentException) {
                console.printErrorMessage("❌ Invalid type. Please enter INCOME or EXPENSE.")
            }
        }
    }




}