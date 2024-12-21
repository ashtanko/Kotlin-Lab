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

/**
 * 2709. Greatest Common Divisor Traversal
 * @see <a href="https://leetcode.com/problems/greatest-common-divisor-traversal">Source</a>
 */
fun interface GCDTraversal {
    operator fun invoke(nums: IntArray): Boolean
}

class GCDTraversalGraph : GCDTraversal {
    override fun invoke(nums: IntArray): Boolean {
        val size: Int = nums.size
        val has = BooleanArray(MAX + 1)
        for (x in nums) {
            has[x] = true
        }

        // edge cases
        if (size == 1) {
            return true
        }
        if (has[1]) {
            return false
        }

        // the general solution
        val sieve = IntArray(MAX + 1)
        for (d in 2..MAX) {
            if (sieve[d] == 0) {
                var v = d
                while (v <= MAX) {
                    sieve[v] = d
                    v += d
                }
            }
        }

        val union = DSU(2 * MAX + 1)
        for (x in nums) {
            var value = x
            while (value > 1) {
                val prime = sieve[value]
                val root = prime + MAX
                if (union.find(root) != union.find(x)) {
                    union.merge(root, x)
                }
                while (value % prime == 0) {
                    value /= prime
                }
            }
        }

        var cnt = 0
        for (i in 2..MAX) {
            if (has[i] && union.find(i) == i) {
                cnt++
            }
        }
        return cnt == 1
    }

    private class DSU(num: Int) {
        var res: IntArray = IntArray(num + 1)
        var size: IntArray = IntArray(num + 1)

        init {
            for (i in 0..num) {
                res[i] = i
                size[i] = 1
            }
        }

        fun find(x: Int): Int {
            return if (res[x] == x) x else (find(res[x]).also { res[x] = it })
        }

        fun merge(x: Int, y: Int) {
            var fx = find(x)
            var fy = find(y)
            if (fx == fy) {
                return
            }
            if (size[fx] > size[fy]) {
                val temp = fx
                fx = fy
                fy = temp
            }
            res[fx] = fy
            size[fy] += size[fx]
        }
    }

    companion object {
        private const val MAX = 100000
    }
}
