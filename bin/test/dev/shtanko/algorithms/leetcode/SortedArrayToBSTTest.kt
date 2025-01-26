/*
 * Copyright 2021 Oleksii Shtanko
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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SortedArrayToBSTTest<out T : SortedArrayToBST>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(-10, -3, 0, 5, 9),
                TreeNode(0).apply {
                    left = TreeNode(-10).apply {
                        right = TreeNode(-3)
                    }
                    right = TreeNode(5).apply {
                        right = TreeNode(9)
                    }
                },
            ),
            Arguments.of(
                intArrayOf(1, 3),
                TreeNode(1).apply {
                    left = TreeNode(3)
                },
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sortedArrayToBST test`(nums: IntArray, expected: TreeNode?) {
        val actual = strategy.invoke(nums).postOrderTraversal()
        assertThat(actual).containsAll(expected.postOrderTraversal())
    }
}

class SortedArrayToBSTPreorderTest : SortedArrayToBSTTest<SortedArrayToBSTPreorder>(SortedArrayToBSTPreorder())
class RightMiddleSortedArrayToBSTTest :
    SortedArrayToBSTTest<RightMiddleSortedArrayToBST>(RightMiddleSortedArrayToBST())

class RandomMiddleSortedArrayToBSTTest :
    SortedArrayToBSTTest<RandomMiddleSortedArrayToBST>(RandomMiddleSortedArrayToBST())
