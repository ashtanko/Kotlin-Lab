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

import dev.shtanko.algorithms.annotations.Backtracking
import dev.shtanko.algorithms.annotations.Bitwise
import dev.shtanko.algorithms.annotations.level.Medium
import kotlin.math.pow

/**
 * 2597. The Number of Beautiful Subsets
 * @see <a href="https://leetcode.com/problems/the-number-of-beautiful-subsets/">Source</a>
 */
@Medium("https://leetcode.com/problems/the-number-of-beautiful-subsets")
fun interface BeautifulSubsets {
    operator fun invoke(nums: IntArray, k: Int): Int
}

@Bitwise
class BeautifulSubsetsBitset : BeautifulSubsets {
    override fun invoke(nums: IntArray, k: Int): Int {
        return countBeautifulSubsets(nums, k, 0, 0)
    }

    private fun countBeautifulSubsets(nums: IntArray, difference: Int, index: Int, mask: Int): Int {
        if (index == nums.size) {
            return if (mask > 0) {
                1
            } else {
                0
            }
        }

        var isBeautiful = true

        for (j in 0 until index) {
            isBeautiful = ((1 shl j) and mask) == 0 ||
                kotlin.math.abs(nums[j] - nums[index]) != difference
            if (!isBeautiful) break
        }

        val skip = countBeautifulSubsets(nums, difference, index + 1, mask)
        val take = if (isBeautiful) {
            countBeautifulSubsets(nums, difference, index + 1, mask + (1 shl index))
        } else {
            0
        }

        return skip + take
    }
}

@Backtracking
class BeautifulSubsetsBacktracking : BeautifulSubsets {
    override fun invoke(nums: IntArray, k: Int): Int {
        val freqMap = mutableMapOf<Int, Int>()
        nums.sort()
        return countBeautifulSubsets(nums, k, freqMap, 0) - 1
    }

    private fun countBeautifulSubsets(nums: IntArray, difference: Int, freqMap: MutableMap<Int, Int>, i: Int): Int {
        if (i == nums.size) {
            return 1
        }
        var totalCount = countBeautifulSubsets(nums, difference, freqMap, i + 1)

        if (!freqMap.containsKey(nums[i] - difference)) {
            freqMap[nums[i]] = freqMap.getOrDefault(nums[i], 0) + 1
            totalCount += countBeautifulSubsets(nums, difference, freqMap, i + 1)
            freqMap[nums[i]] = freqMap.getOrDefault(nums[i], 0) - 1

            if (freqMap[nums[i]] == 0) {
                freqMap.remove(nums[i])
            }
        }

        return totalCount
    }
}

class BeautifulSubsetsRecOpt : BeautifulSubsets {
    override fun invoke(nums: IntArray, k: Int): Int {
        var totalCount = 1
        val freqMap = mutableMapOf<Int, MutableMap<Int, Int>>()

        for (num in nums) {
            val fr = if (k != 0) freqMap.getOrDefault(num % k, mutableMapOf()) else mutableMapOf()
            fr[num] = fr.getOrDefault(num, 0) + 1
            if (k != 0) freqMap[num % k] = fr
        }

        for (entry in freqMap) {
            val subsets = ArrayList<Pair<Int, Int>>(entry.value.size)
            for (subsetEntry in entry.value) {
                subsets.add(Pair(subsetEntry.key, subsetEntry.value))
            }
            totalCount *= countBeautifulSubsets(subsets, subsets.size, k, 0)
        }

        return totalCount - 1
    }

    private fun countBeautifulSubsets(
        subsets: ArrayList<Pair<Int, Int>>,
        numSubsets: Int,
        difference: Int,
        i: Int,
    ): Int {
        if (i == numSubsets) {
            return 1
        }

        val skip = countBeautifulSubsets(subsets, numSubsets, difference, i + 1)
        var take = (2.0.pow(subsets[i].second.toDouble()) - 1).toInt()

        take *= if (i + 1 < numSubsets && subsets[i + 1].first - subsets[i].first == difference) {
            countBeautifulSubsets(subsets, numSubsets, difference, i + 2)
        } else {
            countBeautifulSubsets(subsets, numSubsets, difference, i + 1)
        }

        return skip + take
    }
}

