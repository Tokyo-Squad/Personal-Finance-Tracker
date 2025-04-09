package transaction

import entity.TransactionEntity
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileTransaction(private val file: File = File("transactions.ser")) : Transaction {

    override fun add(transaction: TransactionEntity): Boolean {
        return try {
            val transactions = getAll().toMutableList()

            transactions.add(transaction)
            saveAll(transactions)

            true
        } catch (e: IOException) {
            println("IOException occurred while adding the transaction: ${e.message}")
            false
        } catch (e: Exception) {
            println("An unexpected error occurred: ${e.message}")
            false
        }
    }

    override fun getAll(): List<TransactionEntity> {
        return try {
            if (file.exists() && file.length() > 0) {
                ObjectInputStream(FileInputStream(file)).use { inputStream ->
                    val transactions = inputStream.readObject()

                    if (transactions is List<*>) {
                        transactions.filterIsInstance<TransactionEntity>() // Safely cast to List<TransactionEntity>
                    } else {
                        emptyList()
                    }
                }
            } else {
                emptyList()
            }
        } catch (e: FileNotFoundException) {
            println("File not found: ${file.absolutePath}")
            emptyList()
        } catch (e: IOException) {
            println("I/O error occurred while reading the file: ${e.message}")
            emptyList()
        } catch (e: ClassNotFoundException) {
            println("Class not found during deserialization: ${e.message}")
            emptyList()
        }
    }

    override fun update(transaction: TransactionEntity): Boolean {
        return try {
            val transactions = getAll().toMutableList()
            val index = transactions.indexOfFirst { it.id == transaction.id }
            if (index == -1) return false

            transactions[index] = transaction
            saveAll(transactions)
            true
        } catch (e: Exception) {
            println("Error updating transaction: ${e.message}")
            false
        }
    }

    override fun delete(id: Int): Boolean {
        return try {
            val transactions = getAll().toMutableList()
            val removed = transactions.removeIf { it.id == id }
            if (removed) {
                saveAll(transactions)
            }
            removed
        } catch (e: Exception) {
            println("Error deleting transaction ${e.message}")
            false
        }

    }

    private fun saveAll(transactions: List<TransactionEntity>) {
        ObjectOutputStream(FileOutputStream(file)).use { outputStream ->
            outputStream.writeObject(ArrayList(transactions))
        }
    }
}