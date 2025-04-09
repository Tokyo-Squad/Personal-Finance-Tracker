package transaction

import entity.Transaction

typealias TransactionEntity = Transaction


interface Transaction {
    fun add(transaction: TransactionEntity): Boolean
    fun getAll(): List<TransactionEntity>
    fun update(transaction: TransactionEntity): Boolean
    fun delete(id: Int): Boolean
}