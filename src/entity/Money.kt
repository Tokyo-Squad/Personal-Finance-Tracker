package entity


data class Money(
    val amount: Double,
    val currencyType: Currency = Currency.USD
){
enum class Currency {
    USD,
    EUR,
    EGP,
    IQD
}
}
