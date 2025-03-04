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

abstract class DistantBarcodesTest<out T : DistantBarcodes>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 1, 1, 2, 2, 2),
                intArrayOf(2, 1, 2, 1, 2, 1),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1, 2, 2, 3, 3),
                intArrayOf(1, 3, 1, 3, 1, 2, 1, 2),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1),
                intArrayOf(1),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                intArrayOf(1, 2),
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2),
                intArrayOf(1, 2, 1, 2),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 2, 2, 3),
                intArrayOf(1, 3, 1, 2, 1, 2),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `rearrange barcodes test`(barcodes: IntArray, expected: IntArray) {
        val actual = strategy.rearrangeBarcodes(barcodes)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}

class DistantBarcodesMapTest : DistantBarcodesTest<DistantBarcodes>(DistantBarcodesMap())
class DistantBarcodesQueueTest : DistantBarcodesTest<DistantBarcodes>(DistantBarcodesQueue())
