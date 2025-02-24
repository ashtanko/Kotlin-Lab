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

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.Stack

/**
 * 962. Maximum Width Ramp
 * @see <a href="https://leetcode.com/problems/maximum-width-ramp/">Source</a>
 */
@Medium("https://leetcode.com/problems/maximum-width-ramp")
fun interface MaximumWidthRamp {
    operator fun invoke(arr: IntArray): Int
}

class MaximumWidthRampSort : MaximumWidthRamp {
    override fun invoke(arr: IntArray): Int {
        val n: Int = arr.size
        val b = Array(n) { 0 }
        for (i in 0 until n) {
            b[i] = i
        }
        b.sortWith { i, j ->
            arr[i].compareTo(arr[j])
        }
        var ans = 0
        var m = n
        for (i in b) {
            ans = ans.coerceAtLeast(i - m)
            m = m.coerceAtMost(i)
        }

        return ans
    }
}

class MaximumWidthRampTwoPointers : MaximumWidthRamp {
    override fun invoke(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        val n = arr.size
        val rightMax = IntArray(n)

        // Fill rightMax array with the maximum values from the right
        rightMax[n - 1] = arr[n - 1]
        for (i in n - 2 downTo 0) {
            rightMax[i] = maxOf(rightMax[i + 1], arr[i])
        }

        var left = 0
        var right = 0
        var maxWidth = 0

        // Traverse the array using left and right pointers
        while (right < n) {
            // Move left pointer forward if current left exceeds rightMax
            while (left < right && arr[left] > rightMax[right]) {
                left++
            }
            maxWidth = maxOf(maxWidth, right - left)
            right++
        }

        return maxWidth
    }
}

class MaximumWidthRampMonotonicStack : MaximumWidthRamp {
    override fun invoke(arr: IntArray): Int {
        val n = arr.size
        val indicesStack = Stack<Int>()

        // Fill the stack with indices in increasing order of their values
        for (i in 0 until n) {
            if (indicesStack.isEmpty || arr[indicesStack.peek()] > arr[i]) {
                indicesStack.push(i)
            }
        }

        var maxWidth = 0

        // Traverse the array from the end to the start
        for (j in n - 1 downTo 0) {
            while (indicesStack.isNotEmpty() && arr[indicesStack.peek()] <= arr[j]) {
                maxWidth = maxOf(maxWidth, j - indicesStack.peek())
                // Pop the index since it's already processed
                indicesStack.pop()
            }
        }

        return maxWidth
    }
}

class MaximumWidthRampBinarySearch : MaximumWidthRamp {
    override fun invoke(arr: IntArray): Int {
        val n: Int = arr.size
        if (n == 0) return 0
        var ans = 0
        val candidates: MutableList<Pair<Int, Int>> = ArrayList()
        candidates.add(Pair(arr[n - 1], n - 1))

        // candidates: i's decreasing, by increasing value of A[i]
        for (i in n - 2 downTo 0) {
            // Find largest j in candidates with A[j] >= A[i]
            var lo = 0
            var hi = candidates.size
            while (lo < hi) {
                val local = hi - lo
                val mi = lo + local / 2
                if (candidates[mi].first < arr[i]) lo = mi + 1 else hi = mi
            }
            if (lo < candidates.size) {
                val j: Int = candidates[lo].second
                ans = ans.coerceAtLeast(j - i)
            } else {
                candidates.add(Pair(arr[i], i))
            }
        }
        return ans
    }
}
