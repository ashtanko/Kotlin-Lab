/*
 * Copyright 2022 Alexey Shtanko
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

abstract class ConstrainedSubsequenceSumTest<out T : ConstrainedSubsequenceSum>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(10, 2, -10, 5, 20),
                2,
                37
            ),
            Arguments.of(
                intArrayOf(-1, -2, -3),
                1,
                -1
            ),
            Arguments.of(
                intArrayOf(10, -2, -10, -5, 20),
                2,
                23
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `constrained subset sum test`(nums: IntArray, k: Int, expected: Int) {
        val actual = strategy.perform(nums, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class ConstrainedSubsequenceSumDequeTest :
    ConstrainedSubsequenceSumTest<ConstrainedSubsequenceSum>(ConstrainedSubsequenceSumDeque())

class ConstrainedSubsequenceSumDPTest :
    ConstrainedSubsequenceSumTest<ConstrainedSubsequenceSum>(ConstrainedSubsequenceSumDP())

class ConstrainedSubsequenceSumQueueTest :
    ConstrainedSubsequenceSumTest<ConstrainedSubsequenceSum>(ConstrainedSubsequenceSumQueue())

class ConstrainedSubsequenceSumQueueOptTest :
    ConstrainedSubsequenceSumTest<ConstrainedSubsequenceSum>(ConstrainedSubsequenceSumQueueOpt())
