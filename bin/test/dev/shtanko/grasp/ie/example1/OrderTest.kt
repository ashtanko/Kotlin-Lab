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

package dev.shtanko.grasp.ie.example1

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class OrderTest {
    @Test
    fun testCalculateTotalCost() {
        val order = Order()

        order.addItem(Item("Product A", 10.0))
        order.addItem(Item("Product B", 15.0))
        order.addItem(Item("Product C", 20.0))

        val totalCost = order.calculateTotalCost()

        assertEquals(45.0, totalCost, 0.01) // Allowing a small delta for floating-point precision
    }
}
