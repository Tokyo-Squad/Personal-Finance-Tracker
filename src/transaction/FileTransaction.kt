package transaction

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileTransaction (private val file: File = File("transactions.ser")): Transaction {

    override fun add(transaction: TransactionEntity): Boolean {
        return try {
            val transactions = getAll().toMutableList()

            transactions.add(transaction)

            // Serialize and write the updated list back to the file
            ObjectOutputStream(FileOutputStream(file)).use { outputStream ->
                outputStream.writeObject(transactions) // Write the whole list
            }

            true
        } catch (e: IOException) {
            // Handle general I/O errors (e.g., file not found, access errors)
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

                    // Check if the object is a List, and return it as a List<TransactionEntity>
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
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}
