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

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal abstract class AllPathsSourceTargetTest<out T : AllPathsSourceTarget>(private val strategy: T) {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(intArrayOf(1), intArrayOf()),
                listOf(listOf(0, 1))
            ),
            Arguments.of(
                arrayOf(intArrayOf(1, 2, 3), intArrayOf(2), intArrayOf(3), intArrayOf()),
                listOf(listOf(0, 1, 2, 3), listOf(0, 2, 3), listOf(0, 3))
            ),
            Arguments.of(
                arrayOf(intArrayOf(1, 3), intArrayOf(2), intArrayOf(3), intArrayOf()),
                listOf(listOf(0, 1, 2, 3), listOf(0, 3))
            ),
            Arguments.of(
                arrayOf(intArrayOf(1, 2), intArrayOf(3), intArrayOf(3), intArrayOf()),
                listOf(listOf(0, 1, 3), listOf(0, 2, 3))
            ),
            Arguments.of(
                arrayOf(intArrayOf(4, 3, 1), intArrayOf(3, 2, 4), intArrayOf(3), intArrayOf(4), intArrayOf()),
                listOf(listOf(0, 4), listOf(0, 3, 4), listOf(0, 1, 3, 4), listOf(0, 1, 2, 3, 4), listOf(0, 1, 4))
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `all paths source target test`(graph: Array<IntArray>, expected: List<List<Int>>) {
        val actual = strategy.perform(graph)
        assertThat(actual, equalTo(expected))
    }
}

internal class AllPathsSourceBacktrackingTest :
    AllPathsSourceTargetTest<AllPathsSourceBacktracking>(AllPathsSourceBacktracking())

internal class AllPathsSourceDPTest :
    AllPathsSourceTargetTest<AllPathsSourceDP>(AllPathsSourceDP())
