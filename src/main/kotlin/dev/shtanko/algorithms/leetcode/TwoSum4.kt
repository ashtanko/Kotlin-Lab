/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import java.util.LinkedList
import java.util.Queue

/**
 * 653. Two Sum IV - Input is a BST
 * @see <a href="https://leetcode.com/problems/two-sum-iv-input-is-a-bst/">Source</a>
 */
fun interface TwoSum4 {
    operator fun invoke(root: TreeNode, k: Int): Boolean
}

/**
 * Approach #1 Using HashSet
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
class TwoSum4HashSet : TwoSum4 {
    override fun invoke(root: TreeNode, k: Int): Boolean {
        val set: MutableSet<Int?> = HashSet()
        return find(root, k, set)
    }

    private fun find(root: TreeNode?, k: Int, set: MutableSet<Int?>): Boolean {
        if (root == null) return false
        if (set.contains(k - root.value)) return true
        set.add(root.value)
        return find(root.left, k, set) || find(root.right, k, set)
    }
}

/**
 * Approach #2 Using BFS and HashSet
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
@BFS
class TwoSum4BFS : TwoSum4 {
    override fun invoke(root: TreeNode, k: Int): Boolean {
        val set: MutableSet<Int?> = HashSet()
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            if (queue.peek() != null) {
                val node: TreeNode = queue.remove()
                if (set.contains(k - node.value)) return true
                set.add(node.value)
                queue.add(node.right)
                queue.add(node.left)
            } else {
                queue.remove()
            }
        }
        return false
    }
}

/**
 * Approach #3 Using BST
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
@BFS
class TwoSum4BST : TwoSum4 {
    override fun invoke(root: TreeNode, k: Int): Boolean {
        val list: MutableList<Int> = ArrayList()
        inorder(root, list)
        var l = 0
        var r = list.size - 1
        while (l < r) {
            val sum = list[l] + list[r]
            if (sum == k) return true
            if (sum < k) l++ else r--
        }
        return false
    }

    private fun inorder(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) return
        inorder(root.left, list)
        list.add(root.value)
        inorder(root.right, list)
    }
}

@DFS
class TwoSum4DFS : TwoSum4 {
    override fun invoke(root: TreeNode, k: Int): Boolean {
        return dfs(root, root, k)
    }

    fun dfs(root: TreeNode?, cur: TreeNode?, k: Int): Boolean {
        return if (cur == null) {
            false
        } else {
            search(root, cur, k - cur.value) || dfs(root, cur.left, k) || dfs(
                root,
                cur.right,
                k,
            )
        }
    }

    fun search(root: TreeNode?, cur: TreeNode, value: Int): Boolean {
        return if (root == null) {
            false
        } else {
            root.value == value && root != cur || root.value < value && search(
                root.right,
                cur,
                value,
            ) || root.value > value && search(root.left, cur, value)
        }
    }
}
