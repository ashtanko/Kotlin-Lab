package dev.shtanko.algorithms.leetcode

// Find the Smallest Divisor Given a Threshold
interface SmallestDivisorStrategy {
    fun perform(nums: IntArray, threshold: Int): Int

    fun computeSum(nums: IntArray, x: Int): Long {
        var s: Long = 0
        for (n in nums) {
            s += (n / x + if (n % x == 0) 0 else 1).toLong()
        }
        return s
    }
}

class SmallestDivisorBinarySearch : SmallestDivisorStrategy {
    override fun perform(nums: IntArray, threshold: Int): Int {
        // search boundaries for the divisor
        var left = 1
        var right = 2
        while (computeSum(nums, right) > threshold) {
            left = right
            right = right shl 1
        }

        // binary search
        while (left <= right) {
            val pivot = left + (right - left shr 1)
            val num: Long = computeSum(nums, pivot)
            if (num > threshold) {
                left = pivot + 1
            } else {
                right = pivot - 1
            }
        }

        // at the end of loop, left > right,
        // computeSum(right) > threshold
        // computeSum(left) <= threshold
        // --> return left
        return left
    }
}

class SmallestDivisorMath : SmallestDivisorStrategy {
    override fun perform(nums: IntArray, threshold: Int): Int {
        // binary search
        // binary search
        var left = 1
        var right = nums[nums.size - 1]
        while (left <= right) {
            val pivot = left + (right - left shr 1)
            val num = computeSum(nums, pivot).toInt()
            if (num > threshold) {
                left = pivot + 1
            } else {
                right = pivot - 1
            }
        }

        // at the end of loop, left > right,
        // computeSum(right) > threshold
        // computeSum(left) <= threshold
        // --> return left

        // at the end of loop, left > right,
        // computeSum(right) > threshold
        // computeSum(left) <= threshold
        // --> return left
        return left
    }
}