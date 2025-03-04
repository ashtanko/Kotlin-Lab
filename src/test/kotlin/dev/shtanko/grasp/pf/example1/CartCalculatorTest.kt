/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.grasp.pf.example1

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class CartCalculatorTest {
    @Test
    fun testCalculateTotal() {
        val product1 = Product("Product A", 10.0)
        val product2 = Product("Product B", 15.0)

        val cartItem1 = CartItem(product1, 2)
        val cartItem2 = CartItem(product2, 3)

        val cartItems = listOf(cartItem1, cartItem2)

        val cartCalculator = CartCalculator()
        val totalCost = cartCalculator.calculateTotal(cartItems)

        assertEquals(65.0, totalCost, 0.01) // Corrected expected value
    }
}
