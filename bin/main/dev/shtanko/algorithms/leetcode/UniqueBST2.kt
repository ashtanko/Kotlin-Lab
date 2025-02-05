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
 * 95. Unique Binary Search Trees II
 */
fun interface UniqueBST2 {
    operator fun invoke(n: Int): List<TreeNode?>
}

/**
 * Approach 1: Recursive Dynamic Programming
 */
class UniqueBST2RecursiveDP : UniqueBST2 {
    override fun invoke(n: Int): List<TreeNode?> {
        val memo: MutableMap<Pair<Int, Int>, List<TreeNode?>> = HashMap()
        return allPossibleBST(1, n, memo)
    }

    private fun allPossibleBST(
        start: Int,
        end: Int,
        memo: MutableMap<Pair<Int, Int>, List<TreeNode?>>,
    ): List<TreeNode?> {
        val res: MutableList<TreeNode?> = ArrayList()
        if (start > end) {
            res.add(null)
            return res
        }
        if (memo.containsKey(Pair(start, end))) {
            return memo[Pair(start, end)]!!
        }
        // Iterate through all values from start to end to construct left and right subtree recursively.
        for (i in start..end) {
            val leftSubTrees = allPossibleBST(start, i - 1, memo)
            val rightSubTrees = allPossibleBST(i + 1, end, memo)

            // Loop through all left and right subtrees and connect them to ith root.
            for (l in leftSubTrees) {
                for (r in rightSubTrees) {
                    val root = TreeNode(i).apply {
                        left = l
                        right = r
                    }
                    res.add(root)
                }
            }
        }
        memo[Pair(start, end)] = res
        return res
    }
}

/**
 * Approach 2: Iterative Dynamic Programming
 */
class UniqueBST2IterativeDP : UniqueBST2 {
    override fun invoke(n: Int): List<TreeNode?> {
        val dp: MutableList<List<MutableList<TreeNode?>>> = ArrayList(n + 1)
        for (i in 0..n) {
            val innerList: MutableList<MutableList<TreeNode?>> = ArrayList(n + 1)
            for (j in 0..n) {
                innerList.add(ArrayList())
            }
            dp.add(innerList)
        }

        for (i in 1..n) {
            dp[i][i].add(TreeNode(i))
        }

        for (numberOfNodes in 2..n) {
            for (start in 1..n - numberOfNodes + 1) {
                val end = start + numberOfNodes - 1
                for (i in start..end) {
                    val leftSubtrees: MutableList<TreeNode?> = if (i != start) dp[start][i - 1] else ArrayList()
                    if (leftSubtrees.isEmpty()) {
                        leftSubtrees.add(null)
                    }
                    val rightSubtrees: MutableList<TreeNode?> = if (i != end) dp[i + 1][end] else ArrayList()
                    if (rightSubtrees.isEmpty()) {
                        rightSubtrees.add(null)
                    }
                    for (l in leftSubtrees) {
                        for (r in rightSubtrees) {
                            val root = TreeNode(i).apply {
                                left = l
                                right = r
                            }
                            dp[start][end].add(root)
                        }
                    }
                }
            }
        }
        return dp[1][n]
    }
}

/**
 * Approach 3: Dynamic Programming with Space Optimization
 */
class UniqueBST2DPSpaceOpt : UniqueBST2 {
    override fun invoke(n: Int): List<TreeNode?> {
        val dp: MutableList<MutableList<TreeNode?>> = ArrayList(n + 1)
        for (i in 0..n) {
            dp.add(ArrayList())
        }
        dp[0].add(null)

        for (numberOfNodes in 1..n) {
            for (i in 1..numberOfNodes) {
                val j = numberOfNodes - i
                for (l in dp[i - 1]) {
                    for (r in dp[j]) {
                        val root = TreeNode(i).apply {
                            left = l
                            right = r.clone(i)
                        }
                        dp[numberOfNodes].add(root)
                    }
                }
            }
        }
        return dp[n]
    }
}
