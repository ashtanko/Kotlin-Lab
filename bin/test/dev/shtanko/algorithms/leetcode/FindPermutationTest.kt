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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FindPermutationTest<out T : FindPermutation>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "I",
                intArrayOf(1, 2),
            ),
            Arguments.of(
                "DI",
                intArrayOf(2, 1, 3),
            ),
            Arguments.of(
                "DDIIDI",
                intArrayOf(3, 2, 1, 4, 6, 5, 7),
            ),
            Arguments.of(
                "DDI",
                intArrayOf(3, 2, 1, 4),
            ),
            Arguments.of(
                "IDID",
                intArrayOf(1, 3, 2, 5, 4),
            ),
            Arguments.of(
                "IDIDD",
                intArrayOf(1, 3, 2, 6, 5, 4),
            ),
            Arguments.of(
                "IDIDDD",
                intArrayOf(1, 3, 2, 7, 6, 5, 4),
            ),
            Arguments.of(
                "IDIDID",
                intArrayOf(1, 3, 2, 5, 4, 7, 6),
            ),
            Arguments.of(
                "IDIDIDID",
                intArrayOf(1, 3, 2, 5, 4, 7, 6, 9, 8),
            ),
            Arguments.of(
                "IDIDIDIDID",
                intArrayOf(1, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10),
            ),
            Arguments.of(
                "IDIDIDIDIDID",
                intArrayOf(1, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, 13, 12),
            ),
            Arguments.of(
                "IDIDIDIDIDIDID",
                intArrayOf(1, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, 13, 12, 15, 14),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find permutation test`(str: String, expected: IntArray) {
        val actual = strategy.invoke(str)
        assertThat(actual, equalTo(expected))
    }
}

class FindPermutationStackTest : FindPermutationTest<FindPermutationStack>(FindPermutationStack())
class FindPermutationReversingTest : FindPermutationTest<FindPermutationReversing>(FindPermutationReversing())
class FindPermutationTwoPointersTest :
    FindPermutationTest<FindPermutationTwoPointers>(FindPermutationTwoPointers())
