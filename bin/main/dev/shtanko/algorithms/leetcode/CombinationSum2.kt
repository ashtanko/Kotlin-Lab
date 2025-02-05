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
import dev.shtanko.algorithms.annotations.Recursive
import java.util.LinkedList

/**
 * 40. Combination Sum II
 * @see <a href="https://leetcode.com/problems/combination-sum-ii/">Source</a>
 */
fun interface CombinationSum2 {
    operator fun invoke(candidates: IntArray, target: Int): List<List<Int>>
}

@Backtracking
class BacktrackingWithCounters : CombinationSum2 {
    override operator fun invoke(candidates: IntArray, target: Int): List<List<Int>> {
        // container to hold the final combinations
        val results: MutableList<List<Int>> = ArrayList()
        val comb = LinkedList<Int>()

        val counter: HashMap<Int, Int> = HashMap()
        for (candidate in candidates) {
            if (counter.containsKey(candidate)) {
                counter[candidate] = counter.getOrDefault(candidate, 0) + 1
            } else {
                counter[candidate] = 1
            }
        }

        // convert the counter table to a list of (num, count) tuples
        val counterList: MutableList<IntArray> = ArrayList()
        counter.forEach { (key, value) -> counterList.add(intArrayOf(key, value)) }

        backtrack(comb, target, 0, counterList, results)
        return results
    }

    private fun backtrack(
        comb: LinkedList<Int>,
        remain: Int,
        curr: Int,
        counter: MutableList<IntArray>,
        results: MutableList<List<Int>>,
    ) {
        if (remain <= 0) {
            if (remain == 0) {
                // make a deep copy of the current combination.
                results.add(ArrayList(comb))
            }
            return
        }
        for (nextCurr in curr until counter.size) {
            val entry = counter[nextCurr]
            val candidate = entry[0]
            val freq = entry[1]
            if (freq <= 0) continue

            // add a new element to the current combination
            comb.addLast(candidate)
            counter[nextCurr] = intArrayOf(candidate, freq - 1)

            // continue the exploration with the updated combination
            backtrack(comb, remain - candidate, nextCurr, counter, results)

            // backtrack the changes, so that we can try another candidate
            counter[nextCurr] = intArrayOf(candidate, freq)
            comb.removeLast()
        }
    }
}

@Backtracking
class BacktrackingWithIndex : CombinationSum2 {
    override operator fun invoke(candidates: IntArray, target: Int): List<List<Int>> {
        val results: MutableList<List<Int>> = ArrayList()
        val comb = LinkedList<Int>()
        candidates.sort()
        backtrack(candidates, comb, target, 0, results)
        return results
    }

    private fun backtrack(
        candidates: IntArray,
        comb: LinkedList<Int>,
        remain: Int,
        curr: Int,
        results: MutableList<List<Int>>,
    ) {
        if (remain == 0) {
            // copy the current combination to the final list.
            results.add(ArrayList(comb))
            return
        }
        for (nextCurr in curr until candidates.size) {
            if (nextCurr > curr && candidates[nextCurr] == candidates[nextCurr - 1]) continue
            val pick = candidates[nextCurr]
            // optimization: early stopping
            if (remain - pick < 0) break
            comb.addLast(pick)
            backtrack(candidates, comb, remain - pick, nextCurr + 1, results)
            comb.removeLast()
        }
    }
}

@Recursive
class CombinationSum2Compact : CombinationSum2 {
    override fun invoke(candidates: IntArray, target: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        val root = TreeNode(target)
        fun helper(
            candidates: IntArray,
            node: TreeNode,
        ): List<List<Int>> {
            if (node.target == 0) {
                res.add(node.path)
                return res
            }

            for (i in node.startIndex until candidates.size) {
                val currentNum = candidates[i]
                val remainingTarget = node.target - currentNum
                if (remainingTarget < 0) break
                val child = TreeNode(remainingTarget, i + 1, node.path + currentNum)
                helper(candidates, child)
            }
            return res
        }
        return helper(candidates.sorted().toIntArray(), root).toSet().toList()
    }

    data class TreeNode(val target: Int, val startIndex: Int = 0, val path: List<Int> = emptyList())
}
