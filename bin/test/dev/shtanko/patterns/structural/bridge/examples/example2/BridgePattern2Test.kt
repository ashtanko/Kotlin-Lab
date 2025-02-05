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

package dev.shtanko.patterns.structural.bridge.examples.example2

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BridgePattern2Test {
    private val originalOut = System.out
    private val outputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUpStreams() {
        System.setOut(PrintStream(outputStream))
    }

    @Test
    fun testRedCircle() {
        val redCircle = Circle(RedColor())
        redCircle.draw()

        val expectedOutput = "Drawing a circle\nApplying red color\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testBlueSquare() {
        val blueSquare = Square(BlueColor())
        blueSquare.draw()

        val expectedOutput = "Drawing a square\nApplying blue color\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testRedSquare() {
        val redSquare = Square(RedColor())
        redSquare.draw()

        val expectedOutput = "Drawing a square\nApplying red color\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testBlueCircle() {
        val blueCircle = Circle(BlueColor())
        blueCircle.draw()

        val expectedOutput = "Drawing a circle\nApplying blue color\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testMultipleShapes() {
        val redCircle = Circle(RedColor())
        val blueSquare = Square(BlueColor())

        redCircle.draw()
        blueSquare.draw()

        val expectedOutput = "Drawing a circle\nApplying red color\n" +
            "Drawing a square\nApplying blue color\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testMultipleColors() {
        val redSquare = Square(RedColor())
        val blueCircle = Circle(BlueColor())

        redSquare.draw()
        blueCircle.draw()

        val expectedOutput = "Drawing a square\nApplying red color\n" +
            "Drawing a circle\nApplying blue color\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testNoColor() {
        val shape = Circle(
            object : Color {
                override fun applyColor() {
                    // No color implementation
                }
            },
        )

        shape.draw()

        val expectedOutput = "Drawing a circle\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testNoShape() {
        val color = RedColor()

        val shape = object : Shape(color) {
            override fun draw() {
                // No shape implementation
            }
        }

        shape.draw()

        val expectedOutput = ""
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testNoShapeAndColor() {
        val shape = object : Shape(
            object : Color {
                override fun applyColor() {
                    // No color implementation
                }
            },
        ) {
            override fun draw() {
                // No shape implementation
            }
        }

        shape.draw()

        val expectedOutput = ""
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testMixedShapesAndColors() {
        val redCircle = Circle(RedColor())
        val blueSquare = Square(BlueColor())

        blueSquare.draw()
        redCircle.draw()

        val expectedOutput = "Drawing a square\nApplying blue color\n" +
            "Drawing a circle\nApplying red color\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testMixedColorsAndShapes() {
        val redSquare = Square(RedColor())
        val blueCircle = Circle(BlueColor())

        blueCircle.draw()
        redSquare.draw()

        val expectedOutput = "Drawing a circle\nApplying blue color\n" +
            "Drawing a square\nApplying red color\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    // Restore the original System.out
    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
    }
}
