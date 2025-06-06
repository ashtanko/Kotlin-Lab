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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class LongestValidParenthesesTest<out T : LongestValidParenthesesStrategy>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                0,
            ),
            Arguments.of(
                "(()",
                2,
            ),
            Arguments.of(
                ")()())",
                4,
            ),
        )
    }

    private class HighLoadInputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                0,
            ),
            Arguments.of(
                "(()",
                2,
            ),
            Arguments.of(
                ")()())",
                4,
            ),
            Arguments.of(
                "((())))()())))(((()()(())))((()(())()((()))())())())()()",
                42,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `longest valid parentheses brute force test`(s: String, expected: Int) {
        val actual = LongestValidParenthesesBruteForce().invoke(s)
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(HighLoadInputArgumentsProvider::class)
    fun `longest valid parentheses test`(s: String, expected: Int) {
        val actual = strategy.invoke(s)
        assertEquals(expected, actual)
    }
}

// Do not run on CI, time limit expended
// class LongestValidParenthesesBruteForceTest :
//    LongestValidParenthesesTest<LongestValidParenthesesBruteForce>(LongestValidParenthesesBruteForce())

class LongestValidParenthesesDPTest :
    LongestValidParenthesesTest<LongestValidParenthesesDP>(LongestValidParenthesesDP())

class LongestValidParenthesesStackTest :
    LongestValidParenthesesTest<LongestValidParenthesesStack>(LongestValidParenthesesStack())

class LongestValidParenthesesWithoutExtraSpaceTest :
    LongestValidParenthesesTest<LongestValidParenthesesWithoutExtraSpace>(LongestValidParenthesesWithoutExtraSpace())
