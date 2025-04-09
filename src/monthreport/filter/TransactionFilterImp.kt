package monthreport.filter

import entity.Transaction

class TransactionFilterImp : TransactionFilter{
    override fun byMonth(transactions: List<Transaction>, month : Int): List<Transaction>{
        return transactions.filter { it.date.month.value == month }
    }
}