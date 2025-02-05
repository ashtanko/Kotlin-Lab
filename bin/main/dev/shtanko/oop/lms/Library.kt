/*
 * Copyright 2024 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.oop.lms

/**
 * Represents a library which can manage books and patrons.
 */
class Library {
    // List of books in the library
    private val books = mutableListOf<Book>()

    // List of patrons registered with the library
    private val patrons = mutableListOf<Patron>()

    /**
     * Adds a book to the library.
     * @param book The book to be added.
     */
    fun addBook(book: Book) {
        books.add(book)
    }

    /**
     * Removes a book from the library using its ISBN.
     * @param isbn The ISBN of the book to be removed.
     */
    fun removeBook(isbn: String) {
        val bookToRemove = books.find { it.isbn == isbn }
        bookToRemove?.let { books.remove(it) }
    }

    /**
     * Adds a patron to the library.
     * @param patron The patron to be added.
     */
    fun addPatron(patron: Patron) {
        patrons.add(patron)
    }

    /**
     * Removes a patron from the library using its ID.
     * @param id The ID of the patron to be removed.
     */
    fun removePatron(id: String) {
        val patronToRemove = patrons.find { it.id == id }
        patronToRemove?.let { patrons.remove(it) }
    }

    /**
     * Returns the count of books in the library.
     * @return The count of books.
     */
    fun getBookCount(): Int {
        return books.size
    }

    /**
     * Returns the count of patrons registered with the library.
     * @return The count of patrons.
     */
    fun getPatronCount(): Int {
        return patrons.size
    }
}
