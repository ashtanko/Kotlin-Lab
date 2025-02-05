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

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DisplayTest {

    @Test
    fun `displayBooks prints message when no books are available`() {
        val display = Display(emptyList(), emptyList())
        val output = captureStandardOutput {
            display.displayBooks()
        }
        assertTrue(output.contains("No books available in the library."))
    }

    @Test
    fun `displayBooks prints book details when books are available`() {
        val books = listOf(Book("Title", "Author", "ISBN", "Genre"))
        val display = Display(books, emptyList())
        val output = captureStandardOutput {
            display.displayBooks()
        }
        assertTrue(output.contains("Books available in the library:"))
        assertTrue(output.contains("Title"))
    }

    @Test
    fun `displayPatrons prints message when no patrons are registered`() {
        val display = Display(emptyList(), emptyList())
        val output = captureStandardOutput {
            display.displayPatrons()
        }
        assertTrue(output.contains("No patrons registered with the library."))
    }

    @Test
    fun `displayPatrons prints patron details when patrons are registered`() {
        val patrons = listOf(Patron("Name", 30, "Email"))
        val display = Display(emptyList(), patrons)
        val output = captureStandardOutput {
            display.displayPatrons()
        }
        assertTrue(output.contains("Patrons registered with the library:"))
        assertTrue(output.contains("Name"))
    }

    private fun captureStandardOutput(block: () -> Unit): String {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        block()
        return outputStream.toString()
    }
}
