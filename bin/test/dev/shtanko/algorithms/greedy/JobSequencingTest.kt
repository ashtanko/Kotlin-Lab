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

package dev.shtanko.algorithms.greedy

import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class JobSequencingTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    Job('a', 2, 100),
                    Job('b', 1, 19),
                    Job('c', 2, 27),
                    Job('d', 1, 25),
                    Job('e', 3, 15),
                ),
                listOf('c', 'a', 'e'),
            ),
            Arguments.of(
                arrayOf(
                    Job('a', 3, 30),
                    Job('b', 5, 12),
                    Job('c', 1, 2),
                    Job('d', 7, 25),
                    Job('e', 12, 5),
                    Job('f', 12, 5),
                ),
                listOf('c', 'f', 'a', 'e', 'b', 'd'),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `job sequencing test`(jobs: Array<Job>, expected: List<Char>) {
        val actual = jobs.scheduleJobs()
        assertThat(actual, equalTo(expected))
    }
}
