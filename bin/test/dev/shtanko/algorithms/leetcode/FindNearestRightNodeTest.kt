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

abstract class FindNearestRightNodeTest<out T : FindNearestRightNodeStrategy>(private val strategy: T) {

    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        right = TreeNode(4)
                    }
                    right = TreeNode(3).apply {
                        left = TreeNode(5)
                        right = TreeNode(6)
                    }
                },
                TreeNode(4),
                TreeNode(5),
            ),
            Arguments.of(
                TreeNode(3).apply {
                    right = TreeNode(4).apply {
                        left = TreeNode(2)
                    }
                },
                TreeNode(2),
                null,
            ),
            Arguments.of(
                TreeNode(1),
                TreeNode(1),
                null,
            ),
            Arguments.of(
                null,
                TreeNode(1),
                null,
            ),
            Arguments.of(
                null,
                null,
                null,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3)
                    }
                },
                TreeNode(3),
                null,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find nearest right node in binary tree test`(root: TreeNode?, u: TreeNode?, expected: TreeNode?) {
        val actual = strategy.invoke(root, u).levelOrder().flatten()
        assertEquals(expected.levelOrder().flatten(), actual)
    }
}

class FindNearestRightNodeTwoQueuesTest :
    FindNearestRightNodeTest<FindNearestRightNodeTwoQueues>(FindNearestRightNodeTwoQueues())

class FindNearestRightNodeSentinelTest :
    FindNearestRightNodeTest<FindNearestRightNodeSentinel>(FindNearestRightNodeSentinel())

class FindNearestRightNodeSizeMeasurementsTest :
    FindNearestRightNodeTest<FindNearestRightNodeSizeMeasurements>(FindNearestRightNodeSizeMeasurements())

class FindNearestRightNodeSizePreorderTraversalTest :
    FindNearestRightNodeTest<FindNearestRightNodeSizePreorderTraversal>(FindNearestRightNodeSizePreorderTraversal())
