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

private const val MAX_ARR_SIZE = 500

fun interface FindLuckyStrategy {
    operator fun invoke(arr: IntArray): Int
}

class FindLuckyStraightForward : FindLuckyStrategy {
    override operator fun invoke(arr: IntArray): Int {
        val cnt = IntArray(MAX_ARR_SIZE + 1)
        for (a in arr) {
            ++cnt[a]
        }
        for (i in MAX_ARR_SIZE downTo 1) {
            if (cnt[i] == i) {
                return i
            }
        }
        return -1
    }
}

class FindLuckyMap : FindLuckyStrategy {
    override operator fun invoke(arr: IntArray): Int {
        val freq: MutableMap<Int, Int> = HashMap()
        for (a in arr) {
            freq[a] = 1 + freq.getOrDefault(a, 0) // Accumulate the occurrence of a.
        }
        var ans = -1
        for ((key, value) in freq) {
            if (key == value) {
                ans = ans.coerceAtLeast(key)
            }
        }
        return ans
    }
}
