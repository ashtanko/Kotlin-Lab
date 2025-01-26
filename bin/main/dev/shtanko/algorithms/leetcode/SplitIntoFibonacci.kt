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

import dev.shtanko.algorithms.DECIMAL
import kotlin.math.min

/**
 * 842. Split Array into Fibonacci Sequence
 * @see <a href="https://leetcode.com/problems/split-array-into-fibonacci-sequence/">Source</a>
 */
fun interface SplitIntoFibonacci {
    operator fun invoke(num: String): List<Int>
}

/**
 * Approach #1: Brute Force
 */
class SplitIntoFibonacciBruteForce : SplitIntoFibonacci {
    override operator fun invoke(num: String): List<Int> {
        val n: Int = num.length
        for (i in 0 until min(10, n)) {
            if (num[0] == '0' && i > 0) break
            val a: Long = java.lang.Long.valueOf(num.substring(0, i + 1))
            if (a >= Int.MAX_VALUE) break
            search@ for (j in i + 1 until min(i + DECIMAL, n)) {
                if (num[i + 1] == '0' && j > i + 1) break
                val b: Long = java.lang.Long.valueOf(num.substring(i + 1, j + 1))
                if (b >= Int.MAX_VALUE) break
                val fib: MutableList<Int> = ArrayList<Int>().apply {
                    add(a.toInt())
                    add(b.toInt())
                }
                var k = j + 1
                while (k < n) {
                    val nxt = (fib[fib.size - 2] + fib[fib.size - 1]).toLong()
                    val nxtS = nxt.toString()
                    if (nxt <= Int.MAX_VALUE && num.substring(k).startsWith(nxtS)) {
                        k += nxtS.length
                        fib.add(nxt.toInt())
                    } else {
                        continue@search
                    }
                }
                if (fib.size >= 3) {
                    return fib
                }
            }
        }

        return ArrayList()
    }
}

class SplitIntoFibonacciBacktracking : SplitIntoFibonacci {
    override operator fun invoke(num: String): List<Int> {
        val ans: MutableList<Int> = ArrayList()
        helper(num, ans, 0)
        return ans
    }

    fun helper(s: String, ans: MutableList<Int>, idx: Int): Boolean {
        if (idx == s.length && ans.size >= 3) {
            return true
        }
        for (i in idx until s.length) {
            if (s[idx] == '0' && i > idx) {
                break
            }
            val num = s.substring(idx, i + 1).toLong()
            if (num > Int.MAX_VALUE) {
                break
            }
            val size = ans.size
            // early termination
            if (size >= 2 && num > ans[size - 1] + ans[size - 2]) {
                break
            }
            if (size <= 1 || num == (ans[size - 1] + ans[size - 2]).toLong()) {
                ans.add(num.toInt())
                // branch pruning. if one branch has found fib seq, return true to upper call
                if (helper(s, ans, i + 1)) {
                    return true
                }
                ans.removeAt(ans.size - 1)
            }
        }
        return false
    }
}
