/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class NumberOfCommonFactorsTest<out T : NumberOfCommonFactors>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                12,
                6,
                4,
            ),
            Arguments.of(
                25,
                30,
                2,
            ),
            Arguments.of(
                30,
                20,
                4,
            ),
            Arguments.of(
                1,
                1,
                1,
            ),
            Arguments.of(
                24,
                25,
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `common factors test`(a: Int, b: Int, expected: Int) {
        val actual = strategy.commonFactors(a, b)
        assertThat(actual).isEqualTo(expected)
    }
}

class NumberOfCommonFactorsNaiveTest : NumberOfCommonFactorsTest<NumberOfCommonFactors>(NumberOfCommonFactorsNaive())
class NumberOfCommonFactorsBruteForceTest :
    NumberOfCommonFactorsTest<NumberOfCommonFactors>(NumberOfCommonFactorsBruteForce())
