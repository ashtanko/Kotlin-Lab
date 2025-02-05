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

package dev.shtanko.algorithms.bitwise

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FastInverseSquareRootTest {
    @Test
    fun test_fastInverseSquareRootBase() {
        val number = 4f
        val expected = 0.5f
        val actual = fastInverseSquareRoot(number)
        assertEquals(expected, actual, 0.01f)
    }

    @Test
    fun test_fastInverseSquareRootWithZero() {
        val number = 0f
        val expected = 1.9817754E19f
        val actual = fastInverseSquareRoot(number)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fastInverseSquareRootWithNegativeNumber() {
        val number = -4f
        val expected = Float.POSITIVE_INFINITY
        val actual = fastInverseSquareRoot(number)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fastInverseSquareRootWithInfinity() {
        val number = Float.POSITIVE_INFINITY
        val expected = Float.NEGATIVE_INFINITY
        val actual = fastInverseSquareRoot(number)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fastInverseSquareRootWithNaN() {
        val number = Float.NaN
        val expected = Float.NaN
        val actual = fastInverseSquareRoot(number)
        assertEquals(expected, actual)
    }
}
