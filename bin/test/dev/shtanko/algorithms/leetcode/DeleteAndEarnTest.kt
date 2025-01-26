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

abstract class DeleteAndEarnTest<out T : DeleteAndEarn>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(3, 4, 2),
                6,
            ),
            Arguments.of(
                intArrayOf(2, 2, 3, 3, 3, 4),
                9,
            ),
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2),
                2,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                4,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                6,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                9,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6),
                12,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7),
                16,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `delete and earn test`(nums: IntArray, expected: Int) {
        val actual = strategy.invoke(nums)
        assertThat(actual).isEqualTo(expected)
    }
}

class DeleteAndEarnTopDownTest : DeleteAndEarnTest<DeleteAndEarn>(DeleteAndEarnTopDown())
class DeleteAndEarnBottomUpTest : DeleteAndEarnTest<DeleteAndEarn>(DeleteAndEarnBottomUp())
class DeleteAndEarnBottomUpOptTest : DeleteAndEarnTest<DeleteAndEarn>(DeleteAndEarnBottomUpOpt())
class DeleteAndEarnIterativeTest : DeleteAndEarnTest<DeleteAndEarn>(DeleteAndEarnIterative())
class DeleteAndEarnBestTest : DeleteAndEarnTest<DeleteAndEarn>(DeleteAndEarnBest())
