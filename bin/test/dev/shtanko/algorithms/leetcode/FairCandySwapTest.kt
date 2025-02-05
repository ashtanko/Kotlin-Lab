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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class FairCandySwapTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf() to intArrayOf(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1, 1) to intArrayOf(2, 2),
                intArrayOf(1, 2),
            ),
            Arguments.of(
                intArrayOf(1, 2) to intArrayOf(2, 3),
                intArrayOf(1, 2),
            ),
            Arguments.of(
                intArrayOf(2) to intArrayOf(1, 3),
                intArrayOf(2, 3),
            ),
            Arguments.of(
                intArrayOf(1, 2, 5) to intArrayOf(2, 4),
                intArrayOf(5, 4),
            ),
            Arguments.of(
                intArrayOf(35, 17, 4, 24, 10) to intArrayOf(63, 21),
                intArrayOf(24, 21),
            ),
            Arguments.of(
                intArrayOf(1, 2, 5) to intArrayOf(2, 4),
                intArrayOf(5, 4),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `fair candy swap test`(candies: Pair<IntArray, IntArray>, expected: IntArray) {
        val actual = candies.fairCandySwap()
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}
