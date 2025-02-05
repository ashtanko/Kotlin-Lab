/*
 * Copyright 2023 Oleksii Shtanko
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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class UniquePathsTest<out T : UniquePaths>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                0,
                0,
            ),
            Arguments.of(
                1,
                1,
                1,
            ),
            Arguments.of(
                3,
                3,
                6,
            ),
            Arguments.of(
                3,
                7,
                28,
            ),
            Arguments.of(
                3,
                2,
                3,
            ),
            Arguments.of(
                10,
                10,
                48620,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `unique paths test`(m: Int, num: Int, expected: Int) {
        val actual = strategy(m, num)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class UniquePathsBruteForceTest : UniquePathsTest<UniquePaths>(UniquePathsBruteForce())
class UniquePathsDpMemoTest : UniquePathsTest<UniquePaths>(UniquePathsDpMemo())
class UniquePathsDpTabulationTest : UniquePathsTest<UniquePaths>(UniquePathsDpTabulation())
class UniquePathsDpOptTest : UniquePathsTest<UniquePaths>(UniquePathsDpOpt())
class UniquePathsMathTest : UniquePathsTest<UniquePaths>(UniquePathsMath())
