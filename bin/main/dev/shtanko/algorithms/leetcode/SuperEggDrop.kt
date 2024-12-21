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

fun interface SuperEggDrop {
    operator fun invoke(eggs: Int, floors: Int): Int
}

class SuperEggDropDPBinarySearch : SuperEggDrop {

    private var memo: MutableMap<Int, Int?> = HashMap()

    override operator fun invoke(eggs: Int, floors: Int): Int {
        return calculate(eggs, floors)
    }

    private fun calculate(eggs: Int, floors: Int): Int {
        if (!memo.containsKey(floors * HUNDRED + eggs)) {
            val ans: Int
            when {
                floors == 0 -> ans = 0
                eggs == 1 -> ans = floors
                else -> {
                    var lo = 1
                    var hi = floors
                    while (lo + 1 < hi) {
                        val x = (lo + hi) / 2
                        val t1 = calculate(eggs - 1, x - 1)
                        val t2 = calculate(eggs, floors - x)
                        when {
                            t1 < t2 -> lo = x
                            t1 > t2 -> hi = x
                            else -> {
                                hi = x
                                lo = hi
                            }
                        }
                    }
                    ans = 1 + calculate(eggs - 1, lo - 1).coerceAtLeast(calculate(eggs, floors - lo))
                        .coerceAtMost(calculate(eggs - 1, hi - 1).coerceAtLeast(calculate(eggs, floors - hi)))
                }
            }
            memo[floors * HUNDRED + eggs] = ans
        }
        return memo[floors * HUNDRED + eggs]!!
    }

    companion object {
        private const val HUNDRED = 100
    }
}

class SuperEggDropDPOptimalityCriterion : SuperEggDrop {
    override operator fun invoke(eggs: Int, floors: Int): Int {
        // Right now, dp[i] represents dp(1, i)
        // Right now, dp[i] represents dp(1, i)
        var dp = IntArray(floors + 1)
        for (i in 0..floors) dp[i] = i

        for (i in 2..eggs) {
            // Now, we will develop dp2[i] = dp(k, i)
            val dp2 = IntArray(floors + 1)
            var x = 1
            for (j in 1..floors) {
                // Let's find dp2[n] = dp(k, n)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[n-x]) > max(dp[x], dp2[n-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (x < j && dp[x - 1].coerceAtLeast(dp2[j - x]) > dp[x].coerceAtLeast(dp2[j - x - 1])) {
                    x++
                }

                // The final answer happens at this x.
                dp2[j] = 1 + dp[x - 1].coerceAtLeast(dp2[j - x])
            }
            dp = dp2
        }

        return dp[floors]
    }
}

class SuperEggDropMathematical : SuperEggDrop {
    override operator fun invoke(eggs: Int, floors: Int): Int {
        var lo = 1
        var hi: Int = floors
        while (lo < hi) {
            val mi = (lo + hi) / 2
            if (calculate(mi, eggs, floors) < floors) lo = mi + 1 else hi = mi
        }

        return lo
    }

    private fun calculate(x: Int, eggs: Int, floors: Int): Int {
        var ans = 0
        var res = 1
        for (i in 1..eggs) {
            res *= x - i + 1
            res /= i
            ans += res
            if (ans >= floors) break
        }
        return ans
    }
}
