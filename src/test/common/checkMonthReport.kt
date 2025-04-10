package test.common

import entity.MonthReportEntity


fun <T> check(name: String, result: T, correctResult: T) {
    if (result == correctResult) {
        println("Success: $name")
    } else {
        println("Fail: $name")
    }
}