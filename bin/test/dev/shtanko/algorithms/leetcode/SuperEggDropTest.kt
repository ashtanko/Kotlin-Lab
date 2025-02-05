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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SuperEggDropTest<out T : SuperEggDrop>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `super egg drop test`(eggs: Int, floors: Int, expected: Int) {
        val actual = strategy.invoke(eggs, floors)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2,
                6,
                3,
            ),
            Arguments.of(
                3,
                14,
                4,
            ),
            Arguments.of(
                2,
                2,
                2,
            ),
            Arguments.of(
                1,
                2,
                2,
            ),
            Arguments.of(
                2,
                100,
                14,
            ),
            Arguments.of(
                3,
                100,
                9,
            ),
            Arguments.of(
                4,
                100,
                8,
            ),
            Arguments.of(
                5,
                100,
                7,
            ),
        )
    }
}

class SuperEggDropDPBinarySearchTest : SuperEggDropTest<SuperEggDrop>(SuperEggDropDPBinarySearch())

class SuperEggDropDPOptimalityCriterionTest : SuperEggDropTest<SuperEggDrop>(SuperEggDropDPOptimalityCriterion())

class SuperEggDropMathematicalTest : SuperEggDropTest<SuperEggDrop>(SuperEggDropMathematical())
