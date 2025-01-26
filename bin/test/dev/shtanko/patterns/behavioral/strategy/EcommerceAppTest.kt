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

package dev.shtanko.patterns.behavioral.strategy

import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EcommerceAppTest {
    private lateinit var outContent: ByteArrayOutputStream
    private lateinit var originalOut: PrintStream

    @BeforeEach
    fun setUp() {
        outContent = ByteArrayOutputStream()
        originalOut = System.out
        System.setOut(PrintStream(outContent))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
    }

    @Test
    fun testSelectProduct() {
        val input = "1\n"
        val reader = BufferedReader(StringReader(input))

        val product = EcommerceApp.selectProduct(reader)

        assertEquals(1, product?.id)
        assertEquals("Mother board", product?.name)
        assertEquals(2200, product?.price)
    }

    @Test
    fun testGetProductQuantity() {
        val input = "5\n"
        val reader = BufferedReader(StringReader(input))

        val quantity = EcommerceApp.getProductQuantity(reader)

        assertEquals(5, quantity)
    }

    @Test
    fun testAskContinueShopping() {
        val input = "Y\n"
        val reader = BufferedReader(StringReader(input))

        val result = EcommerceApp.askContinueShopping(reader)

        assertTrue(result)
    }

    @Test
    fun testChoosePaymentMethod() {
        val input = "2\n"
        val reader = BufferedReader(StringReader(input))

        val strategy = EcommerceApp.choosePaymentMethod(reader)

        assertTrue(strategy is PayByCreditCard)
    }

    @Test
    fun testProcessOrder() {
        val input = """
            1
            2
            N
            1
            P
        """.trimIndent()
        val reader = BufferedReader(StringReader(input))

        val order = Order()
        EcommerceApp.processOrder(reader)

        // Output tests to check console messages
        val output = outContent.toString()
        assertTrue(output.contains("Please, select a product:"))
        assertTrue(output.contains("Please, select a payment method:"))

        assertFalse(order.isClosed)
    }
}
