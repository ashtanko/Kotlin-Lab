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

abstract class KidsWithCandiesTest<out T : KidsWithCandiesStrategy>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(2, 3, 5, 1, 3),
                3,
                booleanArrayOf(true, true, true, false, true),
            ),
            Arguments.of(
                intArrayOf(4, 2, 1, 1, 2),
                1,
                booleanArrayOf(true, false, false, false, false),
            ),
            Arguments.of(
                intArrayOf(12, 1, 12),
                10,
                booleanArrayOf(true, false, true),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `kids with candies test`(candies: IntArray, extraCandies: Int, expected: BooleanArray) {
        val actual = strategy.invoke(candies, extraCandies)
        assertArrayEquals(expected, actual)
    }
}

class KidsWithCandiesStraightForwardTest :
    KidsWithCandiesTest<KidsWithCandiesStraightForward>(KidsWithCandiesStraightForward())

class KidsWithCandiesStreamTest : KidsWithCandiesTest<KidsWithCandiesStream>(KidsWithCandiesStream())
