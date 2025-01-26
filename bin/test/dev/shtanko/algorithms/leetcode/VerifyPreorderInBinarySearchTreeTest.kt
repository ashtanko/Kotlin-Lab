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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class VerifyPreorderInBinarySearchTreeTest<out T : VerifyPreorderInBinarySearchTreeStrategy>(
    private val strategy: T,
) {

    //      5
    //     / \
    //    2   6
    //   / \
    //  1   3
    // Preorder (Root, Left, Right)
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(intArrayOf(5, 2, 6, 1, 3), false),
            Arguments.of(intArrayOf(5, 2, 1, 3, 6), true),
            Arguments.of(intArrayOf(5, 2, 1, 3, 6, 7), true),
            Arguments.of(intArrayOf(5, 2, 1, 3, 6, 4), false),
            Arguments.of(intArrayOf(5, 2, 1, 3, 6, 4, 7), false),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `verify test`(preorder: IntArray, expected: Boolean) {
        val actual = strategy.invoke(preorder)
        assertEquals(expected, actual)
    }
}

class VerifyPreorderInBinarySearchTreeBFTest :
    VerifyPreorderInBinarySearchTreeTest<VerifyPreorderInBinarySearchTreeBF>(VerifyPreorderInBinarySearchTreeBF())

class VerifyPreorderInBinarySearchTreeStackTest :
    VerifyPreorderInBinarySearchTreeTest<VerifyPreorderInBinarySearchTreeStack>(VerifyPreorderInBinarySearchTreeStack())

class VerifyPreorderInBinarySearchTreeRecursionTest :
    VerifyPreorderInBinarySearchTreeTest<VerifyPreorderInBinarySearchTreeRecursion>(
        VerifyPreorderInBinarySearchTreeRecursion(),
    )
