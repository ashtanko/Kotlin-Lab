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

abstract class RotateImageTest<out T : RotateImage>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(4, 5, 6),
                    intArrayOf(7, 8, 9),
                ),
                arrayOf(
                    intArrayOf(7, 4, 1),
                    intArrayOf(8, 5, 2),
                    intArrayOf(9, 6, 3),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(5, 1, 9, 11),
                    intArrayOf(2, 4, 8, 10),
                    intArrayOf(13, 3, 6, 7),
                    intArrayOf(15, 14, 12, 16),
                ),
                arrayOf(
                    intArrayOf(15, 13, 2, 5),
                    intArrayOf(14, 3, 4, 1),
                    intArrayOf(12, 6, 8, 9),
                    intArrayOf(16, 7, 10, 11),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `rotate image test`(matrix: Array<IntArray>, expected: Array<IntArray>) {
        strategy.rotate(matrix)
        assertThat(matrix).isEqualTo(expected)
    }
}

class RotateGroupsTest : RotateImageTest<RotateGroups>(RotateGroups())
class ReverseLeftToRightTest : RotateImageTest<ReverseLeftToRight>(ReverseLeftToRight())
