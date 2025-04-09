package monthreport

import entity.MonthReport

interface MonthlyReportCalculator {
    fun calculateMonthlyReport(month: Int): MonthReport
}