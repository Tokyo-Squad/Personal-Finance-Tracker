package entity

import java.time.LocalDateTime

/**
 * @param id Transaction's unique ID.
 * @param date Date of the transaction.
 * @param money Details of the transaction amount.
 * @param category Details of the transaction category.
 * @param description Brief details of the transaction.
 * @param type Indicates if it's INCOME or EXPENSE.
 */
data class TransactionEntity(
    val id: Int,
    val date: LocalDateTime,
    val money: MoneyEntity,
    val category: CategoryEntity,
    val description: String?,
    val type: Type
) {

    enum class Type {
        INCOME,
        EXPENSE
    }
}
