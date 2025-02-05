/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class BitwiseComplementTest<out T : BitwiseComplement>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                1,
            ),
            Arguments.of(
                5,
                2,
            ),
            Arguments.of(
                7,
                0,
            ),
            Arguments.of(
                10,
                5,
            ),
            Arguments.of(
                10_000,
                6383,
            ),
            Arguments.of(
                1_000_000,
                48575,
            ),
            Arguments.of(
                1_000_000_000,
                73741823,
            ),
            Arguments.of(
                1_073_741_823,
                0,
            ),
            Arguments.of(
                1_073_741_824,
                1073741823,
            ),
            Arguments.of(
                2_147_483_647,
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `bitwise complement test`(num: Int, expected: Int) {
        val actual = strategy.invoke(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class BitwiseComplementFlipBitTest :
    BitwiseComplementTest<BitwiseComplementFlipBit>(BitwiseComplementFlipBit())

class BitwiseComplementBitmaskTest :
    BitwiseComplementTest<BitwiseComplementBitmask>(BitwiseComplementBitmask())

class BitwiseComplementBuiltInFuncTest :
    BitwiseComplementTest<BitwiseComplementBuiltInFunc>(BitwiseComplementBuiltInFunc())

class HighestOneBitTest : BitwiseComplementTest<HighestOneBit>(HighestOneBit())

class BitwiseComplementBruteForceTest :
    BitwiseComplementTest<BitwiseComplementBruteForce>(BitwiseComplementBruteForce())
