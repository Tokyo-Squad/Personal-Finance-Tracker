package entity

import java.io.Serializable
import java.time.LocalDateTime

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
    val date: LocalDateTime,
    val money: Money,
    val category: Categories,
    val description: String?,
    val type: Type
): Serializable
{
    enum class Type {
        INCOME,
        EXPENSE
    }
}
