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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

class CanMakeArithmeticProgressionTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(3, 5, 1),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 4),
                false,
            ),
            Arguments.of(
                intArrayOf(0, 1, 4, 9, 16, 25, 36),
                false,
            ),
            Arguments.of(
                intArrayOf(),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6),
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `can make arithmetic progression test`(arr: IntArray, expected: Boolean) {
        val actual = arr.canMakeArithmeticProgression()
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `can make arithmetic progression using set test`(arr: IntArray, expected: Boolean) {
        val actual = arr.canMakeArithmeticProgressionSet()
        assertThat(actual).isEqualTo(expected)
    }
}
