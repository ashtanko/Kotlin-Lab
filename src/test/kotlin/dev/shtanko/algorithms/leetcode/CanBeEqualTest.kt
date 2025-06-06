/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

abstract class CanBeEqualTest<out T : CanBeEqual>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(2, 4, 1, 3),
                true,
            ),
            Arguments.of(
                intArrayOf(7),
                intArrayOf(7),
                true,
            ),
            Arguments.of(
                intArrayOf(3, 7, 9),
                intArrayOf(3, 7, 11),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `can be equal test`(target: IntArray, arr: IntArray, expected: Boolean) {
        val actual = strategy(target, arr)
        assertThat(actual).isEqualTo(expected)
    }
}

class CanBeEqualSortingTest : CanBeEqualTest<CanBeEqualSorting>(CanBeEqualSorting())
class CanBeEqualCountingTest : CanBeEqualTest<CanBeEqualCounting>(CanBeEqualCounting())
class CanBeEqual1DictionaryTest : CanBeEqualTest<CanBeEqual1Dictionary>(CanBeEqual1Dictionary())
