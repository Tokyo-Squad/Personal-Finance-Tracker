
sealed class TransactionType{

    data class Income (val money:  Double):TransactionType()
    data class Expense (val money: Double):TransactionType()

}