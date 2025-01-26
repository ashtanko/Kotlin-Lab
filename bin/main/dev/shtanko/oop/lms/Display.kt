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
 * Represents a display for books and patrons.
 *
 * @property books The list of books to be displayed.
 * @property patrons The list of patrons to be displayed.
 */
class Display(private val books: List<Book>, private val patrons: List<Patron>) {

    /**
     * Displays the list of books.
     * If no books are available, it prints a message indicating the same.
     */
    fun displayBooks() {
        if (books.isEmpty()) {
            println("No books available in the library.")
            return
        }
        println("Books available in the library:")
        books.forEach { println(it) }
    }

    /**
     * Displays the list of patrons.
     * If no patrons are registered, it prints a message indicating the same.
     */
    fun displayPatrons() {
        if (patrons.isEmpty()) {
            println("No patrons registered with the library.")
            return
        }
        println("Patrons registered with the library:")
        patrons.forEach { println(it) }
    }
}
