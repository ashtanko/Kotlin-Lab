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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ArrayStringsAreEqualTest<out T : ArrayStringsAreEqual>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("ab", "c"),
                arrayOf("a", "bc"),
                true,
            ),
            Arguments.of(
                arrayOf("a", "cb"),
                arrayOf("ab", "c"),
                false,
            ),
            Arguments.of(
                arrayOf("abc", "d", "defg"),
                arrayOf("abcddefg"),
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun arrStringsAreEqualTest(word1: Array<String>, word2: Array<String>, expected: Boolean) {
        val actual = strategy(word1, word2)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ArrayStringsAreEqualCompareTest : ArrayStringsAreEqualTest<ArrayStringsAreEqual>(ArrayStringsAreEqualCompare())
class ArrayStringsAreEqualTwoPointersTest :
    ArrayStringsAreEqualTest<ArrayStringsAreEqual>(ArrayStringsAreEqualTwoPointers())
