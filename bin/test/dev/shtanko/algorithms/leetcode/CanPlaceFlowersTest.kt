/*
 * Copyright 2023 Oleksii Shtanko
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

abstract class CanPlaceFlowersTest<out T : CanPlaceFlowers>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1),
                1,
                true,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1),
                2,
                false,
            ),
            Arguments.of(
                intArrayOf(0, 0, 1, 0, 0),
                1,
                true,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                2,
                true,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                3,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                4,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                5,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                6,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                7,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                8,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                9,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                10,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                11,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                12,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                13,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                14,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                15,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                16,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                17,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                18,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 1, 0, 0),
                19,
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `can place flowers test`(flowerbed: IntArray, num: Int, expected: Boolean) {
        val actual = strategy.invoke(flowerbed, num)
        assertThat(actual).isEqualTo(expected)
    }
}

class CanPlaceFlowersGreedyTest : CanPlaceFlowersTest<CanPlaceFlowers>(CanPlaceFlowersGreedy())
