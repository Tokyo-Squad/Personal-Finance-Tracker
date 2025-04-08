package entity.monthreport

import entity.Transaction

class MonthlyReportPrinter(
    private val transactions: List<Transaction>,
    private val report: MonthReport
) {

}