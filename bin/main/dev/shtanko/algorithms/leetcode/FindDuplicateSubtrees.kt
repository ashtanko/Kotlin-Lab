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

package dev.shtanko.algorithms.leetcode

/**
 * 652. Find Duplicate Subtrees
 * @see <a href="https://leetcode.com/problems/find-duplicate-subtrees/">Source</a>
 */
fun interface FindDuplicateSubtrees {
    operator fun invoke(root: TreeNode): List<TreeNode>
}

class FindDuplicateSubtreesMap : FindDuplicateSubtrees {
    override operator fun invoke(root: TreeNode): List<TreeNode> {
        val map: MutableMap<String, MutableList<TreeNode>> = HashMap()
        val dups: MutableList<TreeNode> = ArrayList()
        serialize(root, map)
        for (group in map.values) {
            if (group.size > 1) {
                dups.add(group[0])
            }
        }
        return dups
    }

    private fun serialize(node: TreeNode?, map: MutableMap<String, MutableList<TreeNode>>): String {
        if (node == null) return ""
        val s = "(" + serialize(node.left, map) + node.value + serialize(node.right, map) + ")"
        if (!map.containsKey(s)) map[s] = ArrayList()
        map[s]?.add(node)
        return s
    }
}
