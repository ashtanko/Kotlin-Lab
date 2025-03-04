/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2196. Create Binary Tree From Descriptions
 * @see <a href="https://leetcode.com/problems/create-binary-tree-from-descriptions">Source</a>
 */
@Medium("https://leetcode.com/problems/create-binary-tree-from-descriptions")
fun interface CreateBinaryTree {
    operator fun invoke(descriptions: Array<IntArray>): TreeNode?
}

class CreateBinaryTreeHashMap : CreateBinaryTree {
    override operator fun invoke(descriptions: Array<IntArray>): TreeNode? {
        val parentToNodeMap = HashMap<Int, TreeNode?>()
        val childNodes = HashSet<Int>()
        descriptions.forEach { (parent, child, isLeft) ->
            childNodes.add(child)
            val parentNode = parentToNodeMap.getOrDefault(parent, TreeNode(parent))
            if (isLeft == 1) {
                parentNode?.left = parentToNodeMap.getOrDefault(child, TreeNode(child))
                parentToNodeMap[child] = parentNode?.left
            } else {
                parentNode?.right = parentToNodeMap.getOrDefault(child, TreeNode(child))
                parentToNodeMap[child] = parentNode?.right
            }
            parentToNodeMap[parent] = parentNode
        }

        val rootId = descriptions.find { !childNodes.contains(it[0]) }?.get(0) ?: -1
        return parentToNodeMap[rootId]
    }
}
