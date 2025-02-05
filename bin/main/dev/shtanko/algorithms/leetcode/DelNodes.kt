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

/**
 * Delete Nodes And Return Forest
 */
fun delNodes(root: TreeNode?, toDelete: IntArray): List<TreeNode?> {
    fun dfs(node: TreeNode?, set: Set<Int>, res: MutableList<TreeNode?>): TreeNode? {
        if (node == null) {
            return null
        }
        node.left = dfs(node.left, set, res)
        node.right = dfs(node.right, set, res)
        if (set.contains(node.value)) {
            if (node.left != null) res.add(node.left)
            if (node.right != null) res.add(node.right)
            return null
        }
        return node
    }

    val set: MutableSet<Int> = HashSet()
    for (i in toDelete) set.add(i)
    val res: MutableList<TreeNode?> = ArrayList()
    if (!set.contains(root?.value)) res.add(root)
    dfs(root, set, res)
    return res
}
