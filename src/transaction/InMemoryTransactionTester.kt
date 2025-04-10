import entity.CategoryEntity
import entity.MoneyEntity
import entity.TransactionEntity
import transaction.InMemoryTransaction
import transaction.Transaction
import java.time.LocalDateTime

class InMemoryTransactionTester {
    private lateinit var transaction: Transaction
    private var testsPassed = 0
    private var testsFailed = 0

    private fun assert(condition: Boolean, testName: String) {
        if (condition) {
            println("✅ Test Passed: $testName")
            testsPassed++
        } else {
            println("❌ Test Failed: $testName")
            testsFailed++
        }
    }

    fun setUp() {
        transaction = InMemoryTransaction()
    }

    private fun createSampleTransaction(
        id: Int,
        amount: Double,
        type: TransactionEntity.Type = TransactionEntity.Type.EXPENSE
    ): TransactionEntity {
        return TransactionEntity(
            id = id,
            date = LocalDateTime.now(),
            money = MoneyEntity(amount),
            category = CategoryEntity.Food,
            description = "Test transaction",
            type = type
        )
    }

    fun testAddTransaction() {
        val expenseTransaction = createSampleTransaction(1, 100.0, TransactionEntity.Type.EXPENSE)
        assert(transaction.add(expenseTransaction), "Add valid expense transaction")

        val incomeTransaction = createSampleTransaction(2, 500.0, TransactionEntity.Type.INCOME)
        assert(transaction.add(incomeTransaction), "Add valid income transaction")

        val nullDescTransaction = TransactionEntity(
            id = 3,
            date = LocalDateTime.now(),
            money = MoneyEntity(100.0),
            category = CategoryEntity.Water,
            description = null,
            type = TransactionEntity.Type.EXPENSE
        )
        assert(transaction.add(nullDescTransaction), "Add transaction with null description")

        val duplicateTransaction = createSampleTransaction(1, 200.0)
        assert(!transaction.add(duplicateTransaction), "Add duplicate transaction ID")
    }

    fun testGetAllTransactions() {

        val transactions = listOf(
            createSampleTransaction(1, 100.0, TransactionEntity.Type.EXPENSE),
            createSampleTransaction(2, 200.0, TransactionEntity.Type.INCOME),
            createSampleTransaction(3, 300.0, TransactionEntity.Type.EXPENSE)
        )

        transactions.forEach { transaction.add(it) }

        // Test getting all transactions
        val allTransactions = transaction.getAll()
        assert(allTransactions.size == 3, "Get all transactions count")

        assert(
            allTransactions.count { it.type == TransactionEntity.Type.EXPENSE } == 2,
            "Correct number of expense transactions"
        )
        assert(
            allTransactions.count { it.type == TransactionEntity.Type.INCOME } == 1,
            "Correct number of income transactions"
        )
    }

    fun testUpdateTransaction() {
        val initialTransaction = createSampleTransaction(1, 100.0)
        transaction.add(initialTransaction)

        val updatedAmount = TransactionEntity(
            id = 1,
            date = initialTransaction.date,
            money = MoneyEntity(100.0),
            category = initialTransaction.category,
            description = initialTransaction.description,
            type = initialTransaction.type
        )
        assert(transaction.update(updatedAmount), "Update transaction amount")

        val updatedType = initialTransaction.copy(type = TransactionEntity.Type.INCOME)
        assert(transaction.update(updatedType), "Update transaction type")

        val updatedDescription = initialTransaction.copy(description = "Updated description")
        assert(transaction.update(updatedDescription), "Update transaction description")

        val nonExistent = createSampleTransaction(999, 100.0)
        assert(!transaction.update(nonExistent), "Update non-existent transaction")
    }

    fun testDeleteTransaction() {
        val transaction1 = createSampleTransaction(1, 100.0)
        val transaction2 = createSampleTransaction(2, 200.0)

        transaction.add(transaction1)
        transaction.add(transaction2)

        assert(transaction.delete(1), "Delete existing transaction")

        assert(!transaction.delete(1), "Delete already deleted transaction")

        assert(!transaction.delete(999), "Delete non-existent transaction")

        assert(!transaction.delete(-1), "Delete with invalid ID")
    }

    fun testDateCases() {

        val futureTransaction = TransactionEntity(
            id = 3,
            date = LocalDateTime.now().plusYears(1),
            money = MoneyEntity(100.0),
            category = CategoryEntity.Food,
            description = "Future transaction",
            type = TransactionEntity.Type.EXPENSE
        )
        assert(!transaction.add(futureTransaction), "Add transaction with future date")


        val pastTransaction = TransactionEntity(
            id = 4,
            date = LocalDateTime.now().minusYears(1),
            money = MoneyEntity(100.0),
            category = CategoryEntity.Food,
            description = "Past transaction",
            type = TransactionEntity.Type.EXPENSE
        )
        assert(transaction.add(pastTransaction), "Add transaction with past date")
    }

    fun runAllTests() {
        setUp()

        println("\nRunning Transaction Tests...")
        testAddTransaction()
        testGetAllTransactions()
        testUpdateTransaction()
        testDeleteTransaction()
        testDateCases()

        println("\nTest Summary:")
        println("Total tests: ${testsPassed + testsFailed}")
        println("Passed: $testsPassed")
        println("Failed: $testsFailed")
    }
}
