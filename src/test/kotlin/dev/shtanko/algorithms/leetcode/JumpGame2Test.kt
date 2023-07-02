/*
 * Copyright 2020 Oleksii Shtanko
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

class JumpGame2Test {

    companion object {

        @JvmStatic
        fun dataProvider(): List<Pair<IntArray, Int>> = listOf(
            intArrayOf(2, 3, 1, 1, 4) to 2,
            intArrayOf(4, 8, 15, 16, 23, 42) to 2,
            intArrayOf() to 0,
            intArrayOf(1) to 0,
            intArrayOf(1, 1) to 1,
            intArrayOf(1, 2) to 1,
            intArrayOf(1, 1, 1) to 2,
        )
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `jump test`(testCase: Pair<IntArray, Int>) {
        val (arr, expected) = testCase
        val actual = arr.jumpGame2()
        assertEquals(expected, actual)
    }
}
