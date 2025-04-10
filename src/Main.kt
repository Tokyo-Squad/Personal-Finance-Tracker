import entity.CategoryEntity
import entity.MoneyEntity
import entity.TransactionEntity
import transaction.FileTransaction
import transaction.Transaction

import java.io.File
import java.io.Serializable
import java.time.LocalDateTime

fun main() {
    // Initialize repository
    val transactionRepo = FileTransaction()

    // Test data
    val testCategory = CategoryEntity.Salary // Assuming you have this class
    val testMoney = MoneyEntity(100.0, MoneyEntity.Currency.IQD) // Assuming you have this class
    val currentDate = LocalDateTime.now()

    // Test 1: Add transaction
    val transaction1 = TransactionEntity(
        id = 1,
        date = currentDate,
        money = testMoney,
        category = testCategory,
        description = "Dinner at restaurant",
        type = TransactionEntity.Type.EXPENSE
    )
    println("Adding transaction 1: ${transactionRepo.add(transaction1)}")

    // Test 2: Add another transaction
    val transaction2 = TransactionEntity(
        id = 2,
        date = currentDate.plusDays(1),
        money = MoneyEntity(2000.0, MoneyEntity.Currency.EGP),
        category =  CategoryEntity.Salary,
        description = "Monthly salary",
        type = TransactionEntity.Type.INCOME
    )
    println("Adding transaction 2: ${transactionRepo.add(transaction2)}")

    // Test 3: Get all transactions
    println("\nAll transactions:")
    transactionRepo.getAll().forEachIndexed { index, transaction ->
        println("${index + 1}. $transaction")
    }

    // Test 4: Update transaction
    val updatedTransaction = transaction1.copy(
        description = "Dinner at fancy restaurant",
        money = MoneyEntity(150.0,  MoneyEntity.Currency.USD)
    )
    println("\nUpdating transaction 1: ${transactionRepo.update(updatedTransaction)}")

    // Verify update
    println("\nAfter update:")
    transactionRepo.getAll().forEachIndexed { index, transaction ->
        println("${index + 1}. $transaction")
    }

    // Test 5: Delete transaction
    println("\nDeleting transaction 2: ${transactionRepo.delete(2)}")

    // Final state
    println("\nFinal transactions:")
    transactionRepo.getAll().forEachIndexed { index, transaction ->
        println("${index + 1}. $transaction")
    }

    // Test 6: Verify file persistence
    println("\nReloading from file to verify persistence:")
    val newRepoInstance = FileTransaction()
    newRepoInstance.getAll().forEachIndexed { index, transaction ->
        println("${index + 1}. $transaction")
    }


}
