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

import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.DP
import dev.shtanko.algorithms.annotations.Greedy
import dev.shtanko.algorithms.annotations.level.Hard
import kotlin.math.min

/**
 * 968. Binary Tree Cameras
 * @see <a href="https://leetcode.com/problems/binary-tree-cameras/">Source</a>
 */
@Hard("https://leetcode.com/problems/binary-tree-cameras")
fun interface BinaryTreeCameras {
    operator fun invoke(root: TreeNode?): Int
}

@DFS
class BinaryTreeCamerasDFS : BinaryTreeCameras {

    private var cameras = 0

    override operator fun invoke(root: TreeNode?): Int {
        if (root == null) return 0
        val top = root.dfs()
        val local = if (top == NOT_MONITORED) 1 else 0
        return cameras + local
    }

    private fun TreeNode?.dfs(): Int {
        if (this == null) return MONITORED_NO_CAM
        val left = this.left.dfs()
        val right = this.right.dfs()
        return if (left == MONITORED_NO_CAM && right == MONITORED_NO_CAM) {
            NOT_MONITORED
        } else if (left == NOT_MONITORED || right == NOT_MONITORED) {
            cameras++
            MONITORED_WITH_CAM
        } else {
            MONITORED_NO_CAM
        }
    }

    companion object {
        private const val NOT_MONITORED = 0
        private const val MONITORED_NO_CAM = 1
        private const val MONITORED_WITH_CAM = 2
    }
}

@DP
class BinaryTreeCamerasDP : BinaryTreeCameras {
    override operator fun invoke(root: TreeNode?): Int {
        val result = calculate(root)
        return min(result[1], result[2])
    }

    // 0: Strict ST; All nodes below this are covered, but not this one
    // 1: Normal ST; All nodes below and incl this are covered - no camera
    // 2: Placed camera; All nodes below this are covered, plus camera here
    private fun calculate(node: TreeNode?): IntArray {
        if (node == null) return intArrayOf(0, 0, MAX_ARRAY_SIZE)
        val left = calculate(node.left)
        val right = calculate(node.right)
        val minLeft12 = min(left[1], left[2])
        val minRight12 = min(right[1], right[2])
        val state0 = left[1] + left[1]
        val state1 = min(left[2] + minRight12, right[2] + minLeft12)
        val state2 = 1 + min(left[0], minLeft12) + min(right[0], minRight12)
        return intArrayOf(state0, state1, state2)
    }

    companion object {
        private const val MAX_ARRAY_SIZE = 99999
    }
}

@Greedy
class BinaryTreeCamerasGreedy : BinaryTreeCameras {
    private var res = 0
    override operator fun invoke(root: TreeNode?): Int {
        return (if (dfs(root) < 1) 1 else 0) + res
    }

    private fun dfs(root: TreeNode?): Int {
        if (root == null) return 2
        val left = dfs(root.left)
        val right = dfs(root.right)
        if (left == 0 || right == 0) {
            res++
            return 1
        }
        return if (left == 1 || right == 1) 2 else 0
    }
}
