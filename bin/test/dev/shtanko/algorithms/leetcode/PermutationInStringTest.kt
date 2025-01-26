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

abstract class PermutationInStringStrategyTest<out T : StringPermutationStrategy>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `simple test`(str1: String, str2: String, expected: Boolean) {
        val actual = strategy.invoke(str1, str2)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "ab",
                "eidbaooo",
                true,
            ),
            Arguments.of(
                "ab",
                "eidboaoo",
                false,
            ),
            Arguments.of(
                "",
                "",
                true,
            ),
        )
    }
}

class PermutationBruteForceTest :
    PermutationInStringStrategyTest<PermutationBruteForce>(PermutationBruteForce())

class PermutationSortingTest : PermutationInStringStrategyTest<PermutationSorting>(PermutationSorting())

class PermutationHashmapTest : PermutationInStringStrategyTest<PermutationHashmap>(PermutationHashmap())

class PermutationArrayTest : PermutationInStringStrategyTest<PermutationArray>(PermutationArray())

class PermutationSlidingWindowTest :
    PermutationInStringStrategyTest<PermutationSlidingWindow>(PermutationSlidingWindow())

class PermutationOptimizedSlidingWindowTest :
    PermutationInStringStrategyTest<PermutationOptimizedSlidingWindow>(PermutationOptimizedSlidingWindow())
