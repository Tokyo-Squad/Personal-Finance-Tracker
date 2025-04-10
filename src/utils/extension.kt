package utils

import entity.CategoryEntity

fun String.withGreenColor(): String = "\u001B[32m $this \u001B[0m"
fun String.withRedColor(): String = "\u001B[31m $this \u001B[0m"

fun checkCategoryType(categoryName: String): CategoryEntity {
    return when {

        categoryName.lowercase() == CategoryEntity.Food.name.lowercase() -> CategoryEntity.Food
        categoryName.lowercase() == CategoryEntity.Rent.name.lowercase() -> CategoryEntity.Rent
        categoryName.lowercase() == CategoryEntity.Taxes.name.lowercase() -> CategoryEntity.Taxes
        categoryName.lowercase() == CategoryEntity.Salary.name.lowercase() -> CategoryEntity.Salary
        categoryName.lowercase() == CategoryEntity.PhoneBill.name.lowercase() -> CategoryEntity.PhoneBill
        categoryName.lowercase() == CategoryEntity.DentalInsurance.name.lowercase() -> CategoryEntity.DentalInsurance
        categoryName.lowercase() == CategoryEntity.Electricity.name.lowercase() -> CategoryEntity.Electricity
        categoryName.lowercase() == CategoryEntity.HealthInsurance.name.lowercase() -> CategoryEntity.HealthInsurance
        categoryName.lowercase() == CategoryEntity.Internet.name.lowercase() -> CategoryEntity.Internet
        categoryName.lowercase() == CategoryEntity.Water.name.lowercase() -> CategoryEntity.Water
        else -> CategoryEntity.Food
    }
}