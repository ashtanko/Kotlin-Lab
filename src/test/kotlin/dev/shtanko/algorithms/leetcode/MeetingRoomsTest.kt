/*
 * Copyright 2020 Alexey Shtanko
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal abstract class MeetingRoomsTest<out T : MeetingRoomsStrategy>(private val strategy: T) {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 30),
                    intArrayOf(5, 10),
                    intArrayOf(15, 20)
                ),
                false
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(7, 10),
                    intArrayOf(2, 4)
                ),
                true
            )
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `can attend meetings test`(intervals: Array<IntArray>, expected: Boolean) {
        val actual = strategy.canAttendMeetings(intervals)
        assertEquals(expected, actual)
    }
}

internal class MeetingRoomsBruteForceTest : MeetingRoomsTest<MeetingRoomsBruteForce>(MeetingRoomsBruteForce())
internal class MeetingRoomsSortingTest : MeetingRoomsTest<MeetingRoomsSorting>(MeetingRoomsSorting())
