import entity.TransactionEntity
import transaction.Transaction


class TransactionManager (private val transaction: Transaction){




    fun add(transactionEntity: TransactionEntity): Boolean {

        transaction.add(transactionEntity)
        return true
    }

    fun getAll(): List<TransactionEntity> = transaction.getAll()
    fun update(transactionEntity:TransactionEntity ): Boolean {
        transaction.update(transactionEntity)
        return true
    }


    fun delete(id: Int): Boolean {
        return transaction.delete(id)
    }

    }

