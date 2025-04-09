package test.common

import entity.MonthReportEntity


fun check(name: String, result: MonthReportEntity, correctResult: MonthReportEntity){
    if(result == correctResult){
        println("success : $name")
    }
    else
        println("fail : $name")
}