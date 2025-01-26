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

@ExperimentalStdlibApi
private fun backtrack(
    remain: Int,
    comb: MutableList<Int>,
    start: Int,
    candidates: IntArray,
    results: MutableList<List<Int>>,
) {
    if (remain == 0) {
        results.add(ArrayList(comb))
        return
    } else if (remain < 0) {
        return
    }
    for (i in start until candidates.size) {
        comb.add(candidates[i])
        backtrack(remain - candidates[i], comb, i, candidates, results)
        comb.removeLast()
    }
}

@ExperimentalStdlibApi
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val results = ArrayList<List<Int>>()
    val comb = LinkedList<Int>()
    backtrack(target, comb, 0, candidates, results)
    return results
}
