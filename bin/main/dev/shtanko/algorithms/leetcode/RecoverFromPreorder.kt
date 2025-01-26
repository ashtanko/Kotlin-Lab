/*
 * Copyright 2022 Oleksii Shtanko
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

import dev.shtanko.algorithms.DECIMAL
import java.util.Stack

/**
 * 1028. Recover a Tree From Preorder Traversal
 * @see <a href="https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/">Source</a>
 */
fun interface RecoverFromPreorder {
    operator fun invoke(traversal: String): TreeNode?
}

class RecoverFromPreorderIterative : RecoverFromPreorder {
    override operator fun invoke(traversal: String): TreeNode? {
        var level: Int
        var value: Int
        val stack: Stack<TreeNode> = Stack()

        var i = 0
        while (i < traversal.length) {
            level = 0
            while (traversal[i] == '-') {
                level++
                i++
            }
            value = 0
            while (i < traversal.length && traversal[i] != '-') {
                value = value * DECIMAL + (traversal[i] - '0')
                i++
            }
            while (stack.size > level) {
                stack.pop()
            }
            val node = TreeNode(value)
            if (stack.isNotEmpty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node
                } else {
                    stack.peek().right = node
                }
            }
            stack.add(node)
        }

        while (stack.size > 1) {
            stack.pop()
        }
        return stack.pop()
    }
}

class RecoverFromPreorderRecursive : RecoverFromPreorder {
    var i = 0
    override operator fun invoke(traversal: String): TreeNode? {
        return helper(traversal, -1)
    }

    private fun helper(s: String, pLevel: Int): TreeNode? {
        if (i >= s.length) {
            return null
        }
        var count = 0
        var j: Int = i
        while (!Character.isDigit(s[j])) {
            j++
            count++
        }
        return if (count == pLevel + 1) {
            val start = j
            while (j < s.length && Character.isDigit(s[j])) {
                j++
            }
            val value = s.substring(start, j).toInt()
            i = j
            val node = TreeNode(value)
            node.left = helper(s, count)
            node.right = helper(s, count)
            node
        } else {
            null
        }
    }
}
