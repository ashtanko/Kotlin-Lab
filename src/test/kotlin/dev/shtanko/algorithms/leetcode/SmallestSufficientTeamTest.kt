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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SmallestSufficientTeamTest<out T : SmallestSufficientTeam>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("java", "nodejs", "reactjs"),
                listOf(
                    listOf("java"),
                    listOf("nodejs"),
                    listOf("nodejs", "reactjs"),
                ),
                intArrayOf(0, 2),
            ),
            Arguments.of(
                arrayOf("algorithms", "math", "java", "reactjs", "csharp", "aws"),
                listOf(
                    listOf("algorithms", "math", "java"),
                    listOf("algorithms", "math", "reactjs"),
                    listOf("java", "csharp", "aws"),
                    listOf("reactjs", "csharp"),
                    listOf("csharp", "math"),
                    listOf("aws", "java"),
                ),
                intArrayOf(1, 2),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `smallest sufficient team test`(reqSkills: Array<String>, people: List<List<String>>, expected: IntArray) {
        val actual = strategy.invoke(reqSkills, people)
        assertThat(actual).isEqualTo(expected)
    }
}

class SmallestSufficientTeamDFSTest : SmallestSufficientTeamTest<SmallestSufficientTeam>(SmallestSufficientTeamDFS())
