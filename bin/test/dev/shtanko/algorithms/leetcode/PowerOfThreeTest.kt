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

abstract class PowerOfThreeTest<out T : PowerOfThreeStrategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(1, true),
            Arguments.of(3, true),
            Arguments.of(9, true),
            Arguments.of(27, true),
            Arguments.of(81, true),
            Arguments.of(243, true),
            Arguments.of(729, true),
            Arguments.of(2_187, true),
            Arguments.of(6_561, true),
            Arguments.of(19_683, true),
            Arguments.of(59_049, true),
            Arguments.of(177_147, true),
            Arguments.of(531_441, true),
            Arguments.of(1_594_323, true),
            Arguments.of(4_782_969, true),
            Arguments.of(14_348_907, true),
            Arguments.of(43_046_721, true),
            Arguments.of(129_140_163, true),
            Arguments.of(387_420_489, true),
            Arguments.of(1_162_261_467, true),
            Arguments.of(2, false),
            Arguments.of(4, false),
            Arguments.of(8, false),
            Arguments.of(16, false),
            Arguments.of(32, false),
            Arguments.of(64, false),
            Arguments.of(128, false),
            Arguments.of(256, false),
            Arguments.of(512, false),
            Arguments.of(1024, false),
            Arguments.of(2048, false),
            Arguments.of(4096, false),
            Arguments.of(8192, false),
            Arguments.of(16_384, false),
            Arguments.of(32_768, false),
            Arguments.of(65_536, false),
            Arguments.of(131_072, false),
            Arguments.of(262_144, false),
            Arguments.of(524_288, false),
            Arguments.of(1_048_576, false),
            Arguments.of(2_097_152, false),
            Arguments.of(4_194_304, false),
            Arguments.of(8_388_608, false),
            Arguments.of(16_777_216, false),
            Arguments.of(33_554_432, false),
            Arguments.of(67_108_864, false),
            Arguments.of(134_217_728, false),
            Arguments.of(268_435_456, false),
            Arguments.of(536_870_912, false),
            Arguments.of(1_073_741_824, false),
            Arguments.of(-1, false),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is power of tree test`(num: Int, expected: Boolean) {
        val actual = strategy.isPowerOfThree(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class POTLoopIterationTest : PowerOfThreeTest<POTLoopIteration>(POTLoopIteration())

class POTBaseConversionTest : PowerOfThreeTest<POTBaseConversion>(POTBaseConversion())

class POTMathematicsTest : PowerOfThreeTest<POTMathematics>(POTMathematics())

class POTIntegerLimitationsTest : PowerOfThreeTest<POTIntegerLimitations>(POTIntegerLimitations())
