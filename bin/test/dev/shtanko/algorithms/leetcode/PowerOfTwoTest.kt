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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class PowerOfTwoTest<out T : PowerOfTwo>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputPositiveCasesProvider::class)
    fun `power of two positive test`(num: Int) {
        assertThat(strategy(num)).isTrue()
    }

    @ParameterizedTest
    @ArgumentsSource(InputNegativeCasesProvider::class)
    fun `power of two negative test`(num: Int) {
        assertThat(strategy(num)).isFalse()
    }

    private class InputPositiveCasesProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = listOf(
            1,
            2,
            4,
            8,
            16,
            32,
            64,
            128,
            256,
            512,
            1024,
            2048,
            4096,
            8192,
            16_384,
            32_768,
            65_536,
            131_072,
            262_144,
            524_288,
            1_048_576,
            2_097_152,
            4_194_304,
            8_388_608,
            16_777_216,
            33_554_432,
            67_108_864,
            134_217_728,
            268_435_456,
            536_870_912,
            1_073_741_824,
        ).map { Arguments.of(it) }.stream()
    }

    private class InputNegativeCasesProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
            listOf(
                3,
                9,
                27,
                81,
                243,
                729,
                2_187,
                6_561,
                19_683,
                59_049,
                177_147,
                531_441,
                1_594_323,
                4_782_969,
                14_348_907,
                43_046_721,
                129_140_163,
                387_420_489,
                1_162_261_467,
            ).map { Arguments.of(it) }.stream()
    }
}

class PowerOfTwoIterativeTest : PowerOfTwoTest<PowerOfTwo>(PowerOfTwoIterative())
class PowerOfTwoRecursiveTest : PowerOfTwoTest<PowerOfTwo>(PowerOfTwoRecursive())
class PowerOfTwoBitwiseTest : PowerOfTwoTest<PowerOfTwo>(PowerOfTwoBitwise())
class PowerOfTwoTailrecTest : PowerOfTwoTest<PowerOfTwo>(PowerOfTwoTailrec())
class PowerOfTwoMemoTest : PowerOfTwoTest<PowerOfTwo>(PowerOfTwoMemo())
class PowerOfTwoMathOneLineTest : PowerOfTwoTest<PowerOfTwo>(PowerOfTwoMathOneLine())
class PowerOfTwoMathTest : PowerOfTwoTest<PowerOfTwo>(PowerOfTwoMath())
