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

import java.util.stream.IntStream

/**
 * 2032. Two Out of Three
 * @see <a href="https://leetcode.com/problems/two-out-of-three">Source</a>
 */
fun interface TwoOutOfThree {
    operator fun invoke(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int>

    companion object {
        const val ARR_SIZE = 101
    }
}

class TwoOutOfThreeImpl : TwoOutOfThree {
    override operator fun invoke(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int> {
        val list: MutableList<Int> = ArrayList()
        val count = Array(3) { IntArray(TwoOutOfThree.ARR_SIZE) }
        for (n in nums1) count[0][n] = 1
        for (n in nums2) count[1][n] = 1
        for (n in nums3) count[2][n] = 1

        for (i in 1 until TwoOutOfThree.ARR_SIZE) {
            if (count[0][i] + count[1][i] + count[2][i] > 1) list.add(i)
        }
        return list
    }
}

class TwoOutOfThreeStream : TwoOutOfThree {
    override operator fun invoke(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int> {
        val inputArrays = arrayOf(nums1, nums2, nums3)
        val countArrays = Array(3) { IntArray(TwoOutOfThree.ARR_SIZE) }
        for (index in inputArrays.indices) for (number in inputArrays[index]) countArrays[index][number] = 1
        return IntStream.range(1, TwoOutOfThree.ARR_SIZE)
            .filter { number -> countArrays[0][number] + countArrays[1][number] + countArrays[2][number] >= 2 }.boxed()
            .toList()
    }
}

class TwoOutOfThreeKotlinWay : TwoOutOfThree {
    override fun invoke(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int> {
        val inputArrays = listOf(nums1, nums2, nums3)
        val countArrays = Array(3) { IntArray(TwoOutOfThree.ARR_SIZE) }
        for ((index, array) in inputArrays.withIndex()) {
            for (number in array) {
                countArrays[index][number] = 1
            }
        }
        return (1 until TwoOutOfThree.ARR_SIZE)
            .filter { number -> countArrays.sumOf { it[number] } >= 2 }
    }
}