class BeautifulSubsetsDPMemo : BeautifulSubsets {
    override fun invoke(nums: IntArray, k: Int): Int {
        var totalCount = 1
        val freqMap = sortedMapOf<Int, MutableMap<Int, Int>>()

        // Calculate frequencies based on remainder
        for (num in nums) {
            val remainder = num % k
            val fr = freqMap.getOrDefault(remainder, sortedMapOf())
            fr[num] = fr.getOrDefault(num, 0) + 1
            freqMap[remainder] = fr
        }

        // Calculate subsets for each remainder group
        for (entry in freqMap.entries) {
            val subsets = entry.value.entries.map { Pair(it.key, it.value) }
            val counts = IntArray(subsets.size) { -1 } // Store counts of subsets for memoization
            totalCount *= countBeautifulSubsets(subsets, subsets.size, k, 0, counts)
        }
        return totalCount - 1
    }

    private fun countBeautifulSubsets(
        subsets: List<Pair<Int, Int>>,
        numSubsets: Int,
        difference: Int,
        i: Int,
        counts: IntArray,
    ): Int {
        // Base case: Return 1 for a subset of size 1
        if (i == numSubsets) {
            return 1
        }

        // If the count is already calculated, return it
        if (counts[i] != -1) {
            return counts[i]
        }

        // Calculate subsets where the current subset is not taken
        val skip = countBeautifulSubsets(subsets, numSubsets, difference, i + 1, counts)

        // Calculate subsets where the current subset is taken
        var take = (1 shl subsets[i].second) - 1 // take the current subset

        // If the next number has a difference of 'difference',
        // calculate subsets accordingly
        take *= if (i + 1 < numSubsets && subsets[i + 1].first - subsets[i].first == difference) {
            countBeautifulSubsets(subsets, numSubsets, difference, i + 2, counts)
        } else {
            countBeautifulSubsets(subsets, numSubsets, difference, i + 1, counts)
        }

        counts[i] = skip + take // Store and return total count of subsets
        return counts[i]
    }
}

class BeautifulSubsetsDPIterative : BeautifulSubsets {
    override fun invoke(nums: IntArray, k: Int): Int {
        var totalCount = 1

        val freqMap = sortedMapOf<Int, MutableMap<Int, Int>>()

        // Calculate frequencies based on remainder
        for (num in nums) {
            val remainder = num % k
            freqMap.computeIfAbsent(remainder) { sortedMapOf() }
                .merge(num, 1, Integer::sum)
        }

        // Iterate through each remainder group
        for ((_, value) in freqMap) {
            val n = value.size // Number of elements in the current group

            val subsets = ArrayList(value.entries)

            val counts = IntArray(n + 1) // Array to store counts of subsets

            counts[n] = 1 // Initialize count for the last subset

            // Calculate counts for each subset starting from the second last
            for (i in n - 1 downTo 0) {
                // Count of subsets skipping the current subset
                val skip = counts[i + 1]

                // Count of subsets including the current subset
                var take = (1 shl subsets[i].value) - 1

                // If next number has a 'difference',
                // calculate subsets; otherwise, move to next
                if (i + 1 < n && subsets[i + 1].key - subsets[i].key == k) {
                    take *= counts[i + 2]
                } else {
                    take *= counts[i + 1]
                }

                // Store the total count for the current subset
                counts[i] = skip + take
            }

            totalCount *= counts[0]
        }

        return totalCount - 1
    }
}

class BeautifulSubsetsDPIterativeOpt : BeautifulSubsets {
    override fun invoke(nums: IntArray, k: Int): Int {
        var totalCount = 1
        val freqMap = sortedMapOf<Int, MutableMap<Int, Int>>()

        // Calculate frequencies based on remainder
        for (num in nums) {
            val remainder = num % k
            val fr = freqMap.getOrDefault(remainder, sortedMapOf())
            fr[num] = fr.getOrDefault(num, 0) + 1
            freqMap[remainder] = fr
        }

        // Iterate through each remainder group
        for ((_, value) in freqMap) {
            var prevNum = -k
            var prev2 = 0
            var prev1 = 1
            var curr = 1

            // Iterate through each number in the current remainder group
            for ((num, freq) in value) {
                // Count of subsets skipping the current number
                val skip = prev1

                // Count of subsets including the current number
                val take = if (num - prevNum == k) {
                    ((1 shl freq) - 1) * prev2
                } else {
                    ((1 shl freq) - 1) * prev1
                }

                curr = skip + take // Store the total count for the current number
                prev2 = prev1
                prev1 = curr
                prevNum = num
            }
            totalCount *= curr
        }
        return totalCount - 1
    }
}
