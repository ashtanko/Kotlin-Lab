/*
 * Copyright 2024 Oleksii Shtanko
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

import java.util.Arrays
import kotlin.math.max

/**
 * 525. Contiguous Array
 * @see <a href="https://leetcode.com/problems/contiguous-array">Source</a>
 */
fun interface ContiguousArray {
    operator fun invoke(nums: IntArray): Int
}

class ContiguousArrayExtraArray : ContiguousArray {
    override fun invoke(nums: IntArray): Int {
        val arr = IntArray(2 * nums.size + 1)
        Arrays.fill(arr, -2)
        arr[nums.size] = -1
        var maxlen = 0
        var count = 0
        for (i in nums.indices) {
            count += (if (nums[i] == 0) -1 else 1)
            if (arr[count + nums.size] >= -1) {
                maxlen = max(maxlen.toDouble(), (i - arr[count + nums.size]).toDouble()).toInt()
            } else {
                arr[count + nums.size] = i
            }
        }
        return maxlen
    }
}

class ContiguousArrayHashMap : ContiguousArray {
    override fun invoke(nums: IntArray): Int {
        val map: MutableMap<Int, Int> = HashMap()
        map[0] = -1
        var maxlen = 0
        var count = 0
        for (i in nums.indices) {
            count += (if (nums[i] == 1) 1 else -1)
            if (map.containsKey(count)) {
                maxlen = max(maxlen.toDouble(), (i - map.getOrDefault(count, 0)).toDouble()).toInt()
            } else {
                map[count] = i
            }
        }
        return maxlen
    }
}
