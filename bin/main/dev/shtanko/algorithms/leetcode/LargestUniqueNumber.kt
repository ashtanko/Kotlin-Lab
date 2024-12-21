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

fun interface LargestUniqueNumber {
    operator fun invoke(arr: IntArray): Int
}

/**
 * O(n) time.
 * O(1) space.
 */
class LargestUniqueNumberBruteForce : LargestUniqueNumber {

    override operator fun invoke(arr: IntArray): Int {
        var res = -1
        val temp = IntArray(ARR_SIZE)
        for (i in arr.indices) {
            temp[arr[i]]++
        }
        for (i in temp.size - 1 downTo 0) {
            if (temp[i] == 1) {
                res = i
                break
            }
        }
        return res
    }

    companion object {
        private const val ARR_SIZE = 1001
    }
}

/**
 * Time complexity: O(n).
 * Space complexity: O(n).
 */
class LargestUniqueNumberHashMap : LargestUniqueNumber {
    override operator fun invoke(arr: IntArray): Int {
        val seen: MutableMap<Int, Int> = HashMap()
        for (i in arr.indices) {
            seen[arr[i]] = seen.getOrDefault(arr[i], 0) + 1
        }
        var result = -1
        for ((key, value) in seen) {
            if (value == 1 && key > result) {
                result = key
            }
        }
        return result
    }
}
