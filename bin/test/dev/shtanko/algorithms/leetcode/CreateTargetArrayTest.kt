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

class CreateTargetArrayTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf() to intArrayOf(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(0, 1, 2, 3, 4) to intArrayOf(0, 1, 2, 2, 1),
                intArrayOf(0, 4, 1, 3, 2),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 0) to intArrayOf(0, 1, 2, 3, 0),
                intArrayOf(0, 1, 2, 3, 4),
            ),
            Arguments.of(
                intArrayOf(1) to intArrayOf(0),
                intArrayOf(1),
            ),
            Arguments.of(
                intArrayOf(4, 2, 4, 3, 2) to intArrayOf(0, 0, 1, 3, 1),
                intArrayOf(2, 2, 4, 4, 3),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5) to intArrayOf(0, 0, 1, 3, 3),
                intArrayOf(2, 3, 1, 5, 4),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `create target array solution test`(pair: Pair<IntArray, IntArray>, expected: IntArray) {
        val actual = pair.createTargetArray()
        assertThat(actual).containsExactly(*expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `create target array solution 2 test`(pair: Pair<IntArray, IntArray>, expected: IntArray) {
        val actual = pair.createTargetArray2()
        assertThat(actual).containsExactly(*expected)
    }
}
