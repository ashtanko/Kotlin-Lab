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

package dev.shtanko.kotlinlang.lambda

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FoldTest {

    @Test
    internal fun `simple test`() {
        val items = listOf(1, 2, 3, 4, 5)
        val joinedToString: String = items.fold("Elements:", { acc, i -> "$acc $i" })
        assertEquals("Elements: 1 2 3 4 5", joinedToString)
    }

    @Test
    internal fun `simple test 2`() {
        val items = listOf(1, 2, 4, 1)
        val product = items.fold(0, Int::plus)
        assertEquals(8, product)
    }
}
