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

package dev.shtanko.grasp.pf.example1

class CartItem(val product: Product, val quantity: Int)

class Product(val name: String, val price: Double)

class CartCalculator {
    fun calculateTotal(cartItems: List<CartItem>): Double {
        var total = 0.0
        for (item in cartItems) {
            total += item.product.price * item.quantity
        }
        return total
    }
}

private const val A_VAL = 10.0
private const val B_VAL = 15.0

fun main() {
    val product1 = Product("Product A", A_VAL)
    val product2 = Product("Product B", B_VAL)

    val cartItem1 = CartItem(product1, 2)
    val cartItem2 = CartItem(product2, 3)

    val cartItems = listOf(cartItem1, cartItem2)

    val cartCalculator = CartCalculator()
    val totalCost = cartCalculator.calculateTotal(cartItems)

    println("Total cost of the shopping cart: $totalCost")
}
