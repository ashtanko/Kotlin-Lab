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

import dev.shtanko.algorithms.annotations.Sort

fun interface PairSum {
    operator fun invoke(arr: IntArray): Int
}

@Sort
class PairSumSort1 : PairSum {
    override operator fun invoke(arr: IntArray): Int {
        arr.sort()
        val list = arr.toList()
        val chunks = list.chunked(2)
        var res = 0
        for (chunk in chunks) {
            res += kotlin.math.min(chunk.first(), chunk.last())
        }
        return res
    }
}

@Sort
class PairSumSort2 : PairSum {
    override operator fun invoke(arr: IntArray): Int {
        arr.sort()
        var res = 0
        var i = 0
        while (i < arr.size) {
            res += arr[i]
            i += 2
        }
        return res
    }
}

@Sort
class PairSumSort3 : PairSum {
    override operator fun invoke(arr: IntArray): Int {
        arr.sort()
        return arr.toList().chunked(2) { it.minOrNull() ?: 0 }.sum()
    }
}

class PairSumOdd : PairSum {

    override operator fun invoke(arr: IntArray): Int {
        val exist = IntArray(MAX_ARR_SIZE)
        for (i in arr.indices) {
            exist[arr[i] + ARR_HELPER]++
        }
        var sum = 0
        var isOdd = true
        for (i in exist.indices) {
            while (exist[i] > 0) {
                if (isOdd) {
                    sum += i - ARR_HELPER
                }
                isOdd = !isOdd
                exist[i]--
            }
        }
        return sum
    }

    companion object {
        private const val MAX_ARR_SIZE = 20001
        private const val ARR_HELPER = 10000
    }
}
