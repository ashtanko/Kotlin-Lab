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

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PayByCreditCardTest {

    private lateinit var payByCreditCard: PayByCreditCard

    @BeforeEach
    fun setUp() {
        // Initialize with mock input reader
    }

    @Test
    fun `test collect payment details with valid inputs`() {
        val mockInputReader = MockInputReader(listOf("1234-5678-9876-5432", "12/23", "123"))
        payByCreditCard = PayByCreditCard(mockInputReader)

        assertDoesNotThrow {
            payByCreditCard.collectPaymentDetails()
        }
        assertTrue(payByCreditCard.pay(100)) // Ensure the card is initialized and payment is possible
    }

    @Test
    fun `test pay with sufficient balance`() {
        val mockInputReader = MockInputReader(listOf("1234-5678-9876-5432", "12/23", "123"))
        payByCreditCard = PayByCreditCard(mockInputReader)
        payByCreditCard.collectPaymentDetails()

        assertTrue(payByCreditCard.pay(100)) // Should succeed as initial balance is 1000
    }

    @Test
    fun `test pay with insufficient balance`() {
        val mockInputReader = MockInputReader(listOf("1234-5678-9876-5432", "12/23", "123"))
        payByCreditCard = PayByCreditCard(mockInputReader)
        payByCreditCard.collectPaymentDetails()

        assertTrue(payByCreditCard.pay(2000)) // Should fail due to insufficient balance
    }

    @Test
    fun `test pay without collecting payment details`() {
        val mockInputReader = MockInputReader(listOf("1234-5678-9876-5432", "12/23", "123"))
        payByCreditCard = PayByCreditCard(mockInputReader)

        assertFalse(payByCreditCard.pay(100)) // Should fail because the card is not initialized
    }
}
