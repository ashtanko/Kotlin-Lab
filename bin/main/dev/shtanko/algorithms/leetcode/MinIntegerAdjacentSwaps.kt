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

import java.util.Deque
import java.util.LinkedList
import java.util.Queue

/**
 * 1505. Minimum Possible Integer After at Most K Adjacent Swaps On Digits
 * @see <a href="https://leetcode.com/problems/minimum-possible-integer-after-at-most-k-adjacent-swaps-on-digits/">
 *     Source</a>
 */
fun interface MinIntegerAdjacentSwaps {
    fun minInteger(num: String, k: Int): String
}

class MinIntegerAdjacentSwapsImpl : MinIntegerAdjacentSwaps {
    override fun minInteger(num: String, k: Int): String {
        // pqs stores the location of each digit.
        var k0 = k
        val pqs: MutableList<Queue<Int>> = ArrayList()
        for (i in 0..9) {
            pqs.add(LinkedList())
        }

        for (i in num.indices) {
            pqs[num[i] - '0'].add(i)
        }
        var ans = ""
        val seg = SegmentTree(num.length)

        for (i in num.indices) {
            // At each location, try to place 0....9
            for (digit in 0..9) {
                // is there any occurrence of digit left?
                if (pqs[digit].isNotEmpty()) {
                    // yes, there is a occurrence of digit at pos
                    val pos: Int = pqs[digit].peek()
                    // Since few numbers already shifted to left, this `pos` might be outdated.
                    // we try to find how many number already got shifted that were to the left of pos.
                    val shift = seg.getCountLessThan(pos)
                    // (pos - shift) is number of steps to make digit move from pos to i.
                    if (pos - shift <= k0) {
                        k0 -= pos - shift
                        seg.add(pos) // Add pos to our segment tree.
                        pqs[digit].remove()
                        ans += digit
                        break
                    }
                }
            }
        }
        return ans
    }

    class SegmentTree(max: Int) {
        var nodes: IntArray
        var n: Int

        init {
            nodes = IntArray(4 * max)
            n = max
        }

        fun add(num: Int) {
            addUtil(num, 0, n, 0)
        }

        private fun addUtil(num: Int, l: Int, r: Int, node: Int) {
            if (num < l || num > r) {
                return
            }
            if (l == r) {
                nodes[node]++
                return
            }
            val mid = (l + r) / 2
            addUtil(num, l, mid, 2 * node + 1)
            addUtil(num, mid + 1, r, 2 * node + 2)
            nodes[node] = nodes[2 * node + 1] + nodes[2 * node + 2]
        }

        // Essentially it tells count of numbers < num.
        fun getCountLessThan(num: Int): Int {
            return getUtil(0, num, 0, n, 0)
        }

        private fun getUtil(ql: Int, qr: Int, l: Int, r: Int, node: Int): Int {
            if (qr < l || ql > r) return 0
            if (ql <= l && qr >= r) {
                return nodes[node]
            }
            val mid = (l + r) / 2
            return getUtil(ql, qr, l, mid, 2 * node + 1) + getUtil(ql, qr, mid + 1, r, 2 * node + 2)
        }
    }
}

class MinIntegerAdjacentSwapsBit : MinIntegerAdjacentSwaps {
    override fun minInteger(num: String, k: Int): String {
        val cs = num.toCharArray()
        val n = cs.size
        val sb = StringBuilder()
        val bit = IntegerBIT(n)
        var k0 = k
        for (i in 1..n) {
            bit.update(i, 1)
        }
        val dq: Array<Deque<Int>?> = arrayOfNulls(10)
        for (i in 0..9) {
            dq[i] = LinkedList()
        }
        for (i in 0 until n) {
            dq[cs[i].code - '0'.code]?.offerLast(i)
        }
        for (i in 0 until n) {
            for (j in 0..9) {
                val index = dq[j]?.peekFirst() ?: -1
                if (!dq[j].isNullOrEmpty() && bit.query(index) <= k0) {
                    // number of '1's before index is the number of swaps
                    k0 -= bit.query(index)
                    // index in string is represented in BIT at index + 1
                    bit.update(index + 1, -1)
                    dq[j]?.pollFirst()
                    sb.append(('0'.code + j).toChar())
                    break
                }
            }
        }
        return sb.toString()
    }

    class IntegerBIT(var n: Int) {
        var data: IntArray = IntArray(n + 1)

        /**
         * Queries sum of A[1]..A[x] inclusive. A is the underlying array this BIT represents.
         */
        fun query(x: Int): Int {
            var x0 = x
            var sum = 0
            while (x0 > 0) {
                sum += data[x0]
                x0 -= x0 and -x0
            }
            return sum
        }

        /**
         * Updates A[i] by d
         */
        fun update(i: Int, d: Int) {
            var i0 = i
            while (i0 <= n) {
                data[i0] += d
                i0 += i0 and -i0
            }
        }
    }
}
