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

import dev.shtanko.algorithms.annotations.DFS
import java.util.LinkedList
import java.util.Queue

/**
 * 1361. Validate Binary Tree Nodes
 * @see <a href="https://leetcode.com/problems/validate-binary-tree-nodes">Source</a>
 */
fun interface ValidateBinaryTreeNodes {
    operator fun invoke(n: Int, leftChild: IntArray, rightChild: IntArray): Boolean
}

@DFS
class ValidateBinaryTreeNodesDFS : ValidateBinaryTreeNodes {
    override fun invoke(n: Int, leftChild: IntArray, rightChild: IntArray): Boolean {
        val inDegree = IntArray(n) { 0 }
        var root = -1
        for (i in leftChild.indices) {
            if (leftChild[i] != -1 && inDegree[leftChild[i]]++ == 1) {
                return false
            } else if (rightChild[i] != -1 && inDegree[rightChild[i]]++ == 1) {
                return false
            }
        }
        for (i in leftChild.indices) {
            if (inDegree[i] == 0) {
                if (root == -1) {
                    root = i
                } else {
                    return false
                }
            }
        }
        if (root == -1) {
            return false
        }
        return countNodes(leftChild, rightChild, root) == n
    }

    private fun countNodes(l: IntArray, r: IntArray, root: Int): Int {
        if (root == -1) {
            return 0
        }
        return 1 + countNodes(l, r, l[root]) + countNodes(l, r, r[root])
    }
}

class ValidateBinaryTreeNodesBFS : ValidateBinaryTreeNodes {
    override fun invoke(n: Int, leftChild: IntArray, rightChild: IntArray): Boolean {
        val indegree = IntArray(n)
        for (i in 0 until n) {
            if (leftChild[i] != -1 && indegree[leftChild[i]]++ == 1) return false
            if (rightChild[i] != -1 && indegree[rightChild[i]]++ == 1) return false
        }

        var root = -1
        for (i in 0 until n) if (indegree[i] == 0) root = i
        if (root == -1) return false

        val queue: Queue<Int> = LinkedList()
        queue.offer(root)
        var count = 0
        while (queue.isNotEmpty()) {
            val node: Int = queue.poll()
            ++count
            if (leftChild[node] != -1) queue.offer(leftChild[node])
            if (rightChild[node] != -1) queue.offer(rightChild[node])
        }

        return count == n
    }
}
