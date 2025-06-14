/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

class ReverseLinkedListTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = args().stream()

        private fun args() = testCases().map {
            Arguments.of(::reverseListIterative, it)
        } + testCases().map {
            Arguments.of(::reverseListRecursive, it)
        }

        private fun testCases() = listOf(
            listOf(0) to listOf(0),
            listOf<Int>() to listOf(0),
            listOf(1) to listOf(1),
            listOf(1, 2) to listOf(2, 1),
            listOf(2, 1) to listOf(1, 2),
            listOf(-1, -2) to listOf(-2, -1),
            listOf(1, 2, 3, 4, 5) to listOf(5, 4, 3, 2, 1),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `reverse linked list test`(
        strategy: (head: ListNode?) -> ListNode?,
        testCase: Pair<List<Int>, List<Int>>,
    ) {
        val (list, expected) = testCase
        val reversed = strategy(list.toListNode())?.toList() ?: emptyList()
        assertThat(reversed).isEqualTo(expected)
    }
}
