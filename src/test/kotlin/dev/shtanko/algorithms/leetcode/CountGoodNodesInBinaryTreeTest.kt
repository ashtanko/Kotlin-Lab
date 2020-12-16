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

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class CountGoodNodesInBinaryTreeTest {

    companion object {
        @JvmStatic
        fun dataProvider(): Stream<Arguments?> = Stream.of(
            Arguments.of(intArrayOf(3, 1, 4, 3, 1, 5).toTree(), 4),
            Arguments.of(intArrayOf(3, 3, 4, 2).toTree(), 3)
        )
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    internal fun `count good nodes in binary tree test`(tree: TreeNode, expected: Int) {
        val actual = CountGoodNodesInBinaryTree().goodNodes(tree)
        assertEquals(expected, actual)
    }
}
