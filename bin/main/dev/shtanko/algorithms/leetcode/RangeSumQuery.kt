/*
 * Copyright 2021 Oleksii Shtanko
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
 *  Range Sum Query - Immutable
 *  @see <a href="https://leetcode.com/problems/range-sum-query-immutable/">Source</a>
 */
fun interface RangeSumQuery {
    operator fun invoke(i: Int, j: Int): Int
}

class RangeSumQueryBruteForce(val nums: IntArray) : RangeSumQuery {
    override operator fun invoke(i: Int, j: Int): Int {
        var sum = 0
        for (k in i..j) {
            sum += nums[k]
        }
        return sum
    }
}

class RangeSumQueryCaching(val nums: IntArray) : RangeSumQuery {

    private val map: MutableMap<Pair<Int, Int>, Int> = HashMap()

    init {
        for (i in nums.indices) {
            var sum = 0
            for (j in i until nums.size) {
                sum += nums[j]
                map[i to j] = sum
            }
        }
    }

    override operator fun invoke(i: Int, j: Int): Int {
        return map[i to j] ?: 0
    }
}

class RangeSumQueryCachingOptimized(val nums: IntArray) : RangeSumQuery {

    private val sum: IntArray = IntArray(nums.size + 1)

    init {
        for (i in nums.indices) {
            sum[i + 1] = sum[i] + nums[i]
        }
    }

    override operator fun invoke(i: Int, j: Int): Int {
        return sum[j + 1] - sum[i]
    }
}
