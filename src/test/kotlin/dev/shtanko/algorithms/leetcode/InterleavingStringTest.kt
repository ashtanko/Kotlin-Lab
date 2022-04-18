/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal abstract class InterleavingStringTest<out T : InterleavingStringStrategy>(private val strategy: T) {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("aabcc", "dbbca", "aadbbcbcac", true),
            Arguments.of("aabcc", "dbbca", "aadbbbaccc", false),
            Arguments.of("", "", "", true)
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `interleaving string test`(s1: String, s2: String, s3: String, expected: Boolean) {
        val actual = strategy.perform(s1, s2, s3)
        assertEquals(expected, actual)
    }
}

internal class InterleavingStringBruteForceTest :
    InterleavingStringTest<InterleavingStringBruteForce>(InterleavingStringBruteForce())

internal class InterleavingStringRecursionWithMemoTest :
    InterleavingStringTest<InterleavingStringRecursionWithMemo>(InterleavingStringRecursionWithMemo())

internal class InterleavingString2DTest : InterleavingStringTest<InterleavingString2D>(InterleavingString2D())

internal class InterleavingString1DTest : InterleavingStringTest<InterleavingString1D>(InterleavingString1D())
