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
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FourSumTest<out T : FourSum>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(1, 0, -1, 0, -2, 2),
                0,
                listOf(
                    listOf(-2, -1, 1, 2),
                    listOf(-2, 0, 0, 2),
                    listOf(-1, 0, 0, 1),
                ),
            ),
            Arguments.of(
                intArrayOf(2, 2, 2, 2, 2),
                8,
                listOf(
                    listOf(2, 2, 2, 2),
                ),
            ),
            Arguments.of(
                intArrayOf(-2, -1, -1, 1, 1, 2, 2),
                0,
                listOf(
                    listOf(-2, -1, 1, 2),
                    listOf(-1, -1, 1, 1),
                ),
            ),
            Arguments.of(
                intArrayOf(-3, -2, -1, 0, 0, 1, 2, 3),
                0,
                listOf(
                    listOf(-3, -2, 2, 3),
                    listOf(-3, -1, 1, 3),
                    listOf(-3, 0, 0, 3),
                    listOf(-3, 0, 1, 2),
                    listOf(-2, -1, 0, 3),
                    listOf(-2, -1, 1, 2),
                    listOf(-2, 0, 0, 2),
                    listOf(-1, 0, 0, 1),
                ),
            ),
            Arguments.of(
                intArrayOf(-1, 0, 1, 2, -1, -4),
                -1,
                listOf(
                    listOf(-4, 0, 1, 2),
                    listOf(-1, -1, 0, 1),
                ),
            ),
            Arguments.of(
                intArrayOf(0, 0, 0, 0),
                0,
                listOf(
                    listOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                4,
                listOf(
                    listOf(1, 1, 1, 1),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                5,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                3,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                2,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                1,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                0,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                -1,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                -2,
                emptyList<List<Int>>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `4 sum test`(nums: IntArray, target: Int, expected: List<List<Int>>) {
        val actual = strategy.invoke(nums, target).flatten().sorted()
        assertThat(actual, equalTo(expected.flatten().sorted()))
    }
}

class FourSumTwoPointersTest : FourSumTest<FourSumTwoPointers>(FourSumTwoPointers())
class FourSumHashSetTest : FourSumTest<FourSumHashSet>(FourSumHashSet())
