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

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class SummaryRangesTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `summary ranges test`(arr: IntArray, expected: List<String>) {
        val summaryRanges = arr.getSummaryRanges()
        assertEquals(expected, summaryRanges)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(0, 1, 2, 4, 5, 7),
                listOf("0->2", "4->5", "7"),
            ),
            Arguments.of(
                intArrayOf(0, 2, 3, 4, 6, 8, 9),
                listOf("0", "2->4", "6", "8->9"),
            ),
            Arguments.of(
                intArrayOf(),
                listOf<String>(),
            ),
            Arguments.of(
                intArrayOf(0),
                listOf("0"),
            ),
            Arguments.of(
                intArrayOf(0, 1),
                listOf("0->1"),
            ),
            Arguments.of(
                intArrayOf(Int.MIN_VALUE, Int.MAX_VALUE),
                listOf("${Int.MIN_VALUE}", "${Int.MAX_VALUE}"),
            ),
            Arguments.of(
                intArrayOf(),
                listOf<String>(),
            ),
            Arguments.of(
                intArrayOf(-1, 0, 1),
                listOf("-1->1"),
            ),
            Arguments.of(
                intArrayOf(0, 1, 2, 3, 3, 3, 4, 5, 6),
                listOf("0->3", "3", "3->6"),
            ),
            Arguments.of(
                intArrayOf(0, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6),
                listOf("0->3", "3", "3->6", "6", "6"),
            ),
            Arguments.of(
                intArrayOf(0, 1, 2, 3, 3, 3, 4, 5, 6, 8, 9, 10),
                listOf("0->3", "3", "3->6", "8->10"),
            ),
            Arguments.of(
                intArrayOf(0, 1, 2, 3, 3, 3, 4, 5, 6, 8, 9, 10, 12),
                listOf("0->3", "3", "3->6", "8->10", "12"),
            ),
        )
    }
}
