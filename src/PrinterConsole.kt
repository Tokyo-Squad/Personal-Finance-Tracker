import entity.TransactionEntity
import utils.withGreenColor
import utils.withRedColor

class PrinterConsole {

    fun printWelcomeMessage(){
        println("Welcome to the Transaction Manager")
    }

    fun printMenuList(){
        println("\nMenu:")
        println("1. Add a new transaction")
        println("2. Show monthly summary")
        println("3. Update Specific Transaction")
        println("4. Delete Specific Transaction")
        println("5. Get All Transaction")
        println("6. Exit")
        print("Please choose an option (1/2/3/4/5/6): ")
    }

    fun printTitleMessage(message: String){
        print("$message : ")
    }

    fun printErrorMessage(errorMessage: String){
        println(errorMessage.withRedColor())
    }

    fun printSuccessOperation(descriptionOperation: String){
        println(descriptionOperation.withGreenColor())
    }

    fun printAllTransaction(transactions: List<TransactionEntity>){
        println(transactions)
    }

}