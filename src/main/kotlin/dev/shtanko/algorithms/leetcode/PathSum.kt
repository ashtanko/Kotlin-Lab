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

import java.util.Stack

interface PathSumStrategy {
    fun hasPathSum(root: TreeNode?, sum: Int): Boolean
}

class PathSumRecursive : PathSumStrategy {
    override fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
        if (root == null) return false
        return if (root.left == null && root.right == null && sum - root.value == 0) true else hasPathSum(
            root.left,
            sum - root.value
        ) || hasPathSum(root.right, sum - root.value)
    }
}

class PathSumStack : PathSumStrategy {
    override fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
        val stack: Stack<TreeNode> = Stack()
        stack.push(root)
        while (!stack.isEmpty() && root != null) {
            val cur: TreeNode = stack.pop()
            if (cur.left == null && cur.right == null) {
                if (cur.value == sum) return true
            }
            if (cur.right != null) {
                cur.right?.value = cur.value + cur.right!!.value
                stack.push(cur.right)
            }
            if (cur.left != null) {
                cur.left?.value = cur.value + cur.left!!.value
                stack.push(cur.left)
            }
        }
        return false
    }
}
