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

package dev.shtanko.grasp.controller.example1

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class CheckoutControllerTest {
    @Test
    fun testProcessCheckout() {
        val book1 = Book("Book A", 15.0)
        val book2 = Book("Book B", 20.0)
        val book3 = Book("Book C", 25.0)

        val shoppingCart = ShoppingCart()
        shoppingCart.addItem(book1)
        shoppingCart.addItem(book2)
        shoppingCart.addItem(book3)

        val checkoutController = CheckoutController(shoppingCart)
        val totalAmount = checkoutController.processCheckout()

        assertEquals(60.0, totalAmount, 0.01) // Allowing a small delta for floating-point precision
    }
}
