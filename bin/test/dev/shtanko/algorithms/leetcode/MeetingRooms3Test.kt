/*
 * Copyright 2024 Oleksii Shtanko
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

abstract class MeetingRooms3Test<out T : MeetingRooms3>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2,
                arrayOf(intArrayOf(0, 10), intArrayOf(1, 5), intArrayOf(2, 7), intArrayOf(3, 4)),
                0,
            ),
            Arguments.of(
                3,
                arrayOf(intArrayOf(1, 20), intArrayOf(2, 10), intArrayOf(3, 5), intArrayOf(4, 9), intArrayOf(6, 8)),
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `most booked test`(num: Int, meetings: Array<IntArray>, expected: Int) {
        val actual = strategy(num, meetings)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MeetingRooms3CountingTest : MeetingRooms3Test<MeetingRooms3>(MeetingRooms3Counting())
class MeetingRooms3PQTest : MeetingRooms3Test<MeetingRooms3>(MeetingRooms3PQ())
