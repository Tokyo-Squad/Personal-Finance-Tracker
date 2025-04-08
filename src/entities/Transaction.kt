package entities

data class Transaction(
    val id :Int,
    val amount :Float,
    val category: String,
)
