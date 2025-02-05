/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.sorts

import dev.shtanko.utils.measureMemFormatted
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class MemoryTest {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "BubbleSort",
                BubbleSort(),
            ),
            Arguments.of(
                "SimpleBubbleSort",
                SimpleBubbleSort(),
            ),
            Arguments.of(
                "InsertionSort",
                InsertionSort(),
            ),
            Arguments.of(
                "InsertionSort2",
                InsertionSort2(),
            ),
            Arguments.of(
                "MergeSort",
                MergeSort(),
            ),
            Arguments.of(
                "QuickSort",
                QuickSort(),
            ),
            Arguments.of(
                "SelectionSort",
                SelectionSort(),
            ),
            Arguments.of(
                "ShellSort",
                ShellSort(),
            ),
            Arguments.of(
                "HeapSort",
                HeapSort(),
            ),
            Arguments.of(
                "ArraySort",
                ArraySort(),
            ),
            Arguments.of(
                "PancakeSort",
                PancakeSort(),
            ),
            Arguments.of(
                "GnomeSort",
                GnomeSort(),
            ),
            Arguments.of(
                "BinarySort",
                BinarySort(),
            ),
        )
    }

    companion object {
        // 1000_000 //uncomment for original test
        private const val ARRAY_SIZE = 5000

        private fun getSortedArray(): IntArray {
            val array = IntArray(ARRAY_SIZE)
            for (i in 0 until ARRAY_SIZE) {
                array[i] = i
            }
            return array
        }
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `sorts test`(name: String, testCase: AbstractSortStrategy) {
        execute(name, testCase, getSortedArray())
    }

    private fun execute(name: String, strategy: AbstractSortStrategy, array: IntArray) {
        val (msg, result) = measureMemFormatted(name) {
            strategy.perform(array.toTypedArray())
            array.toTypedArray()
        }
        println(msg)
        assertTrue(result.isSorted())
    }
}
