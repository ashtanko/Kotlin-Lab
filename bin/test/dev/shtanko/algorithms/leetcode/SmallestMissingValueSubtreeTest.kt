/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class SmallestMissingValueSubtreeTest<out T : SmallestMissingValueSubtree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(-1, 0, 0, 2),
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 1, 1, 1),
            ),
            Arguments.of(
                intArrayOf(-1, 0, 1, 0, 3, 3),
                intArrayOf(5, 4, 6, 2, 1, 3),
                intArrayOf(7, 1, 1, 4, 2, 1),
            ),
            Arguments.of(
                intArrayOf(-1, 2, 3, 0, 2, 4, 1),
                intArrayOf(2, 3, 4, 5, 6, 7, 8),
                intArrayOf(1, 1, 1, 1, 1, 1, 1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `smallest missing value subtree test`(parents: IntArray, nums: IntArray, expected: IntArray) {
        val actual = strategy.invoke(parents, nums)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}

class SmallestMissingValueSubtreeStrictTest :
    SmallestMissingValueSubtreeTest<SmallestMissingValueSubtree>(SmallestMissingValueSubtreeStrict())
