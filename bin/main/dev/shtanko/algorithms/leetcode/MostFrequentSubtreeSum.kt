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

import kotlin.math.max

class MostFrequentSubtreeSum {
    private val count: MutableMap<Int, Int> = HashMap()
    private var maxCount = 0

    operator fun invoke(root: TreeNode?): IntArray {
        dfs(root)
        val res: MutableList<Int> = ArrayList()
        for (s in count.keys) {
            if (count[s] == maxCount) res.add(s)
        }
        return res.stream().mapToInt { i: Int -> i }.toArray()
    }

    private fun dfs(root: TreeNode?): Int {
        if (root == null) return 0
        val s: Int = dfs(root.left) + dfs(root.right) + root.value
        count[s] = count.getOrDefault(s, 0) + 1
        maxCount = max(maxCount, count[s]!!)
        return s
    }
}
