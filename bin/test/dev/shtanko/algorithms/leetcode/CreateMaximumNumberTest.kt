/*
 * Copyright 2020 Oleksii Shtanko
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

class CreateMaximumNumberTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(3, 4, 6, 5),
                intArrayOf(9, 1, 2, 5, 8, 3),
                5,
                intArrayOf(9, 8, 6, 5, 3),
            ),
            Arguments.of(
                intArrayOf(6, 7),
                intArrayOf(6, 0, 4),
                5,
                intArrayOf(6, 7, 6, 0, 4),
            ),
            Arguments.of(
                intArrayOf(3, 9),
                intArrayOf(8, 9),
                3,
                intArrayOf(9, 8, 9),
            ),
            Arguments.of(
                intArrayOf(3, 9, 8),
                intArrayOf(8, 9),
                3,
                intArrayOf(9, 8, 9),
            ),
            Arguments.of(
                intArrayOf(3, 9, 8),
                intArrayOf(8, 9),
                2,
                intArrayOf(9, 9),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `maximum number test`(nums1: IntArray, nums2: IntArray, k: Int, expected: IntArray) {
        val actual = maxNumber(nums1, nums2, k)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}
