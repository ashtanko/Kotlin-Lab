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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class MinScoreTest<out T : MinScore>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(1, 2, 9),
                    intArrayOf(2, 3, 6),
                    intArrayOf(2, 4, 5),
                    intArrayOf(1, 4, 7),
                ),
                5,
            ),
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(1, 2, 2),
                    intArrayOf(1, 3, 4),
                    intArrayOf(3, 4, 7),
                ),
                2,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min score test`(num: Int, roads: Array<IntArray>, expected: Int) {
        val actual = strategy.invoke(num, roads)
        assertThat(actual).isEqualTo(expected)
    }
}

class MinScoreBFSTest : MinScoreTest<MinScore>(MinScoreBFS())
class MinScoreDFSTest : MinScoreTest<MinScore>(MinScoreDFS())
