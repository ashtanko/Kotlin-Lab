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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class CrackingSafeStrategyTest<out T : CrackingSafeStrategy>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(1, 1, "0"),
            Arguments.of(1, 2, "10"),
            Arguments.of(2, 2, "01100"),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `cracking the safe test`(n: Int, k: Int, expected: String) {
        val actual = strategy.invoke(n, k)
        val resultList = expected.toList().map { it.toString() }
        val actualList = actual.toList().map { it.toString() }
        assertThat(actualList).containsExactlyInAnyOrder(*resultList.toTypedArray())
    }
}

class CrackingSafeHierholzersAlgorithmTest :
    CrackingSafeStrategyTest<CrackingSafeStrategy>(CrackingSafeHierholzersAlgorithm())

class CrackingSafeInverseBurrowsWheelerTransformTest :
    CrackingSafeStrategyTest<CrackingSafeStrategy>(CrackingSafeInverseBurrowsWheelerTransform())
