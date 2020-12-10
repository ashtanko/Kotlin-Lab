package dev.shtanko.algorithms.leetcode

import java.util.Arrays
import kotlin.math.max

interface TwoSumLessThanKStrategy {
    fun perform(nums: IntArray, k: Int): Int
}

class TwoSumLessThanKBruteForce : TwoSumLessThanKStrategy {
    override fun perform(nums: IntArray, k: Int): Int {
        var answer = -1
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                val sum = nums[i] + nums[j]
                if (sum < k) {
                    answer = max(answer, sum)
                }
            }
        }
        return answer
    }
}

class TwoSumLessThanKTwoPointers : TwoSumLessThanKStrategy {
    override fun perform(nums: IntArray, k: Int): Int {
        nums.sort()
        var answer = -1
        var left = 0
        var right: Int = nums.size - 1
        while (left < right) {
            val sum = nums[left] + nums[right]
            if (sum < k) {
                answer = max(answer, sum)
                left++
            } else {
                right--
            }
        }
        return answer
    }
}

class TwoSumLessThanKBinarySearch : TwoSumLessThanKStrategy {
    override fun perform(nums: IntArray, k: Int): Int {
        var answer = -1
        nums.sort()
        for (i in nums.indices) {
            val idx: Int = Arrays.binarySearch(nums, i + 1, nums.size, k - nums[i] - 1)
            var j = if (idx >= 0) idx else idx.inv()
            if (j == nums.size || nums[j] > k - nums[i] - 1) {
                j--
            }
            if (j > i) {
                answer = max(answer, nums[i] + nums[j])
            }
        }
        return answer
    }
}

class TwoSumLessThanKCountingSort : TwoSumLessThanKStrategy {

    override fun perform(nums: IntArray, k: Int): Int {
        var answer = -1
        val count = IntArray(ARRAY_SIZE)
        for (num in nums) {
            count[num]++
        }
        var lo = 1
        var hi = MAX
        while (lo <= hi) {
            if (lo + hi >= k || count[hi] == 0) {
                hi--
            } else {
                if (count[lo] > (if (lo < hi) 0 else 1)) {
                    answer = max(answer, lo + hi)
                }
                lo++
            }
        }
        return answer
    }

    companion object {
        private const val ARRAY_SIZE = 1001
        private const val MAX = 1000
    }
}
