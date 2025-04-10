package monthreport

import entity.MonthReportEntity

interface MonthlyReportCalculator {
    fun calculateMonthlyReport(month: Int): MonthReportEntity
}