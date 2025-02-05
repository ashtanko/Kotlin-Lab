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

package dev.shtanko.kotlinlang.infix

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class InfixFunctionsTest {

    @Test
    internal fun `test colours`() {
        val color = 0x123456
        val red = color and 0xff0000 shr 16
        assertEquals(0x12, red)
    }

    @Test
    internal fun `test new assertions`() {
        val result = Assertion(5)
        result isEqualTo 5
        result isDifferentFrom 6
    }

    @Test
    internal fun `test new string method`() {
        infix fun String.substringMatches(r: Regex): List<String> {
            return r.findAll(this)
                .map { it.value }
                .toList()
        }

        val matches = "a bc def" substringMatches ".*? ".toRegex()
        assertEquals(listOf("a ", "bc "), matches)
    }
}
