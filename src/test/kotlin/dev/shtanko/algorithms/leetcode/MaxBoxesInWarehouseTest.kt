/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

abstract class MaxBoxesInWarehouseTest<out T : MaxBoxesInWarehouse>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 3, 4, 1),
                intArrayOf(5, 3, 3, 4, 1),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 2, 2, 3, 4),
                intArrayOf(3, 4, 1, 2),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                intArrayOf(1, 2, 3, 4),
                1,
            ),
            Arguments.of(
                intArrayOf(4, 5, 6),
                intArrayOf(3, 3, 3, 3, 3),
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max boxes in warehouse test`(boxes: IntArray, warehouse: IntArray, expected: Int) {
        val actual = strategy.invoke(boxes, warehouse)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxBoxesInWarehouseAddTest : MaxBoxesInWarehouseTest<MaxBoxesInWarehouseAdd>(MaxBoxesInWarehouseAdd())
class MaxBoxesInWarehouseLPBTest : MaxBoxesInWarehouseTest<MaxBoxesInWarehouseLPB>(MaxBoxesInWarehouseLPB())
