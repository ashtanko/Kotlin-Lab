/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class AverageSalaryTest<out T : AverageSalary>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4000, 3000, 1000, 2000),
                2500.00000,
            ),
            Arguments.of(
                intArrayOf(1000, 2000, 3000),
                2000.00000,
            ),
            Arguments.of(
                intArrayOf(6000, 5000, 4000, 3000, 2000, 1000),
                3500.00000,
            ),
            Arguments.of(
                intArrayOf(8000, 9000, 2000, 3000, 6000, 1000),
                4750.00000,
            ),
            Arguments.of(
                intArrayOf(500, 2000, 3000),
                2000.0,
            ),
            Arguments.of(
                intArrayOf(10, 25, 15, 40, 55, 23),
                25.75,
            ),
            Arguments.of(
                intArrayOf(
                    344,
                    56,
                    7,
                    78,
                    678,
                    987,
                    89,
                    76,
                    54,
                    6,
                    435,
                    4,
                    35,
                    3,
                    42,
                    5645,
                    5,
                    7,
                    657,
                    56,
                    7,
                    547,
                ),
                208.5,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `average salary test`(arr: IntArray, expected: Double) {
        val actual = strategy.average(arr)
        assertThat(actual, equalTo(expected))
    }
}

class AverageSalaryBruteForceTest : AverageSalaryTest<AverageSalary>(AverageSalaryBruteForce())
class AverageSalarySimpleTest : AverageSalaryTest<AverageSalary>(AverageSalarySimple())
