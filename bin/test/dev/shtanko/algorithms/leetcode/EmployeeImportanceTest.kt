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

import dev.shtanko.algorithms.leetcode.EmployeeImportance.Employee
import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class EmployeeImportanceTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                listOf(
                    Employee(1, 5, listOf(2, 3)),
                    Employee(2, 3, listOf()),
                    Employee(3, 3, listOf()),
                ),
                11,
            ),
            Arguments.of(
                0,
                emptyList<Employee>(),
                0,
            ),
            Arguments.of(
                0,
                listOf(
                    Employee(),
                ),
                0,
            ),
            Arguments.of(
                1,
                listOf(
                    Employee(1),
                    Employee(1),
                ),
                0,
            ),
            Arguments.of(
                1,
                listOf(
                    Employee(1, 5, listOf(2, 3)),
                    Employee(2, 3, listOf(4)),
                    Employee(3, 3, listOf()),
                    Employee(4, 1, listOf()),
                ),
                12,
            ),
            Arguments.of(
                1,
                listOf(
                    Employee(1, 2, listOf(2)),
                    Employee(2, 3, listOf()),
                ),
                5,
            ),
            Arguments.of(
                1,
                listOf(
                    Employee(1, 2, listOf(2)),
                    Employee(2, 3, listOf(3)),
                    Employee(3, 3, listOf()),
                ),
                8,
            ),
            Arguments.of(
                1,
                listOf(
                    Employee(1, 2, listOf(2)),
                    Employee(2, 3, listOf(3)),
                    Employee(3, 3, listOf(4)),
                    Employee(4, 4, listOf()),
                ),
                12,
            ),
            Arguments.of(
                1,
                listOf(
                    Employee(1, 2, listOf(2)),
                    Employee(2, 3, listOf(3)),
                    Employee(3, 3, listOf(4)),
                    Employee(4, 4, listOf(5)),
                    Employee(5, 5, listOf()),
                ),
                17,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `employee importance test`(id: Int, employees: List<Employee>, expected: Int) {
        val actual = EmployeeImportance.getImportance(employees, id)
        assertThat(actual, equalTo(expected))
    }
}
