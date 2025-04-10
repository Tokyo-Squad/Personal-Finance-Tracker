package monthreport.filter

import entity.TransactionEntity

class TransactionFilterImp : TransactionFilter{
    override fun byMonth(transactions: List<TransactionEntity>, month : Int): List<TransactionEntity>?{
        return if(month in 1..12)
            transactions.filter { it.date.month.value == month }
        else
            null
    }

    override fun byIncomeType(transactions: List<TransactionEntity>): List<TransactionEntity> {
        return transactions.filter { it.type == TransactionEntity.Type.INCOME }
    }

    override fun byExpenseType(transactions: List<TransactionEntity>): List<TransactionEntity> {
        return transactions.filter { it.type == TransactionEntity.Type.EXPENSE }
    }
}