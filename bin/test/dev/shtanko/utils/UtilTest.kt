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

package dev.shtanko.utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class UtilTest {

    @Test
    internal fun `list of int equals test`() {
        val list1 = listOf(1, 3, 2)
        val list2 = listOf(1, 3, 2)
        assertTrue(assertListEquals(list1, list2))
    }

    @Test
    internal fun `list of string equals test`() {
        val list1 = listOf("a", "c", "b")
        val list2 = listOf("a", "c", "b")
        assertTrue(assertListEquals(list1, list2))
    }

    @Test
    internal fun `list of string equals diff order test`() {
        val list1 = listOf("a", "c", "b")
        val list2 = listOf("a", "b", "c")
        assertTrue(assertListEquals(list1, list2))
    }

    @Test
    internal fun `not equals test`() {
        val list1 = listOf("a", "c", "b")
        val list2 = listOf("a", "b", "c", "d")
        assertFalse(assertListEquals(list1, list2))
    }

    @Test
    internal fun `not equals int test`() {
        val list1 = listOf(1, 1, 2)
        val list2 = listOf(1, 3, 2)
        assertFalse(assertListEquals(list1, list2))
    }
}
