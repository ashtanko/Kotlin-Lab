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

abstract class DefuseTheBombTest<out T : DefuseTheBomb>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(5, 7, 1, 4),
                3,
                intArrayOf(12, 10, 16, 13),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                0,
                intArrayOf(0, 0, 0, 0),
            ),
            Arguments.of(
                intArrayOf(2, 4, 9, 3),
                -2,
                intArrayOf(12, 5, 6, 13),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun decryptTest(code: IntArray, k: Int, expected: IntArray) {
        val actual = strategy(code, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class DefuseTheBombSlidingWindowTest : DefuseTheBombTest<DefuseTheBombSlidingWindow>(DefuseTheBombSlidingWindow)