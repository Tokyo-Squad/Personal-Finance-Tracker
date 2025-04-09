package monthreport

import entity.MonthReportEntity
import entity.TransactionEntity

class MonthlyReportPrinter(
    private val transactions: List<TransactionEntity>,
    private val report: MonthReportEntity
) {

}