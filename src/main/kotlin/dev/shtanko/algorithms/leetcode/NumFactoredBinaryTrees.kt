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

import dev.shtanko.algorithms.MOD
import dev.shtanko.algorithms.annotations.DFS

/**
 * 823. Binary Trees With Factors
 * @see <a href="https://leetcode.com/problems/binary-trees-with-factors">Source</a>
 */
fun interface NumFactoredBinaryTrees {
    operator fun invoke(arr: IntArray): Int
}

@DFS
class NumFactoredBinaryTreesDFS : NumFactoredBinaryTrees {
    override fun invoke(arr: IntArray): Int {
        val set = arr.toSet()
        arr.sort()
        val dp = mutableMapOf<Int, Long>()
        fun dfs(a: Int): Long = dp.getOrPut(a) {
            1L + arr.sumOf {
                if (a % it == 0 && set.contains(a / it)) {
                    dfs(it) * dfs(a / it)
                } else {
                    0L
                }
            }
        }
        return (arr.sumOf { dfs(it) } % MOD).toInt()
    }
}
