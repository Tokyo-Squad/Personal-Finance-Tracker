package console

import entity.MonthReportEntity
import entity.TransactionEntity
import monthreport.MonthlyReportCalculator
import monthreport.MonthlyReportCalculatorImp
import monthreport.filter.TransactionFilter
import monthreport.filter.TransactionFilterImp
import transaction.Transaction


class TransactionManager (
    private val transaction: Transaction,
    private val transactionFilter: TransactionFilter= TransactionFilterImp(),
    private val monthlyReportCalculator: MonthlyReportCalculator = MonthlyReportCalculatorImp(transactions = transaction.getAll(), filter = transactionFilter)
    ){

    fun add(transactionEntity: TransactionEntity): Boolean = transaction.add(transactionEntity)

    fun getMonthlyReport(month: Int): MonthReportEntity = monthlyReportCalculator.calculateMonthlyReport(month)

    fun getAll(): List<TransactionEntity> = transaction.getAll()

    fun update(transactionEntity:TransactionEntity ): Boolean = transaction.update(transactionEntity)

    fun delete(id: Int): Boolean = transaction.delete(id)


}

