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

import dev.shtanko.algorithms.annotations.level.Easy
import kotlin.math.abs

/**
 * 110. Balanced Binary Tree
 * @see <a href="https://leetcode.com/problems/balanced-binary-tree">Source</a>
 */
@Easy("https://leetcode.com/problems/balanced-binary-tree")
fun isBalanced(root: TreeNode?): Boolean {
    return balancedHelper(root) >= 0
}

fun balancedHelper(root: TreeNode?, height: Int = 0): Int {
    if (root == null) {
        return height
    }
    val leftTree = balancedHelper(root.left, height + 1)
    val rightTree = balancedHelper(root.right, height + 1)
    if (leftTree < 0 || rightTree < 0 || abs(leftTree - rightTree) > 1) {
        return -1
    }
    return leftTree.coerceAtLeast(rightTree)
}
