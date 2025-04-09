package entity


data class MoneyEntity(
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
