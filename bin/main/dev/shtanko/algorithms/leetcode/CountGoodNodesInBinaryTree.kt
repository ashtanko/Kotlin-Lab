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

import kotlin.math.max

class CountGoodNodesInBinaryTree {

    fun goodNodes(root: TreeNode?): Int {
        return goodNodes(root, MIN_VALUE)
    }

    private fun goodNodes(root: TreeNode?, ma: Int): Int {
        if (root == null) return 0
        var res = if (root.value >= ma) 1 else 0
        res += goodNodes(root.left, max(ma, root.value))
        res += goodNodes(root.right, max(ma, root.value))
        return res
    }

    companion object {
        private const val MIN_VALUE = -10000
    }
}
