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

internal class TakeTest {

    @Test
    internal fun `take test`() {
        val list = IntArray(100) { it * 10 }
        val expected = listOf(0, 10)
        val actual = list.take(2)
        assertEquals(expected, actual)
    }

    @Test
    internal fun `take last test`() {
        val list = IntArray(100) { it * 10 }
        val expected = listOf(980, 990)
        val actual = list.takeLast(2)
        assertEquals(expected, actual)
    }

    @Test
    internal fun `take last while test`() {
        val list = IntArray(100) { it * 10 }
        val expected = listOf(900, 910, 920, 930, 940, 950, 960, 970, 980, 990)
        val actual = list.takeLastWhile { it >= 900 }
        assertEquals(expected, actual)
    }
}
