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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class XorAllNumsTest<out T : XorAllNums>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(2, 1, 3),
                intArrayOf(10, 2, 5, 0),
                13,
            ),
            Arguments.of(
                intArrayOf(1, 2),
                intArrayOf(3, 4),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                7,
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                intArrayOf(),
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `xor all nums test`(nums1: IntArray, nums2: IntArray, expected: Int) {
        val actual = strategy.invoke(nums1, nums2)
        assertThat(actual).isEqualTo(expected)
    }
}

class XorAllNumsConciseTest : XorAllNumsTest<XorAllNums>(XorAllNumsConcise())
class XorAllNumsSimpleTest : XorAllNumsTest<XorAllNums>(XorAllNumsSimple())
