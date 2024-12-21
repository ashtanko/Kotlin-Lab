/*
 * Copyright 2021 Oleksii Shtanko
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

class RangeSumQueryTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        private val arr = intArrayOf(-2, 0, 3, -5, 2, -1)
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                RangeSumQueryBruteForce(arr),
                0,
                2,
                1,
            ),
            Arguments.of(
                RangeSumQueryBruteForce(arr),
                2,
                5,
                -1,
            ),
            Arguments.of(
                RangeSumQueryBruteForce(arr),
                0,
                5,
                -3,
            ),
            Arguments.of(
                RangeSumQueryCaching(arr),
                0,
                2,
                1,
            ),
            Arguments.of(
                RangeSumQueryCaching(arr),
                2,
                5,
                -1,
            ),
            Arguments.of(
                RangeSumQueryCaching(arr),
                0,
                5,
                -3,
            ),
            Arguments.of(
                RangeSumQueryCachingOptimized(arr),
                0,
                2,
                1,
            ),
            Arguments.of(
                RangeSumQueryCachingOptimized(arr),
                2,
                5,
                -1,
            ),
            Arguments.of(
                RangeSumQueryCachingOptimized(arr),
                0,
                5,
                -3,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sum range test`(obj: RangeSumQuery, i: Int, j: Int, expected: Int) {
        val actual = obj.invoke(i, j)
        assertThat(actual).isEqualTo(expected)
    }
}
