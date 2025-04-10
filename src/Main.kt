fun main(args: Array<String>) {
    if (args.contains("test")) {  // Check for the "test" argument
        val tester = InMemoryTransactionTester()
        tester.runAllTests()
    } else {
        println("Running normal application logic...")
    }
}