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

import java.util.Stack

fun interface ConstructStringFromBinaryTreeStrategy {
    operator fun invoke(t: TreeNode?): String
}

class ConstructStringFromBinaryTreeRecursion : ConstructStringFromBinaryTreeStrategy {
    override operator fun invoke(t: TreeNode?): String {
        if (t == null) {
            return ""
        }
        if (t.left == null && t.right == null) {
            return "${t.value}"
        }
        val leftFormat = "%s(%s)"
        val leftToRightValueFormat = "%s(%s)(%s)"
        val leftValue = String.format(leftFormat, "${t.value}", invoke(t.left))
        val leftToRightValue = String.format(leftToRightValueFormat, "${t.value}", invoke(t.left), invoke(t.right))
        return if (t.right == null) {
            leftValue
        } else {
            leftToRightValue
        }
    }
}

class ConstructStringFromBinaryTreeStack : ConstructStringFromBinaryTreeStrategy {
    override operator fun invoke(t: TreeNode?): String {
        var tree: TreeNode? = t ?: return ""
        val stack: Stack<TreeNode> = Stack()
        stack.push(tree)
        val visited: MutableSet<TreeNode> = HashSet()
        val s = StringBuilder()
        while (stack.isNotEmpty()) {
            tree = stack.peek()
            if (visited.contains(tree)) {
                stack.pop()
                s.append(")")
            } else {
                visited.add(tree)
                s.append("(" + tree.value)
                if (tree.left == null && tree.right != null) {
                    s.append("()")
                }
                if (tree.right != null) {
                    stack.push(tree.right)
                }
                if (tree.left != null) {
                    stack.push(tree.left)
                }
            }
        }
        return s.substring(1, s.length - 1)
    }
}
