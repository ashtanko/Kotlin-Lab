/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.templates

import dev.shtanko.algorithms.leetcode.TreeNode
import java.util.Stack

/**
 * Binary tree: DFS (iterative)
 */
private fun dfs(root: TreeNode?): Int {
    val stack: Stack<TreeNode> = Stack()
    stack.push(root)
    val ans = 0
    while (!stack.empty()) {
        val node: TreeNode = stack.pop()
        // do logic
        if (node.left != null) {
            stack.push(node.left)
        }
        if (node.right != null) {
            stack.push(node.right)
        }
    }
    return ans
}
