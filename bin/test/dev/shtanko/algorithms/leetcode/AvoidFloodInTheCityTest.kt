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

abstract class AvoidFloodInTheCityTest<out T : AvoidFlood>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(-1, -1, -1, -1),
            ),
            Arguments.of(
                intArrayOf(1, 2, 0, 0, 2, 1),
                intArrayOf(-1, -1, 2, 1, -1, -1),
            ),
            Arguments.of(
                intArrayOf(1, 2, 0, 1, 2),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(69, 0, 0, 0, 69),
                intArrayOf(-1, 69, 1, 1, -1),
            ),
            Arguments.of(
                intArrayOf(10, 20, 20),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(0),
                intArrayOf(1),
            ),
            Arguments.of(
                intArrayOf(1),
                intArrayOf(-1),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                intArrayOf(-1, -1),
            ),
            Arguments.of(
                intArrayOf(1, 0, 2, 0, 2, 1),
                intArrayOf(-1, 1, -1, 2, -1, -1),
            ),
            Arguments.of(
                intArrayOf(1, 0, 2, 0, 1, 2),
                intArrayOf(-1, 1, -1, 2, -1, -1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `avoid flood test`(rains: IntArray, expected: IntArray) {
        val actual = strategy.invoke(rains)
        assertThat(actual, equalTo(expected))
    }
}

class AvoidFloodTreeTest : AvoidFloodInTheCityTest<AvoidFloodTree>(AvoidFloodTree())

class AvoidFloodSimpleTest : AvoidFloodInTheCityTest<AvoidFloodSimple>(AvoidFloodSimple())
