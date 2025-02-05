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

package dev.shtanko.kotlinlang.op

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DropTest {

    @Test
    internal fun `drop test`() {
        val list = 0.until(10).toList()
        val actual = list.drop(5)
        val expected = listOf(5, 6, 7, 8, 9)
        assertEquals(expected, actual)
    }

    @Test
    internal fun `drop last test`() {
        val list = 0.until(10).toList()
        val actual = list.dropLast(5)
        val expected = listOf(0, 1, 2, 3, 4)
        assertEquals(expected, actual)
    }
}
