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
import java.io.InputStreamReader

data class Product(val id: Int, val name: String, val price: Int)

object EcommerceApp {

    private val products = listOf(
        Product(1, "Mother board", 2200),
        Product(2, "CPU", 1850),
        Product(3, "HDD", 1100),
        Product(4, "Memory", 890),
    )

    private val order = Order()
    private lateinit var strategy: PayStrategy

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = BufferedReader(InputStreamReader(System.`in`))

        while (!order.isClosed) {
            processOrder(reader)
        }
    }

    fun processOrder(reader: BufferedReader) {
        var continueShopping: Boolean
        do {
            val selectedProduct = selectProduct(reader)
            val quantity = getProductQuantity(reader)
            order.totalCost += selectedProduct.price * quantity

            continueShopping = askContinueShopping(reader)
        } while (continueShopping)

        strategy = choosePaymentMethod(reader)

        order.processOrder(strategy)
    }

    fun selectProduct(reader: BufferedReader): Product {
        println("Please, select a product:")
        products.forEach { println("${it.id} - ${it.name}") }

        val choice = reader.readLine().toInt()
        return products.firstOrDefault({ it.id == choice }, products.first())
    }

    fun getProductQuantity(reader: BufferedReader): Int {
        print("Count: ")
        return reader.readLine().toInt()
    }

    fun askContinueShopping(reader: BufferedReader): Boolean {
        print("Do you wish to continue selecting products? Y/N: ")
        val answer = reader.readLine()
        return answer.equals("Y", ignoreCase = true)
    }

    fun choosePaymentMethod(reader: BufferedReader): PayStrategy {
        println("Please, select a payment method:")
        println("1 - PayPal")
        println("2 - Credit Card")

        val paymentMethod = reader.readLine()

        return if (paymentMethod == "1") {
            PayByPayPal()
        } else {
            PayByCreditCard()
        }
    }
}

inline fun <T> Iterable<T>.firstOrDefault(predicate: (T) -> Boolean, default: T): T {
    for (element in this) {
        if (predicate(element)) {
            return element
        }
    }
    return default
}
