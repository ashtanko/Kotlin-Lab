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

import dev.shtanko.algorithms.annotations.Backtracking
import dev.shtanko.algorithms.annotations.BruteForce
import dev.shtanko.algorithms.annotations.level.Medium
import dev.shtanko.extensions.swap

/**
 * 526. Beautiful Arrangement
 * @see <a href="https://leetcode.com/problems/beautiful-arrangement/">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/beautiful-arrangement")
fun interface BeautifulArrangement {
    operator fun invoke(num: Int): Int
}

/**
 * Approach #2 Better Brute Force
 */
@BruteForce
class BABruteForce : BeautifulArrangement {
    private var count = 0

    override fun invoke(num: Int): Int {
        val nums = IntArray(num)
        for (i in 1..num) nums[i - 1] = i
        return permute(nums, 0)
    }

    fun permute(nums: IntArray, l: Int): Int {
        if (l == nums.size) {
            count++
        }
        for (i in l until nums.size) {
            nums.swap(i, l)
            if (nums[l] % (l + 1) == 0 || (l + 1) % nums[l] == 0) {
                permute(nums, l + 1)
            }
            nums.swap(i, l)
        }
        return count
    }
}

/**
 * Approach #3 Backtracking
 */
@Backtracking
class BABacktracking : BeautifulArrangement {
    private var count = 0

    override fun invoke(num: Int): Int {
        val visited = BooleanArray(num + 1)
        calculate(num, 1, visited)
        return count
    }

    fun calculate(n: Int, pos: Int, visited: BooleanArray) {
        if (pos > n) count++
        for (i in 1..n) {
            if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                visited[i] = true
                calculate(n, pos + 1, visited)
                visited[i] = false
            }
        }
    }
}
