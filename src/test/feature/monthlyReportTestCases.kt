package test.feature

import entity.CategoryEntity
import entity.MoneyEntity
import entity.MonthReportEntity
import entity.TransactionEntity
import monthreport.MonthlyReportCalculatorImp
import monthreport.filter.TransactionFilterImp
import java.time.LocalDateTime

fun monthlyReportTestCases() {
    val filter = TransactionFilterImp()
    val currentMonth = LocalDateTime.now().monthValue

    // Test 1: Mixed income and expenses (existing)
    val test1 = listOf(
        TransactionEntity(id = 1, date = LocalDateTime.now(), money = MoneyEntity(50.0),
            category = CategoryEntity.Food, type = TransactionEntity.Type.INCOME, description = null),
        TransactionEntity(id = 2, date = LocalDateTime.now(), money = MoneyEntity(90.0),
            category = CategoryEntity.Food, type = TransactionEntity.Type.INCOME, description = null),
        TransactionEntity(id = 3, date = LocalDateTime.now(), money = MoneyEntity(90.0),
            category = CategoryEntity.Food, type = TransactionEntity.Type.EXPENSE, description = null)
    )
    val monthReport1 = MonthlyReportCalculatorImp(test1, filter)
    test.common.check(
        name = "Mixed income and expenses",
        result = monthReport1.calculateMonthlyReport(currentMonth),
        correctResult = MonthReportEntity(140.0, 90.0, 50.0, currentMonth)
    )

    // Test 2: Negative balance (corrected from original)
    val test2 = listOf(
        TransactionEntity(id = 1, date = LocalDateTime.now(), money = MoneyEntity(50.0),
            category = CategoryEntity.Food, type = TransactionEntity.Type.INCOME, description = "Salary"),
        TransactionEntity(id = 2, date = LocalDateTime.now(), money = MoneyEntity(90.0),
            category = CategoryEntity.Salary, type = TransactionEntity.Type.EXPENSE, description = "Rent"),
        TransactionEntity(id = 3, date = LocalDateTime.now(), money = MoneyEntity(90.0),
            category = CategoryEntity.Rent, type = TransactionEntity.Type.EXPENSE, description = "Electricity")
    )
    val monthReport2 = MonthlyReportCalculatorImp(test2, filter)
    test.common.check(
        name = "Negative balance scenario",
        result = monthReport2.calculateMonthlyReport(currentMonth),
        correctResult = MonthReportEntity(50.0, 180.0, 0.0, currentMonth)
    )

    // Test 3: Different categories with descriptions
    val test3 = listOf(
        TransactionEntity(id = 1, date = LocalDateTime.now(), money = MoneyEntity(150.0),
            category = CategoryEntity.Salary, type = TransactionEntity.Type.INCOME, description = "Main job"),
        TransactionEntity(id = 2, date = LocalDateTime.now(), money = MoneyEntity(50.0),
            category = CategoryEntity.Taxes, type = TransactionEntity.Type.INCOME, description = "Side project"),
        TransactionEntity(id = 3, date = LocalDateTime.now(), money = MoneyEntity(75.0),
            category = CategoryEntity.DentalInsurance, type = TransactionEntity.Type.EXPENSE, description = "Movies")
    )
    val monthReport3 = MonthlyReportCalculatorImp(test3, filter)
    test.common.check(
        name = "Different categories with descriptions",
        result = monthReport3.calculateMonthlyReport(currentMonth),
        correctResult = MonthReportEntity(200.0, 75.0, 125.0, currentMonth)
    )

    // Test 4: Zero-value transactions
    val test4 = listOf(
        TransactionEntity(id = 1, date = LocalDateTime.now(), money = MoneyEntity(0.0),
            category = CategoryEntity.PhoneBill, type = TransactionEntity.Type.INCOME, description = "Gift"),
        TransactionEntity(id = 2, date = LocalDateTime.now(), money = MoneyEntity(0.0),
            category = CategoryEntity.Rent, type = TransactionEntity.Type.EXPENSE, description = "Return")
    )
    val monthReport4 = MonthlyReportCalculatorImp(test4, filter)
    test.common.check(
        name = "Zero-value transactions",
        result = monthReport4.calculateMonthlyReport(currentMonth),
        correctResult = MonthReportEntity(0.0, 0.0, 0.0, currentMonth)
    )

    // Test 5: Large number of transactions
    val test5 = List(100) { index ->
        if (index % 2 == 0) {
            TransactionEntity(id = index, date = LocalDateTime.now(), money = MoneyEntity(100.0),
                category = CategoryEntity.entries.toTypedArray().random(), type = TransactionEntity.Type.INCOME,
                description = "Income $index")
        } else {
            TransactionEntity(id = index, date = LocalDateTime.now(), money = MoneyEntity(50.0),
                category = CategoryEntity.entries.toTypedArray().random(), type = TransactionEntity.Type.EXPENSE,
                description = "Expense $index")
        }
    }
    val monthReport5 = MonthlyReportCalculatorImp(test5, filter)
    test.common.check(
        name = "Large number of transactions",
        result = monthReport5.calculateMonthlyReport(currentMonth),
        correctResult = MonthReportEntity(5000.0, 2500.0, 2500.0, currentMonth)
    )

    // Test 6: Different months filtering
    val test6 = listOf(
        TransactionEntity(id = 1, date = LocalDateTime.now().withMonth(1), money = MoneyEntity(100.0),
            category = CategoryEntity.Salary, type = TransactionEntity.Type.INCOME, description = "January"),
        TransactionEntity(id = 2, date = LocalDateTime.now().withMonth(currentMonth), money = MoneyEntity(200.0),
            category = CategoryEntity.Salary, type = TransactionEntity.Type.INCOME, description = "Current"),
        TransactionEntity(id = 3, date = LocalDateTime.now().withMonth(currentMonth), money = MoneyEntity(50.0),
            category = CategoryEntity.Food, type = TransactionEntity.Type.EXPENSE, description = "Groceries")
    )
    val monthReport6 = MonthlyReportCalculatorImp(test6, filter)
    test.common.check(
        name = "Different months filtering",
        result = monthReport6.calculateMonthlyReport(currentMonth),
        correctResult = MonthReportEntity(200.0, 50.0, 150.0, currentMonth)
    )


    // Test 7: Empty transaction list
    val test7 = emptyList<TransactionEntity>()
    val monthReport7 = MonthlyReportCalculatorImp(test7, filter)
    test.common.check(
        name = "Empty transaction list",
        result = monthReport7.calculateMonthlyReport(currentMonth),
        correctResult = MonthReportEntity(0.0, 0.0, 0.0, currentMonth)
    )
}
