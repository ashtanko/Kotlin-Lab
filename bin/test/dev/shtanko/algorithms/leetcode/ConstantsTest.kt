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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.DECIMAL
import dev.shtanko.algorithms.EPSILON
import dev.shtanko.algorithms.E_9
import dev.shtanko.algorithms.HEXADECIMAL
import dev.shtanko.algorithms.MOD
import dev.shtanko.algorithms.OCTAL
import dev.shtanko.algorithms.SHUFFLE_CONST
import dev.shtanko.algorithms.TOLERANCE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConstantsTest {
    @Test
    fun `constants test`() {
        assertEquals(10, DECIMAL)
        assertEquals(8, OCTAL)
        assertEquals(16, HEXADECIMAL)
        assertEquals(65535, SHUFFLE_CONST)
        assertEquals(1_000_000_007, MOD)
        assertEquals(1_000_000_000.0, E_9)
        assertEquals(0.00001, EPSILON)
        assertEquals(0.0000000001, TOLERANCE)
    }
}
