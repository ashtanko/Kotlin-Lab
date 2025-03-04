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
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class NextPermutationTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `next permutation test`(arr: IntArray, expected: IntArray) {
        arr.nextPermutation()
        assertArrayEquals(expected, arr)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = listOf(
            intArrayOf(1, 3, 2) to intArrayOf(1, 2, 3),
            intArrayOf(1, 2, 3) to intArrayOf(3, 2, 1),
            intArrayOf(1, 5, 1) to intArrayOf(1, 1, 5),
            intArrayOf(2, 1, 3) to intArrayOf(1, 3, 2),
        ).map { Arguments.of(it.second, it.first) }.stream()
    }
}
