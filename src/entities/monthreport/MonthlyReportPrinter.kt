package entities.monthreport

import entities.Transaction

class MonthlyReportPrinter(
    private val transactions: List<Transaction>,
    private val report: MonthReport
) {

}