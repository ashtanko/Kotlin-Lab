/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class KInversePairsArrayTest<out T : KInversePairsArray>(private val strategy: T) {
    private class InputArgumentsProvide : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                0,
                1,
            ),
            Arguments.of(
                3,
                1,
                2,
            ),
            Arguments.of(
                0,
                0,
                0,
            ),
            Arguments.of(
                1,
                1,
                0,
            ),
            Arguments.of(
                2,
                0,
                1,
            ),
            Arguments.of(
                4,
                0,
                1,
            ),
            Arguments.of(
                5,
                2,
                9,
            ),
            Arguments.of(
                15,
                7,
                66013,
            ),
            Arguments.of(
                20,
                12,
                67163518,
            ),
        )
    }

    @ArgumentsSource(InputArgumentsProvide::class)
    @ParameterizedTest
    fun `k inverse pairs test`(num: Int, k: Int, expected: Int) {
        val actual = strategy.invoke(num, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class KInversePairsArrayRecursionTest : KInversePairsArrayTest<KInversePairsArray>(KInversePairsArrayRecursion())
class KInversePairsArrayDPTest : KInversePairsArrayTest<KInversePairsArray>(KInversePairsArrayDP())
class CumulativeSumTest : KInversePairsArrayTest<KInversePairsArray>(CumulativeSum())
class KInversePairsArrayOptimizedDPTest : KInversePairsArrayTest<KInversePairsArray>(KInversePairsArrayOptimizedDP())
class KInversePairsArrayMemoizationTest : KInversePairsArrayTest<KInversePairsArray>(KInversePairsArrayMemoization())
class KInversePairsArrayDP1DTest : KInversePairsArrayTest<KInversePairsArray>(KInversePairsArrayDP1D())
