package transaction

import java.io.File

class FileTransaction (private val file: File = File("transactions.json")): Transaction {
    override fun add(transaction: TransactionEntity): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<TransactionEntity> {
        TODO("Not yet implemented")
    }

    override fun update(transaction: TransactionEntity): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}