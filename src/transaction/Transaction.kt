package transaction

import entity.TransactionEntity



interface Transaction {
    fun add(transaction: TransactionEntity): Boolean
    fun getAll(): List<TransactionEntity>
    fun update(transaction: TransactionEntity): Boolean
    fun delete(id: Int): Boolean
}