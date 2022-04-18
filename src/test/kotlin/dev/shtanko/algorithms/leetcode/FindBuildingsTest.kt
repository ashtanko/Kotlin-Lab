/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class FindBuildingsTest<out T : FindBuildings>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 2, 3, 1),
                intArrayOf(0, 2, 3),
            ),
            Arguments.of(
                intArrayOf(4, 3, 2, 1),
                intArrayOf(0, 1, 2, 3),
            ),
            Arguments.of(
                intArrayOf(1, 3, 2, 4),
                intArrayOf(3),
            ),
            Arguments.of(
                intArrayOf(2, 2, 2, 2),
                intArrayOf(3),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find buildings test`(heights: IntArray, expected: IntArray) {
        val actual = strategy.perform(heights)
        assertThat(actual, equalTo(expected))
    }
}

class FindBuildingsBruteForceTest :
    FindBuildingsTest<FindBuildingStrategy.BruteForce>(FindBuildingStrategy.BruteForce)
