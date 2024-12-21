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
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class AbstractRotateArrayTest<out T : AbstractRotateArray>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `rotate array test`(arr: IntArray, k: Int, expected: IntArray) {
        strategy.invoke(arr, k)
        assertArrayEquals(expected, arr)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7),
                3,
                intArrayOf(5, 6, 7, 1, 2, 3, 4),
            ),
            Arguments.of(
                intArrayOf(-1, -100, 3, 99),
                2,
                intArrayOf(3, 99, -1, -100),
            ),
        )
    }
}

class RotateArrayBruteForceTest :
    AbstractRotateArrayTest<RotateArrayBruteForce>(RotateArrayBruteForce())

class RotateArrayUsingExtraArrayTest :
    AbstractRotateArrayTest<RotateArrayUsingExtraArray>(RotateArrayUsingExtraArray())

class RotateArrayUsingCyclicReplacementsTest :
    AbstractRotateArrayTest<RotateArrayUsingCyclicReplacements>(RotateArrayUsingCyclicReplacements())

class RotateArrayUsingReverseTest :
    AbstractRotateArrayTest<RotateArrayUsingReverse>(RotateArrayUsingReverse())
