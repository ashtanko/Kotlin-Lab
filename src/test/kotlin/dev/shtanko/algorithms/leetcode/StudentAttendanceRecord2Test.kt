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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class StudentAttendanceRecord2Test<out T : StudentAttendanceRecord2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2,
                8,
            ),
            Arguments.of(
                1,
                3,
            ),
            Arguments.of(
                10101,
                183236316,
            ),
            Arguments.of(
                0,
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun checkRecordTest(num: Int, expected: Int) {
        val actual = strategy(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class StudentAttendanceRecord2TopDownMemoizationTest :
    StudentAttendanceRecord2Test<StudentAttendanceRecord2>(StudentAttendanceRecord2TopDownMemoization())

class StudentAttendanceRecord2BottomUpDPTest :
    StudentAttendanceRecord2Test<StudentAttendanceRecord2>(StudentAttendanceRecord2BottomUpDP())

class StudentAttendanceRecord2BottomUpSpaceOptimizedDPTest :
    StudentAttendanceRecord2Test<StudentAttendanceRecord2>(StudentAttendanceRecord2BottomUpSpaceOptimizedDP())
