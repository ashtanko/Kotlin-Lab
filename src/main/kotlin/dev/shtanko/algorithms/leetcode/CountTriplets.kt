/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1442. Count Triplets That Can Form Two Arrays of Equal XOR
 * @see <a href="https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/">Source</a>
 */
@Medium("https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor")
fun interface CountTriplets {
    operator fun invoke(arr: IntArray): Int
}

class CountTripletsBruteForce : CountTriplets {
    override fun invoke(arr: IntArray): Int {
        var count = 0
        for (i in arr.indices) {
            var xor = arr[i]
            for (k in i + 1 until arr.size) {
                xor = xor xor arr[k]
                if (xor == 0) {
                    count += k - i
                }
            }
        }
        return count
    }
}

class NestedPrefixXOR : CountTriplets {
    override fun invoke(arr: IntArray): Int {
        val n = arr.size
        val prefixXor = IntArray(n + 1)
        for (i in 0 until n) {
            prefixXor[i + 1] = prefixXor[i] xor arr[i]
        }
        var count = 0
        for (i in 0 until n) {
            for (k in i + 1 until n) {
                if (prefixXor[i] == prefixXor[k + 1]) {
                    count += k - i
                }
            }
        }
        return count
    }
}

class TwoPassPrefixXOR : CountTriplets {
    override fun invoke(arr: IntArray): Int {
        val prefixXOR = IntArray(arr.size + 1)
        prefixXOR[0] = 0
        System.arraycopy(arr, 0, prefixXOR, 1, arr.size)
        val size = prefixXOR.size
        var count = 0

        // Performing XOR operation on the array elements
        for (i in 1 until size) {
            prefixXOR[i] = prefixXOR[i] xor prefixXOR[i - 1]
        }

        // Maps to store counts and totals of XOR values encountered
        val countMap = mutableMapOf<Int, Int>()
        val totalMap = mutableMapOf<Int, Int>()

        // Iterating through the array
        for (i in 0 until size) {
            // Calculating contribution of current element to the result
            count += (countMap.getOrDefault(prefixXOR[i], 0) * (i - 1) - totalMap.getOrDefault(prefixXOR[i], 0))
            // Updating total count of current XOR value
            totalMap[prefixXOR[i]] = totalMap.getOrDefault(prefixXOR[i], 0) + i
            countMap[prefixXOR[i]] = countMap.getOrDefault(prefixXOR[i], 0) + 1
        }

        return count
    }
}

class OnePassPrefixXOR : CountTriplets {
    override fun invoke(arr: IntArray): Int {
        val size = arr.size
        var count = 0
        var prefix = 0

        // Maps to store counts and totals of XOR values encountered
        val countMap = mutableMapOf<Int, Int>()
        countMap[0] = 1
        val totalMap = mutableMapOf<Int, Int>()

        // Iterating through the array
        for (i in 0 until size) {
            // Calculating XOR prefix
            prefix = prefix xor arr[i]

            // Calculating contribution of current element to the result
            count += countMap.getOrDefault(prefix, 0) * i - totalMap.getOrDefault(prefix, 0)

            // Updating total count of current XOR value
            totalMap[prefix] = totalMap.getOrDefault(prefix, 0) + i + 1
            countMap[prefix] = countMap.getOrDefault(prefix, 0) + 1
        }

        return count
    }
}
