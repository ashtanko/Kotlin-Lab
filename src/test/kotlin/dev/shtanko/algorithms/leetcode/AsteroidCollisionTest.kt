/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class AsteroidCollisionTest<out T : AsteroidCollision>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(5, 10, -5),
                intArrayOf(5, 10),
            ),
            Arguments.of(
                intArrayOf(8, -8),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(10, 2, -5),
                intArrayOf(10),
            ),
            Arguments.of(
                intArrayOf(-2, -1, 1, 2),
                intArrayOf(-2, -1, 1, 2),
            ),
            Arguments.of(
                intArrayOf(-2, -2, 1, -2),
                intArrayOf(-2, -2, -2),
            ),
            Arguments.of(
                intArrayOf(-2, -2, -2, -2),
                intArrayOf(-2, -2, -2, -2),
            ),
            Arguments.of(
                intArrayOf(-2, -2, -2, -2, -2),
                intArrayOf(-2, -2, -2, -2, -2),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `asteroid collision test`(asteroids: IntArray, expected: IntArray) {
        val actual = strategy.invoke(asteroids)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class AsteroidCollisionStackTest : AsteroidCollisionTest<AsteroidCollision>(AsteroidCollisionStack())
