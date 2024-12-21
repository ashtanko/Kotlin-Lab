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

abstract class AbstractMaximumProductStrategyTest<out T : AbstractMaximumProductStrategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3),
                6,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                24,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                60,
            ),
            Arguments.of(
                intArrayOf(4, 8, 15, 16, 23, 42),
                15456,
            ),
            Arguments.of(
                intArrayOf(5, 4, 3, 1),
                60,
            ),
            Arguments.of(
                intArrayOf(
                    5, 89, 1, 234, 78, 4, 9, 66, 123, 6, 9, 0,
                ),
                2561598,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `maximum product test`(products: IntArray, expected: Int) {
        val actual = strategy.invoke(products)
        assertEquals(expected, actual)
    }
}

class MaximumProductBrutForceTest :
    AbstractMaximumProductStrategyTest<MaximumProductBrutForce>(MaximumProductBrutForce())

class MaximumProductSortingTest :
    AbstractMaximumProductStrategyTest<MaximumProductSorting>(MaximumProductSorting())

class MaximumProductSingleScanTest :
    AbstractMaximumProductStrategyTest<MaximumProductSingleScan>(MaximumProductSingleScan())
