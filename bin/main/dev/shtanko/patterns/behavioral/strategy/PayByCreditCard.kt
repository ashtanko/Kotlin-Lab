/*
 * Copyright 2022 Oleksii Shtanko
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
import java.io.IOException
import java.io.InputStreamReader

/**
 * Concrete strategy. Implements credit card payment method.
 */
interface InputReader {
    fun readLine(): String?
}

class ConsoleInputReader : InputReader {
    private val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    override fun readLine(): String? {
        return bufferedReader.readLine()
    }
}

class NoInputException : IOException("No input")

class PayByCreditCard(private val inputReader: InputReader = ConsoleInputReader()) : PayStrategy {
    private lateinit var card: CreditCard

    /**
     * Collect credit card data.
     */
    override fun collectPaymentDetails() {
        try {
            print("Enter the card number: ")
            val number = inputReader.readLine() ?: throw NoInputException()
            print("Enter the card expiration date 'mm/yy': ")
            val date = inputReader.readLine() ?: throw NoInputException()
            print("Enter the CVV code: ")
            val cvv = inputReader.readLine() ?: throw NoInputException()
            card = CreditCard(number, date, cvv)

            // Validate credit card number...
        } catch (ex: NoInputException) {
            ex.printStackTrace()
        }
    }

    /**
     * After card validation, we can charge the customer's credit card.
     */
    override fun pay(paymentAmount: Int): Boolean {
        return if (cardIsPresent()) {
            println("Paying $$paymentAmount using Credit Card.")
            card.amount -= paymentAmount
            true
        } else {
            false
        }
    }

    private fun cardIsPresent(): Boolean {
        return ::card.isInitialized
    }
}
