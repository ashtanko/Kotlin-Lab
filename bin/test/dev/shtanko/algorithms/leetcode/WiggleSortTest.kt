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

abstract class WiggleSortTest<out T : WiggleSort>(private val strategy: T) {

    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(3, 5, 2, 1, 6, 4),
                intArrayOf(3, 5, 1, 6, 2, 4),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1, 3, 2, 2, 3, 1),
                intArrayOf(2, 3, 1, 3, 1, 2),
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 1, 2, 2, 1),
                intArrayOf(1, 2, 1, 2, 1, 2, 1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `wiggle sort test`(nums: IntArray, expected: IntArray) {
        strategy.invoke(nums)
        assertThat(nums).containsExactlyInAnyOrder(*expected)
    }
}

class WiggleSortBruteForceTest : WiggleSortTest<WiggleSortBruteForce>(WiggleSortBruteForce())
class WiggleSortOnePassSwapTest : WiggleSortTest<WiggleSortOnePassSwap>(WiggleSortOnePassSwap())
