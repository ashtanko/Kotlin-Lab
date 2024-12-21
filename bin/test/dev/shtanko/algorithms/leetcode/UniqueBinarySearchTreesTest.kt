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

abstract class UniqueBinarySearchTreesTest<out T : UniqueBinarySearchTrees>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                5,
            ),
            Arguments.of(
                1,
                1,
            ),
            Arguments.of(
                7,
                429,
            ),
            Arguments.of(
                12,
                208012,
            ),
            Arguments.of(
                19,
                1767263190,
            ),
            Arguments.of(
                0,
                1,
            ),
            Arguments.of(
                2,
                2,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `num trees test`(num: Int, expected: Int) {
        val actual = strategy.invoke(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class UniqueBSTDPTest : UniqueBinarySearchTreesTest<UniqueBSTDP>(UniqueBSTDP())
class UniqueBSTDeductionTest : UniqueBinarySearchTreesTest<UniqueBSTDeduction>(UniqueBSTDeduction())
