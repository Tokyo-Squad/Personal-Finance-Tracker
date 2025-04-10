package test.feature

import entity.CategoryEntity
import entity.MoneyEntity
import entity.TransactionEntity
import test.common.check
import transaction.FileTransaction
import java.io.File
import java.time.LocalDateTime


fun fileTransactionTestCases() {
    val testFile = File("temp_transactions.ser")
    testFile.delete()

    val fileTransaction = FileTransaction(testFile)

    // Test 1
    val transactionsEmpty = fileTransaction.getAll()
    check("Get all transactions when empty", transactionsEmpty.isEmpty(), true)

    // Test 2
    val transaction = TransactionEntity(
        1,
        LocalDateTime.now(),
        MoneyEntity(100.0, MoneyEntity.Currency.USD),
        CategoryEntity.Food,
        "Dinner",
        TransactionEntity.Type.EXPENSE
    )
    val resultAdd = fileTransaction.add(transaction)
    check("Add transaction", resultAdd, true)
// Test 3
    val updatedTransaction = transaction.copy(money = MoneyEntity(150.0, MoneyEntity.Currency.USD))
    val resultUpdate = fileTransaction.update(updatedTransaction)
    check("Update transaction", resultUpdate, true)
    val transactionsAfterUpdate = fileTransaction.getAll()
    check("Transaction updated", transactionsAfterUpdate.contains(updatedTransaction), true)
// Test 4
    val nonExistentTransaction = TransactionEntity(
        999,
        LocalDateTime.now(),
        MoneyEntity(100.0, MoneyEntity.Currency.USD),
        CategoryEntity.HealthInsurance,
        "Health",
        TransactionEntity.Type.EXPENSE
    )
    val resultUpdateNonExistent = fileTransaction.update(nonExistentTransaction)
    check("Update non-existent transaction", resultUpdateNonExistent, false)
// Test 5
    val resultDelete = fileTransaction.delete(1)
    check("Delete transaction", resultDelete, true)
    val transactionsAfterDelete = fileTransaction.getAll()
    check("Transaction deleted", !transactionsAfterDelete.any { it.id == 1 }, true)
// Test 6
    val resultDeleteNonExistent = fileTransaction.delete(999)
    check("Delete non-existent transaction", resultDeleteNonExistent, false)
}