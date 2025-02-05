/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.patterns.structural.composite.example1

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CompositePatternTest {
    private val originalOut = System.out
    private val outputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUpStreams() {
        System.setOut(PrintStream(outputStream))
    }

    @Test
    fun testCompositePattern() {
        val root = Directory("Root")
        val subDir1 = Directory("Subdirectory 1")
        val subDir2 = Directory("Subdirectory 2")

        val file1 = File("File 1")
        val file2 = File("File 2")

        root.add(subDir1)
        root.add(subDir2)
        subDir1.add(file1)
        subDir1.add(file2)

        root.display("")
        val expectedOutput = " Directory: Root\n" +
            "   Directory: Subdirectory 1\n" +
            "     File: File 1\n" +
            "     File: File 2\n" +
            "   Directory: Subdirectory 2\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
    }
}
