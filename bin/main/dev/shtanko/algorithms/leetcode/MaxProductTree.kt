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
import kotlin.math.abs

/**
 * 1339. Maximum Product of Splitted Binary Tree
 * @see <a href="https://leetcode.com/problems/maximum-product-of-splitted-binary-tree">Source</a>
 */
fun interface MaxProductTree {
    operator fun invoke(root: TreeNode?): Int
}

class MaxProductTreeInorder : MaxProductTree {
    private var res = 0

    override operator fun invoke(root: TreeNode?): Int {
        val sum = allSum(root)
        inorder(root, sum)
        val num1 = res
        val num2 = sum - res
        var ans = 0
        // Calculate the product
        for (i in 0 until num1) {
            ans += num2
            if (ans > MOD) {
                ans -= MOD
            }
        }
        return ans
    }

    private fun inorder(root: TreeNode?, sum: Int): Int {
        var cur = 0
        if (root == null) {
            return 0
        }
        cur += inorder(root.left, sum)
        cur += root.value
        cur += inorder(root.right, sum)
        val minClose = abs(res - sum / 2)
        val curClose = abs(cur - sum / 2)
        res = if (curClose < minClose) cur else res
        return cur
    }

    private fun allSum(root: TreeNode?): Int {
        return if (root == null) {
            0
        } else {
            root.value + allSum(root.left) + allSum(root.right)
        }
    }
}
