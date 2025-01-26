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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ContiguousArrayTest<out T : ContiguousArray>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                0,
            ),
            Arguments.of(
                intArrayOf(0, 1),
                2,
            ),
            Arguments.of(
                intArrayOf(0, 1, 0),
                2,
            ),
            Arguments.of(
                intArrayOf(0, 1, 0, 1),
                4,
            ),
            Arguments.of(
                intArrayOf(0, 1, 0, 1, 0),
                4,
            ),
            Arguments.of(
                intArrayOf(0, 1, 0, 1, 0, 1),
                6,
            ),
            Arguments.of(
                intArrayOf(0, 1, 0, 1, 0, 1, 0),
                6,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun findMaxLengthTest(nums: IntArray, expected: Int) {
        val actual = strategy(nums)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ContiguousArrayExtraArrayTest : ContiguousArrayTest<ContiguousArray>(ContiguousArrayExtraArray())
class ContiguousArrayHashMapTest : ContiguousArrayTest<ContiguousArray>(ContiguousArrayHashMap())
