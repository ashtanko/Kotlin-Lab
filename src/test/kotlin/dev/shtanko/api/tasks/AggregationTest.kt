/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.api.tasks

import dev.shtanko.api.model.User
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

class AggregationTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    User("1", 10),
                    User("2", 10),
                    User("3", 10),
                    User("1", 10),
                ),
                listOf(
                    User("1", 20),
                    User("2", 10),
                    User("3", 10),
                ),
            ),
            Arguments.of(
                listOf(
                    User("1", 5),
                    User("1", 5),
                    User("3", 10),
                ),
                listOf(
                    User("1", 10),
                    User("3", 10),
                ),
            ),
            Arguments.of(
                listOf(
                    User("1", 10),
                    User("2", 10),
                    User("3", 10),
                ),
                listOf(
                    User("1", 10),
                    User("2", 10),
                    User("3", 10),
                ),
            ),
            Arguments.of(
                listOf(
                    User("1", 10),
                    User("1", 10),
                    User("1", 10),
                ),
                listOf(
                    User("1", 30),
                ),
            ),
            Arguments.of(
                listOf(
                    User("1", 10),
                    User("1", 10),
                    User("2", 10),
                    User("2", 10),
                ),
                listOf(
                    User("1", 20),
                    User("2", 20),
                ),
            ),
            Arguments.of(
                listOf(
                    User("1", 10),
                ),
                listOf(
                    User("1", 10),
                ),
            ),
            Arguments.of(
                emptyList<User>(),
                emptyList<User>(),
            ),
        )
    }

    @ArgumentsSource(InputArgumentsProvider::class)
    @ParameterizedTest
    fun `aggregate users test`(users: List<User>, expected: List<User>) {
        val actual = users.aggregate()
        assertThat(actual).isEqualTo(expected)
    }
}
