/*
 * Copyright 2024 Oleksii Shtanko
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

abstract class MinDaysToMakeBouquetsTest<out T : MinDaysToMakeBouquets>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(0),
                0,
                0,
                1,
            ),
            Arguments.of(
                intArrayOf(1, 10, 3, 10, 2),
                3,
                1,
                3,
            ),
            Arguments.of(
                intArrayOf(1, 10, 3, 10, 2),
                3,
                2,
                -1,
            ),
            Arguments.of(
                intArrayOf(7, 7, 7, 7, 12, 7, 7),
                2,
                3,
                12,
            ),
            Arguments.of(
                intArrayOf(1000000000, 1000000000),
                1,
                1,
                1000000000,
            ),
            Arguments.of(
                intArrayOf(1, 10, 2, 9, 3, 8, 4, 7, 5, 6),
                4,
                2,
                9,
            ),
            Arguments.of(
                intArrayOf(1, 10, 2, 9, 3, 8, 4, 7, 5, 6),
                4,
                2,
                9,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun minDaysTest(bloomDay: IntArray, m: Int, k: Int, expected: Int) {
        val actual = strategy(bloomDay, m, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class MinDaysToMakeBouquetsBinarySearchTest :
    MinDaysToMakeBouquetsTest<MinDaysToMakeBouquets>(MinDaysToMakeBouquetsBinarySearch())
