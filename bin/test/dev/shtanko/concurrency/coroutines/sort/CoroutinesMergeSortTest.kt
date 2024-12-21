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

package dev.shtanko.concurrency.coroutines.sort

import dev.shtanko.algorithms.sorts.isSorted
import dev.shtanko.concurrency.TestBase
import dev.shtanko.utils.toRandomArray
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class CoroutinesMergeSortTest : TestBase() {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1000.genData(),
            ),
            Arguments.of(
                5000.genData(),
            ),
            Arguments.of(
                10_000.genData(),
            ),
            Arguments.of(
                100_000.genData(),
            ),
            Arguments.of(
                500_000.genData(),
            ),
            Arguments.of(
                1000_000.genData(),
            ),
        )

        private fun Int.genData(): Pair<IntArray, IntArray> {
            val arr = this.toRandomArray()
            val expected = arr.sorted().toIntArray()
            return arr to expected
        }
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `test merge sort`(data: Pair<IntArray, IntArray>) = runTest {
        val (array, expected) = data
        val actual = CoroutinesMergeSort().perform(array)
        assertThat(actual).isEqualTo(expected)
        assertThat(actual.isSorted()).isTrue()
    }
}
