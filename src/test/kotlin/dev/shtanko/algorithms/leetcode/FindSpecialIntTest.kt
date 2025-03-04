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

abstract class FindSpecialIntTest<out T : FindSpecialInt>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 2, 6, 6, 6, 6, 7, 10),
                6,
            ),
            Arguments.of(
                intArrayOf(1, 1),
                1,
            ),
            Arguments.of(
                intArrayOf(),
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun findSpecialIntegerTest(arr: IntArray, expected: Int) {
        val actual = strategy(arr)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class FindSpecialIntHashMapTest : FindSpecialIntTest<FindSpecialInt>(FindSpecialIntHashMap())
class FindSpecialIntCheckElementTest : FindSpecialIntTest<FindSpecialInt>(FindSpecialIntCheckElement())
class FindSpecialIntBSTest : FindSpecialIntTest<FindSpecialInt>(FindSpecialIntBS())
