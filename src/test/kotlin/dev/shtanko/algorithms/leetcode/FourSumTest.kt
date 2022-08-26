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

import java.util.stream.Stream
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal abstract class FourSumTest<out T : FourSum>(private val strategy: T) {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
                emptyList<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(1, 0, -1, 0, -2, 2),
                0,
                listOf(listOf(-2, -1, 1, 2), listOf(-2, 0, 0, 2), listOf(-1, 0, 0, 1)),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `4 sum test`(nums: IntArray, target: Int, expected: List<List<Int>>) {
        val actual = strategy.perform(nums, target).flatten().sorted()
        assertThat(actual, equalTo(expected.flatten().sorted()))
    }
}

internal class FourSumTwoPointersTest : FourSumTest<FourSumTwoPointers>(FourSumTwoPointers())
internal class FourSumHashSetTest : FourSumTest<FourSumHashSet>(FourSumHashSet())
