package transaction

import entity.TransactionEntity
import java.time.LocalDateTime


/**
 * An in-memory implementation of the Transaction interface.
 *
 * This class stores TransactionEntity objects in a mutable list and provides simple CRUD operations.
 * Note: Data is not persisted beyond the application runtime.
 *
 * Provided functionalities:
 * - add(transaction): Inserts a new transaction.
 * - getAll(): Returns all stored transactions.
 * - update(transaction): Updates an existing transaction by matching its ID.
 * - delete(id): Removes a transaction with the given ID.
 */
class InMemoryTransaction : Transaction {

    private val transactions = mutableListOf<TransactionEntity>()

    override fun add(transaction: TransactionEntity): Boolean {
        // Check for duplicate ID
        if (transactions.any { it.id == transaction.id }) {
            return false
        }
        // Validate non-negative amount
        if (transaction.money.amount < 0){
            return false
        }
        // Prevent future dates
        if (transaction.date > LocalDateTime.now()){
            return false
        }
        transactions.add(transaction)
        return true
    }

    override fun getAll(): List<TransactionEntity> = transactions

    override fun update(transaction: TransactionEntity): Boolean {
        val index = transactions.indexOfFirst { it.id == transaction.id }
        return if (index != -1) {
            transactions[index] = transaction
            true
        } else {
            false
        }
    }

    override fun delete(id: Int): Boolean = transactions.removeIf { it.id == id }
}