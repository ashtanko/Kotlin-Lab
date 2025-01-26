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

import dev.shtanko.algorithms.annotations.TwoPointers
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1497. Check If Array Pairs Are Divisible by k
 * @see <a href="https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k")
fun interface CanArrange {
    operator fun invoke(arr: IntArray, k: Int): Boolean
}

class CanArrangeHashing : CanArrange {
    override fun invoke(arr: IntArray, k: Int): Boolean {
        val remainderCount = mutableMapOf<Int, Int>()

        // Store the count of remainders in a map.
        for (i in arr) {
            val rem = ((i % k) + k) % k
            remainderCount[rem] = remainderCount.getOrDefault(rem, 0) + 1
        }

        for (i in arr) {
            val rem = ((i % k) + k) % k

            // If the remainder for an element is 0, then the count
            // of numbers that give this remainder must be even.
            if (rem == 0) {
                if (remainderCount.getOrDefault(rem, 0) % 2 == 1) return false
            }
            // If the remainder rem and k-rem do not have the
            // same count then pairs can not be made.
            else if (remainderCount[rem] != remainderCount[k - rem]) return false
        }
        return true
    }
}

@TwoPointers
class CanArrangeTwoPointers : CanArrange {
    override fun invoke(arr: IntArray, k: Int): Boolean {
        val array = arr.toTypedArray()
        array.sortWith(CustomComparator(k))

        var start = 0
        var end = array.size - 1

        while (start < end) {
            if (array[start] % k != 0) {
                break
            }
            if (array[start + 1] % k != 0) {
                return false
            }
            start += 2
        }

        while (start < end) {
            if ((array[start] + array[end]) % k != 0) {
                return false
            }
            start++
            end--
        }
        return true
    }

    private class CustomComparator(private val k: Int) : Comparator<Int> {
        override fun compare(i: Int, j: Int): Int {
            return ((k + (i % k)) % k) - ((k + (j % k)) % k)
        }
    }
}
