import test.feature.InMemoryTransactionTestCases
import test.feature.monthlyReportTestCases

fun main(args: Array<String>) {
    if (args[0]=="test1"){
        val test = InMemoryTransactionTestCases()
        test.runAllTests()
    }
    else if (args[1] == "test2"){
        monthlyReportTestCases()
    }
    else{
        //Program logic
    }

}
