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

abstract class SingleThreadedCPUTest<out T : SingleThreadedCPU>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 4),
                    intArrayOf(3, 2),
                    intArrayOf(4, 1),
                ),
                intArrayOf(0, 2, 3, 1),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(7, 10),
                    intArrayOf(7, 12),
                    intArrayOf(7, 5),
                    intArrayOf(7, 4),
                    intArrayOf(7, 2),
                ),
                intArrayOf(4, 3, 2, 0, 1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `get order test`(tasks: Array<IntArray>, expected: IntArray) {
        val actual = strategy.getOrder(tasks)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}

class SingleThreadedCPUQueueTest : SingleThreadedCPUTest<SingleThreadedCPU>(SingleThreadedCPUQueue())
