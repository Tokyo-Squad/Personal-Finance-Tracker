package entity

import java.time.LocalDate

/**
 * @param id Transaction's unique ID.
 * @param date Date of the transaction.
 * @param money Details of the transaction amount.
 * @param category Details of the transaction category.
 * @param description Brief details of the transaction.
 * @param type Indicates if it's INCOME or EXPENSE.
 */
data class Transaction(
    val id: Int,
    val date: LocalDate,
    val money: Money,
    val category: Category,
    val description: String,
    val type: Type
) {
    /**
     * Transaction types.
     */
    enum class Type {
        INCOME,
        EXPENSE
    }
}