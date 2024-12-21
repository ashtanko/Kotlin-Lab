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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LibraryTest {

    @Test
    fun `Adding a book increases the book count`() {
        val library = Library()
        val book = Book("Title", "Author", "ISBN", "Genre")
        library.addBook(book)
        assertThat(library.getBookCount()).isEqualTo(1)
    }

    @Test
    fun `Removing a book decreases the book count`() {
        val library = Library()
        val book = Book("Title", "Author", "ISBN", "Genre")
        library.addBook(book)
        library.removeBook("ISBN")
        assertThat(library.getBookCount()).isEqualTo(0)
    }

    @Test
    fun `Adding a patron increases the patron count`() {
        val library = Library()
        val patron = Patron("Name", 30, "Email")
        library.addPatron(patron)
        assertThat(library.getPatronCount()).isEqualTo(1)
    }

    @Test
    fun `Removing a patron decreases the patron count`() {
        val library = Library()
        val patron = Patron("Name", 30, "Email")
        library.addPatron(patron)
        library.removePatron(patron.id)
        assertThat(library.getPatronCount()).isEqualTo(0)
    }
}
