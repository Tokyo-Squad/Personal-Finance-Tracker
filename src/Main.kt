import test.feature.InMemoryTransactionTestCases
import test.feature.monthlyReportTestCases

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        // No arguments provided, run default program logic
    } else {
        when (args[0]) {
            "test1" -> {
                val test = InMemoryTransactionTestCases()
                test.runAllTests()
            }
            "test2" -> {
                monthlyReportTestCases()
            }
            else -> {
                println("Invalid argument. Running default program logic...")
            }
        }
    }
}
