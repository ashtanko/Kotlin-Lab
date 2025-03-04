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

abstract class ParallelCourses3Test<out T : ParallelCourses3>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                arrayOf(intArrayOf(1, 3), intArrayOf(2, 3)),
                intArrayOf(3, 2, 5),
                8,
            ),
            Arguments.of(
                5,
                arrayOf(intArrayOf(1, 5), intArrayOf(2, 5), intArrayOf(3, 5), intArrayOf(3, 4), intArrayOf(4, 5)),
                intArrayOf(1, 2, 3, 4, 5),
                12,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `minimum time test`(num: Int, relations: Array<IntArray>, time: IntArray, expected: Int) {
        val actual = strategy(num, relations, time)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ParallelCourses3SortTest : ParallelCourses3Test<ParallelCourses3>(ParallelCourses3Sort())
class ParallelCourses3DFSTest : ParallelCourses3Test<ParallelCourses3>(ParallelCourses3DFS())
