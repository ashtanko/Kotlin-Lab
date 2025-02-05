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

abstract class ScheduleCourse3Test<out T : ScheduleCourse3>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(100, 200),
                    intArrayOf(200, 1300),
                    intArrayOf(1000, 1250),
                    intArrayOf(2000, 3200),
                ),
                3,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                ),
                1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2),
                    intArrayOf(4, 3),
                ),
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `schedule course test`(courses: Array<IntArray>, expected: Int) {
        val actual = strategy.invoke(courses)
        assertThat(actual).isEqualTo(expected)
    }
}

class ScheduleCourse3RecursionTest : ScheduleCourse3Test<ScheduleCourse3Recursion>(ScheduleCourse3Recursion())
class ScheduleCourse3IterativeTest : ScheduleCourse3Test<ScheduleCourse3Iterative>(ScheduleCourse3Iterative())
class ScheduleCourse3OptimizedIterativeTest :
    ScheduleCourse3Test<ScheduleCourse3OptimizedIterative>(ScheduleCourse3OptimizedIterative())

class ScheduleCourse3ExtraListTest :
    ScheduleCourse3Test<ScheduleCourse3ExtraList>(ScheduleCourse3ExtraList())

class ScheduleCourse3PriorityQueueTest :
    ScheduleCourse3Test<ScheduleCourse3PriorityQueue>(ScheduleCourse3PriorityQueue())
