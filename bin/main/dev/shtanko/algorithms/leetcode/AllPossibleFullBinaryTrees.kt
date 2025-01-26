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

import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.Recursive
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 894. All Possible Full Binary Trees
 * @see <a href="https://leetcode.com/problems/all-possible-full-binary-trees/">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/all-possible-full-binary-trees")
fun interface AllPossibleFullBinaryTrees {
    operator fun invoke(n: Int): List<TreeNode?>
}

@Iterative
class AllPossibleFullBinaryTreesIterative : AllPossibleFullBinaryTrees {
    override fun invoke(n: Int): List<TreeNode?> {
        val ret: MutableList<TreeNode> = mutableListOf()
        if (n % 2 == 0) {
            return ret
        }
        if (n == 1) {
            ret.add(TreeNode(0))
            return ret
        }

        // Cache all possible Full Binary Trees (FBT) for the N - 2 levels
        val cache = cacheFBT(n)

        // Clone cached values, link them, and add them to the list
        linkCachedTreesAndAddToList(n, ret, cache)

        return ret
    }

    private fun cacheFBT(n: Int): MutableList<MutableList<TreeNode>> {
        val cache: MutableList<MutableList<TreeNode>> = mutableListOf(mutableListOf(TreeNode(0)))
        for (root in 1 until n / 2) {
            val newRoot = mutableListOf<TreeNode>()
            for (leftSize in 0 until root) {
                linkTreesAndAddToRoot(leftSize, root, newRoot, cache)
            }
            cache.add(newRoot)
        }
        return cache
    }

    private fun linkTreesAndAddToRoot(
        leftSize: Int,
        root: Int,
        newRoot: MutableList<TreeNode>,
        cache: List<MutableList<TreeNode>>,
    ) {
        for (left in cache[leftSize]) {
            for (right in cache[root - leftSize - 1]) {
                val newTree = TreeNode(0)
                newTree.left = left
                newTree.right = right
                newRoot.add(newTree)
            }
        }
    }

    private fun linkCachedTreesAndAddToList(
        n: Int,
        ret: MutableList<TreeNode>,
        cache: List<MutableList<TreeNode>>,
    ) {
        for (root in 0 until n / 2) {
            for (left in cache[root]) {
                for (right in cache[n / 2 - root - 1]) {
                    val newTree = TreeNode(0)
                    newTree.left = left.clone()
                    newTree.right = right.clone()
                    ret.add(newTree)
                }
            }
        }
    }
}

@Recursive
class AllPossibleFullBinaryTreesRecursive : AllPossibleFullBinaryTrees {
    override fun invoke(n: Int): List<TreeNode?> {
        val ret: MutableList<TreeNode> = ArrayList()

        if (n == 1) {
            ret.add(TreeNode(0))
        } else if (n % 2 != 0) {
            generateTrees(n, ret)
        }

        return ret
    }

    private fun generateTrees(n: Int, ret: MutableList<TreeNode>) {
        var i = 2
        while (i <= n) {
            val leftBranch = invoke(i - 1)
            val rightBranch = invoke(n - i)

            processBranches(leftBranch, rightBranch, ret)

            i += 2
        }
    }

    private fun processBranches(
        leftBranch: List<TreeNode?>,
        rightBranch: List<TreeNode?>,
        ret: MutableList<TreeNode>,
    ) {
        val leftIter = leftBranch.iterator()
        while (leftIter.hasNext()) {
            val left = leftIter.next()
            val rightIter = rightBranch.iterator()
            while (rightIter.hasNext()) {
                val right = rightIter.next()
                val tree = TreeNode(0)

                tree.left = if (rightIter.hasNext()) left?.clone() else left

                tree.right = if (leftIter.hasNext()) right?.clone() else right
                ret.add(tree)
            }
        }
    }
}
