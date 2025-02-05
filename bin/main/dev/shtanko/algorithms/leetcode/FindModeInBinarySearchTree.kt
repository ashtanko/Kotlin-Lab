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

import java.util.LinkedList
import java.util.Queue
import java.util.Stack
import kotlin.math.max

/**
 * 501. Find Mode in Binary Search Tree
 * @see <a href="https://leetcode.com/problems/find-mode-in-binary-search-tree">Source</a>
 */
fun interface FindModeInBinarySearchTree {
    operator fun invoke(root: TreeNode?): IntArray
}

sealed interface FindModeInBinarySearchTreeStrategy {

    /**
     * Approach 1: Count Frequency With Hash Map (DFS)
     */
    data object DFS : FindModeInBinarySearchTree, FindModeInBinarySearchTreeStrategy {
        override fun invoke(root: TreeNode?): IntArray {
            val counter: MutableMap<Int, Int> = HashMap()
            var maxFreq = 0

            fun dfs(node: TreeNode?, counter: MutableMap<Int, Int>) {
                if (node == null) {
                    return
                }

                counter[node.value] = counter.getOrDefault(node.value, 0) + 1
                dfs(node.left, counter)
                dfs(node.right, counter)
            }

            dfs(root, counter)

            for (key in counter.keys) {
                maxFreq = max(maxFreq.toDouble(), counter[key]!!.toDouble()).toInt()
            }

            val ans: MutableList<Int?> = ArrayList()
            for (key in counter.keys) {
                if (counter[key] == maxFreq) {
                    ans.add(key)
                }
            }

            val result = IntArray(ans.size)
            for (i in ans.indices) {
                result[i] = ans[i]!!
            }

            return result
        }
    }

    /**
     * Approach 2: Iterative DFS
     */
    data object IterativeDFS : FindModeInBinarySearchTree, FindModeInBinarySearchTreeStrategy {
        override fun invoke(root: TreeNode?): IntArray {
            val counter: MutableMap<Int, Int> = HashMap()
            val stack: Stack<TreeNode> = Stack()
            stack.push(root)

            while (!stack.empty()) {
                val node: TreeNode = stack.pop()

                counter[node.value] = counter.getOrDefault(node.value, 0) + 1
                if (node.left != null) {
                    stack.push(node.left)
                }
                if (node.right != null) {
                    stack.push(node.right)
                }
            }

            var maxFreq = 0
            for (key in counter.keys) {
                maxFreq = max(maxFreq.toDouble(), counter[key]!!.toDouble()).toInt()
            }

            val ans: MutableList<Int?> = ArrayList()
            for (key in counter.keys) {
                if (counter[key] == maxFreq) {
                    ans.add(key)
                }
            }

            val result = IntArray(ans.size)
            for (i in ans.indices) {
                result[i] = ans[i]!!
            }

            return result
        }
    }

    /**
     * Approach 3: Breadth First Search (BFS)
     */
    data object BFS : FindModeInBinarySearchTree, FindModeInBinarySearchTreeStrategy {
        override fun invoke(root: TreeNode?): IntArray {
            val counter: MutableMap<Int?, Int?> = HashMap()
            val queue: Queue<TreeNode?> = LinkedList()
            queue.add(root)

            while (queue.isNotEmpty()) {
                val node: TreeNode? = queue.remove()

                counter[node?.value] = counter.getOrDefault(node?.value, 0)!! + 1
                if (node?.left != null) {
                    queue.add(node.left)
                }
                if (node?.right != null) {
                    queue.add(node.right)
                }
            }

            var maxFreq = 0
            for (key in counter.keys) {
                maxFreq = max(maxFreq.toDouble(), counter[key]!!.toDouble()).toInt()
            }

            val ans: MutableList<Int?> = ArrayList()
            for (key in counter.keys) {
                if (counter[key] == maxFreq) {
                    ans.add(key)
                }
            }

            val result = IntArray(ans.size)
            for (i in ans.indices) {
                result[i] = ans[i]!!
            }

            return result
        }
    }

    /**
     * Approach 4: No Hash-Map
     */
    data object DFSList : FindModeInBinarySearchTree, FindModeInBinarySearchTreeStrategy {
        override fun invoke(root: TreeNode?): IntArray {
            val values: MutableList<Int> = ArrayList()
            var maxStreak = 0
            var currStreak = 0
            var currNum = 0
            var ans: MutableList<Int?> = ArrayList()

            fun dfs(node: TreeNode?, values: MutableList<Int>) {
                if (node == null) {
                    return
                }

                // Inorder traversal visits nodes in sorted order
                dfs(node.left, values)
                values.add(node.value)
                dfs(node.right, values)
            }

            dfs(root, values)

            for (num in values) {
                if (num == currNum) {
                    currStreak++
                } else {
                    currStreak = 1
                    currNum = num
                }

                if (currStreak > maxStreak) {
                    ans = ArrayList()
                    maxStreak = currStreak
                }

                if (currStreak == maxStreak) {
                    ans.add(num)
                }
            }

            val result = IntArray(ans.size)
            for (i in ans.indices) {
                result[i] = ans[i]!!
            }

            return result
        }
    }

    /**
     * Approach 5: No "Values" Array
     */
    data object DFSArray : FindModeInBinarySearchTree, FindModeInBinarySearchTreeStrategy {

        override fun invoke(root: TreeNode?): IntArray {
            val ans = mutableListOf<Int>()
            var maxStreak = 0
            var currentStreak = 0
            var currentNum = 0

            fun dfs(node: TreeNode?) {
                if (node == null) {
                    return
                }

                dfs(node.left)

                val num = node.value
                if (num == currentNum) {
                    currentStreak++
                } else {
                    currentStreak = 1
                    currentNum = num
                }

                if (currentStreak > maxStreak) {
                    ans.clear()
                    maxStreak = currentStreak
                }

                if (currentStreak == maxStreak) {
                    ans.add(num)
                }

                dfs(node.right)
            }

            dfs(root)
            return ans.toIntArray()
        }
    }

    /**
     * Approach 6: True Constant Space: Morris Traversal
     */
    data object MorrisTraversal : FindModeInBinarySearchTree, FindModeInBinarySearchTreeStrategy {
        override fun invoke(root: TreeNode?): IntArray {
            var maxStreak = 0
            var currStreak = 0
            var currNum = 0
            val ans = mutableListOf<Int>()

            var curr = root
            while (curr != null) {
                if (curr.left != null) {
                    // Find the friend
                    var friend = curr.left
                    while (friend?.right != null) {
                        friend = friend.right
                    }

                    friend?.right = curr

                    // Delete the edge after using it
                    val left = curr.left
                    curr.left = null
                    curr = left
                } else {
                    // Handle the current node
                    val num = curr.value
                    if (num == currNum) {
                        currStreak++
                    } else {
                        currStreak = 1
                        currNum = num
                    }

                    if (currStreak > maxStreak) {
                        ans.clear()
                        maxStreak = currStreak
                    }

                    if (currStreak == maxStreak) {
                        ans.add(num)
                    }

                    curr = curr.right
                }
            }

            return ans.toIntArray()
        }
    }
}
