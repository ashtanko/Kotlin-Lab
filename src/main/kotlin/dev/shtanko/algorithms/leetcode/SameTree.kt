/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

/**
 * 100. Same Tree
 * @see <a href="https://leetcode.com/problems/same-tree">Source</a>
 */
fun interface SameTree {
    operator fun invoke(tree1: TreeNode?, tree2: TreeNode?): Boolean
}

class SameTreeRecursive : SameTree {
    override fun invoke(tree1: TreeNode?, tree2: TreeNode?): Boolean {
        if (tree1 == null && tree2 == null) {
            return true
        }
        if (tree1 == null || tree2 == null) {
            return false
        }
        if (tree1.value == tree2.value) {
            return invoke(tree1.left, tree2.left) && invoke(tree1.right, tree2.right)
        }
        return false
    }
}
