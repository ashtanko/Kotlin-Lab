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

package dev.shtanko.grasp.polymorphism.example1

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class ShapeTest {
    @Test
    fun testCircleArea() {
        val circle = Circle(5.0)
        val expectedArea = Math.PI * 5.0 * 5.0
        assertEquals(expectedArea, circle.area(), 0.01) // Allowing a small delta for floating-point precision
    }

    @Test
    fun testRectangleArea() {
        val rectangle = Rectangle(3.0, 4.0)
        val expectedArea = 3.0 * 4.0
        assertEquals(expectedArea, rectangle.area(), 0.01) // Allowing a small delta for floating-point precision
    }

    @Test
    fun testTriangleArea() {
        val triangle = Triangle(6.0, 8.0)
        val expectedArea = 0.5 * 6.0 * 8.0
        assertEquals(expectedArea, triangle.area(), 0.01) // Allowing a small delta for floating-point precision
    }
}
