/*
 * Copyright 2022 Oleksii Shtanko
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

import dev.shtanko.algorithms.MOD
import dev.shtanko.algorithms.annotations.DFS

/**
 * 1569. Number of Ways to Reorder Array to Get Same BST
 * @see <a href="https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/">Source</a>
 */
fun interface NumOfWays {
    operator fun invoke(nums: IntArray): Int
}

class NumOfWaysTriangle : NumOfWays {
    override operator fun invoke(nums: IntArray): Int {
        val len: Int = nums.size
        val arr: MutableList<Int> = ArrayList()
        for (n in nums) {
            arr.add(n)
        }
        return getCombs(arr, getTriangle(len + 1)).toInt() - 1
    }

    private fun getCombs(nums: List<Int>, combs: Array<LongArray>): Long {
        if (nums.size <= 2) {
            return 1
        }
        val root = nums[0]
        val left: MutableList<Int> = ArrayList()
        val right: MutableList<Int> = ArrayList()
        for (n in nums) {
            if (n < root) {
                left.add(n)
            } else if (n > root) {
                right.add(n)
            }
        }
        // mod every number to avoid overflow
        return combs[left.size + right.size][left.size] * (getCombs(left, combs) % MOD) % MOD * getCombs(
            right,
            combs,
        ) % MOD
    }

    private fun getTriangle(n: Int): Array<LongArray> {
        // Yang Hui (Pascle) triangle
        // 4C2 = triangle[4][2] = 6
        val triangle = Array(n) { LongArray(n) }
        for (i in 0 until n) {
            triangle[i][i] = 1
            triangle[i][0] = triangle[i][i]
        }
        for (i in 2 until n) {
            for (j in 1 until i) {
                triangle[i][j] = (triangle[i - 1][j] + triangle[i - 1][j - 1]) % MOD
            }
        }
        return triangle
    }
}

@DFS
class NumOfWaysImpl : NumOfWays {
    private lateinit var arr: Array<LongArray>

    override operator fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        arr = Array(n + 1) { longArrayOf() }

        for (i in 0..n) {
            arr[i] = LongArray(i + 1) { 1 }
            for (j in 1 until i) {
                arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j]) % MOD
            }
        }
        var root: Node? = null
        for (x in nums) {
            root = insert(root, x)
        }
        cnt(root)
        return dfs(root).toInt() - 1
    }

    private fun dfs(root: Node?): Long {
        if (root == null) {
            return 1
        }
        val leftLen = if (root.left == null) 0 else root.left!!.cnt
        val rightLen = if (root.right == null) 0 else root.right!!.cnt
        if (leftLen + rightLen <= 1) {
            return 1
        }
        var res = dfs(root.left) * dfs(root.right) % MOD
        res = res * arr[leftLen + rightLen][leftLen] % MOD
        return res
    }

    private fun insert(root: Node?, value: Int): Node {
        if (root == null) {
            return Node(value)
        }
        if (root.value < value) {
            root.left = insert(root.left, value)
        } else {
            root.right = insert(root.right, value)
        }
        return root
    }

    private fun cnt(root: Node?): Int {
        if (root == null) {
            return 0
        }
        root.cnt += cnt(root.left) + cnt(root.right)
        return root.cnt
    }

    data class Node(
        var value: Int,
        var left: Node? = null,
        var right: Node? = null,
        var cnt: Int = 1,
    )
}
