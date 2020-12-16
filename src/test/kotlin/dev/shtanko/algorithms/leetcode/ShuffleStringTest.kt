/*
 * Copyright 2020 Alexey Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class ShuffleStringTest {

    companion object {
        @JvmStatic
        fun dataProvider(): List<Pair<Pair<IntArray, String>, String>> {
            return listOf(
                Pair(Pair(intArrayOf(4, 5, 6, 7, 0, 2, 1, 3), "codeleet"), "leetcode"),
                Pair(Pair(intArrayOf(0, 1, 2), "abc"), "abc"),
                Pair(Pair(intArrayOf(3, 1, 4, 2, 0), "aiohn"), "nihao"),
                Pair(Pair(intArrayOf(4, 0, 2, 6, 7, 3, 1, 5), "aaiougrt"), "arigatou"),
                Pair(Pair(intArrayOf(1, 0, 2), "art"), "rat"),
                Pair(Pair(intArrayOf(3, 4, 5, 6, 0, 1, 2), "ankosht"), "shtanko")
            )
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    internal fun `restore string test`(testCase: Pair<Pair<IntArray, String>, String>) {
        val (data, expected) = testCase
        val (indices, s) = data
        val actual = (s to indices).restoreString()
        assertEquals(expected, actual)
    }
}
