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

package dev.shtanko.patterns.solid.ocp.example1

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class OpenClosedPrincipleTest {
    @Test
    fun testCircleArea() {
        val circle = Circle(5.0)
        assertEquals(Math.PI * 25.0, circle.calculateArea())
    }

    @Test
    fun testRectangleArea() {
        val rectangle = Rectangle(4.0, 6.0)
        assertEquals(24.0, rectangle.calculateArea())
    }

    @Test
    fun testAreaCalculator() {
        val shapes = listOf(Circle(3.0), Rectangle(2.0, 4.0))
        val calculator = AreaCalculator(shapes)
        assertEquals(Math.PI * 9.0 + 8.0, calculator.calculateTotalArea())
    }
}
