package transaction

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileTransaction (private val file: File = File("transactions.json")): Transaction {
    // Add a new transaction to the file (append mode)
    override fun add(transaction: TransactionEntity): Boolean {
        return try {
            // Read the existing transactions from the file, or initialize an empty list
            val transactions = getAll().toMutableList()

            // Add the new transaction
            transactions.add(transaction)

            // Serialize and write the updated list back to the file
            ObjectOutputStream(FileOutputStream(file)).use { outputStream ->
                outputStream.writeObject(transactions) // Write the whole list
            }

            true // Successfully added
        } catch (e: IOException) {
            e.printStackTrace()  // Handle exceptions if file operations fail
            false  // Failed to add transaction
        }
    }

    // Get all transactions from the file
    override fun getAll(): List<TransactionEntity> {
        return try {
            // Read the transactions from the file if available
            if (file.exists() && file.length() > 0) {
                ObjectInputStream(FileInputStream(file)).use { inputStream ->
                    val transactions = inputStream.readObject()

                    // Check if the object is a List, and return it as a List<TransactionEntity>
                    if (transactions is List<*>) {
                        transactions.filterIsInstance<TransactionEntity>() // Safely cast to List<TransactionEntity>
                    } else {
                        emptyList() // If it's not a List, return an empty list
                    }
                }
            } else {
                emptyList() // If the file doesn't exist or is empty, return an empty list
            }
        } catch (e: FileNotFoundException) {
            emptyList() // File doesn't exist, return empty list
        } catch (e: IOException) {
            emptyList() // IOException occurred, return empty list
        } catch (e: ClassNotFoundException) {
            emptyList() // ClassNotFoundException occurred, return empty list
        }
    }

    override fun update(transaction: TransactionEntity): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}
