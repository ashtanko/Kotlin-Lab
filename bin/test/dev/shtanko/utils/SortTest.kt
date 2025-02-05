/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.utils

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.ValueSource

class SortTest {

    private class MergeInputParamsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                getMetadata(intArrayOf(1, 3, 2)),
                intArrayOf(1, 2, 3),
            ),
            Arguments.of(
                getMetadata(intArrayOf(3, 2, 1)),
                intArrayOf(1, 3, 2),
            ),
            Arguments.of(
                getMetadata(intArrayOf(3, 2)),
                intArrayOf(2, 3),
            ),
            Arguments.of(
                getMetadata(intArrayOf(2, 3)),
                intArrayOf(2, 3),
            ),
            Arguments.of(
                getMetadata(intArrayOf(1)),
                intArrayOf(1),
            ),
            Arguments.of(
                getMetadata(intArrayOf(2, 2, 2)),
                intArrayOf(2, 2, 2),
            ),
        )

        private fun getMetadata(array: IntArray): Pair<IntArray, Triple<Int, Int, Int>> {
            val low = 0
            val high = array.size - 1
            val mid = low.plus(high).div(2)
            return array to Triple(low, mid, high)
        }
    }

    private class ArrayMaxInputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                5,
            ),
            Arguments.of(
                intArrayOf(3),
                3,
            ),
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(0),
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                1,
            ),
            Arguments.of(
                intArrayOf(4, 6, 8, 2, 3),
                8,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(MergeInputParamsProvider::class)
    fun `merge test`(data: Pair<IntArray, Triple<Int, Int, Int>>, expected: IntArray) {
        val array = data.first
        val (low, middle, high) = data.second
        val helper = IntArray(array.size)
        merge(array, helper, low, middle, high)
        assertThat(array).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [10, 100, 1000, 10_000, 100_000])
    fun `new random array test`(num: Int) {
        val array = num.toRandomArray()
        assertThat(array.size).isEqualTo(num)
    }

    @ParameterizedTest
    @ArgumentsSource(ArrayMaxInputArgumentsProvider::class)
    fun `maximum test`(nums: IntArray, expected: Int) {
        val actual = nums.maximum()
        assertThat(actual).isEqualTo(expected)
    }
}
