package monthreport.filter

import entity.Transaction


interface TransactionFilter {
    fun byMonth(transactions: List<Transaction>, month: Int): List<Transaction>
}