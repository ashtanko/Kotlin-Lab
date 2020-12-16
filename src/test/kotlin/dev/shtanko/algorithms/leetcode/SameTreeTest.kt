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
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class SameTreeTest {

    companion object {
        @JvmStatic
        fun dataProvider(): List<Pair<Pair<TreeNode, TreeNode>, Boolean>> {
            return listOf(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                } to TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                } to true,
                TreeNode(1).apply {
                    left = TreeNode(2)
                } to TreeNode(1).apply {
                    right = TreeNode(2)
                } to false,
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(2)
                } to TreeNode(1).apply {
                    right = TreeNode(2)
                    left = TreeNode(1)
                } to false
            )
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    internal fun `is same test test`(testCase: Pair<Pair<TreeNode, TreeNode>, Boolean>) {
        val (data, expected) = testCase
        val actual = data.isSame()
        assertEquals(expected, actual)
    }
}
