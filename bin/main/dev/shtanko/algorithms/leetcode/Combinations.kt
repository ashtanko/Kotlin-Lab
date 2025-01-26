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

import dev.shtanko.algorithms.annotations.Backtracking

/**
 * 77. Combinations
 * @see <a href="https://leetcode.com/problems/combinations/">Source</a>
 */
fun interface Combinations {
    operator fun invoke(n: Int, k: Int): List<List<Int>>
}

@Backtracking
class CombinationsBacktracking : Combinations {
    override fun invoke(n: Int, k: Int): List<List<Int>> {
        val combs: MutableList<List<Int>> = ArrayList()
        combine(combs, ArrayList(), 1, n, k)
        return combs
    }

    private fun combine(combs: MutableList<List<Int>>, comb: MutableList<Int>, start: Int, n: Int, k: Int) {
        if (k == 0) {
            combs.add(ArrayList(comb))
            return
        }
        for (i in start..n) {
            comb.add(i)
            combine(combs, comb, i + 1, n, k - 1)
            comb.removeAt(comb.size - 1)
        }
    }
}
