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

fun interface AbstractNumIdenticalPairs {
    operator fun invoke(arr: IntArray): Int
}

class NumIdenticalPairsNaive : AbstractNumIdenticalPairs {
    override operator fun invoke(arr: IntArray): Int {
        var count = 0
        for (i in 0 until arr.size - 1) {
            var j = i + 1
            while (j < arr.size) {
                if (arr[i] == arr[j]) {
                    count++
                }
                j++
            }
        }
        return count
    }
}

class NumIdenticalPairsMap : AbstractNumIdenticalPairs {
    override operator fun invoke(arr: IntArray): Int {
        val map: MutableMap<Int, Int> = HashMap()
        var count = 0
        for (num in arr) {
            count += map.getOrDefault(num, 0)
            map[num] = map.getOrDefault(num, 0) + 1
        }
        return count
    }
}

class NumIdenticalPairsSort : AbstractNumIdenticalPairs {
    override operator fun invoke(arr: IntArray): Int {
        arr.sort()
        var count = 0
        var i = 0
        for (j in 1 until arr.size) {
            if (arr[j] == arr[i]) {
                count += j - i
            } else {
                i = j
            }
        }
        return count
    }
}
