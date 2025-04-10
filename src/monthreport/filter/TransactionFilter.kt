package monthreport.filter

import entity.TransactionEntity


interface TransactionFilter {
    fun byMonth(transactions: List<TransactionEntity>, month: Int): List<TransactionEntity>?
    fun byIncomeType(transactions: List<TransactionEntity>): List<TransactionEntity>
    fun byExpenseType(transactions: List<TransactionEntity>): List<TransactionEntity>
}