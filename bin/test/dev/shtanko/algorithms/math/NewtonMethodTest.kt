/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class NewtonMethodTest {

    @Test
    internal fun `calculate sqrt newton method test one`() {
        assertTrue(sqrt(-2.0).isNaN())
        assertTrue(sqrt(-2.0, 0.toDouble()).isNaN())
        assertEquals(2.toDouble(), sqrt(4.toDouble()), 1e-15)
        assertEquals(4.toDouble(), sqrt(16.toDouble()), 1e-12)
    }

    @Test
    internal fun `calculate sqrt newton method test two`() {
        assertTrue(sqrt(-2).isNaN())
        assertEquals(2.toDouble(), sqrt(4), 1e-15)
        assertEquals(4.toDouble(), sqrt(16), 1e-12)
    }
}
