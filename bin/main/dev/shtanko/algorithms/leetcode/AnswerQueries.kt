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

import dev.shtanko.algorithms.annotations.BinarySearch
import dev.shtanko.algorithms.annotations.level.Easy
import java.util.Arrays
import kotlin.math.abs

/**
 * 2389. Longest Subsequence With Limited Sum
 * @see <a href="https://leetcode.com/problems/longest-subsequence-with-limited-sum/">Source</a>
 */
@Easy(link = "https://leetcode.com/problems/longest-subsequence-with-limited-sum")
fun interface AnswerQueries {
    operator fun invoke(nums: IntArray, queries: IntArray): IntArray
}

@BinarySearch
class AnswerQueriesBinarySearch : AnswerQueries {
    override operator fun invoke(nums: IntArray, queries: IntArray): IntArray {
        nums.sort()
        val n: Int = nums.size
        val m: Int = queries.size
        val res = IntArray(m)
        for (i in 1 until n) {
            nums[i] += nums[i - 1]
        }
        for (i in 0 until m) {
            val j: Int = Arrays.binarySearch(nums, queries[i])
            res[i] = abs(j + 1)
        }
        return res
    }
}
