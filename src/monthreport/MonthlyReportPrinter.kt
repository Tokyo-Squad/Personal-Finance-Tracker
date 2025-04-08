package monthreport

import entity.MonthReport
import entity.Transaction

class MonthlyReportPrinter(
    private val transactions: List<Transaction>,
    private val report: MonthReport
) {

}