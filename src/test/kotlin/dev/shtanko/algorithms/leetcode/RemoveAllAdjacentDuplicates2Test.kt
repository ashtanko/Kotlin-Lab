/*
 * Copyright 2020 Alexey Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal abstract class RemoveAllAdjacentDuplicates2Test<out T : RemoveAllAdjacentDuplicatesStrategy2>(private val strategy: T) {

    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("abcd", 2, "abcd"),
            Arguments.of("deeedbbcccbdaa", 3, "aa"),
            Arguments.of("pbbcggttciiippooaais", 2, "ps"),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `remove all adjacent duplicates in string test`(s: String, k: Int, expected: String) {
        val actual = strategy.perform(s, k)
        assertEquals(expected, actual)
    }
}

internal class RemoveDuplicates2BruteForceTest :
    RemoveAllAdjacentDuplicates2Test<RemoveDuplicates2BruteForce>(RemoveDuplicates2BruteForce())

internal class RemoveDuplicates2MemoiseTest :
    RemoveAllAdjacentDuplicates2Test<RemoveDuplicates2Memoise>(RemoveDuplicates2Memoise())

internal class RemoveDuplicates2StackTest :
    RemoveAllAdjacentDuplicates2Test<RemoveDuplicates2Stack>(RemoveDuplicates2Stack())

internal class RemoveDuplicates2StackReconstructionTest :
    RemoveAllAdjacentDuplicates2Test<RemoveDuplicates2StackReconstruction>(RemoveDuplicates2StackReconstruction())

internal class RemoveDuplicates2TwoPointersTest :
    RemoveAllAdjacentDuplicates2Test<RemoveDuplicates2TwoPointers>(RemoveDuplicates2TwoPointers())
