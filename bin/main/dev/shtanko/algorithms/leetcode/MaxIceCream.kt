/*
 * Copyright 2023 Oleksii Shtanko
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

import kotlin.math.max
import kotlin.math.min

/**
 * 1833. Maximum Ice Cream Bars
 * @see <a href="https://leetcode.com/problems/maximum-ice-cream-bars/">Source</a>
 */
fun interface MaxIceCream {
    operator fun invoke(costs: IntArray, coins: Int): Int
}

/**
 * Approach 1 (Greedy)
 */
class MaxIceCreamGreedy : MaxIceCream {
    override operator fun invoke(costs: IntArray, coins: Int): Int {
        var c = coins
        // Store ice cream costs in increasing order.
        costs.sort()
        val n: Int = costs.size
        var answer = 0
        // Pick ice creams till we can.
        while (answer < n && costs[answer] <= c) {
            // We can buy this icecream, reduce the cost from the coins.
            c -= costs[answer]
            answer += 1
        }
        return answer
    }
}

/**
 * Approach 2 (Bucket Sort)
 */
class MaxIceCreamBucketSort : MaxIceCream {
    override operator fun invoke(costs: IntArray, coins: Int): Int {
        var c = coins
        // get the maximum cost available
        var max = costs[0]
        for (element in costs) {
            max = max(element, max)
        }
        // create the bucket array of size of maximum_cost + 1
        // and keep the frequencies of the cost
        val buckets = IntArray(max + 1)
        for (p in costs) {
            buckets[p]++
        }

        // keep the track of maximum ice-creams can be bought
        var ans = 0
        for (i in buckets.indices) {
            if (c < i) {
                break
            }
            if (buckets[i] > 0) {
                ans += min(buckets[i], c / i)
                c -= min(c, i * buckets[i])
            }
        }
        return ans
    }
}

/**
 * Approach 3 (DP)
 */
class MaxIceCreamDP : MaxIceCream {
    override operator fun invoke(costs: IntArray, coins: Int): Int {
        var cns = coins
        var maxc = 0

        for (i in costs.indices) {
            if (costs[i] > maxc) {
                maxc = costs[i]
            }
        }

        val cc = IntArray(maxc + 1)

        for (element in costs) {
            cc[element]++
        }

        var c = 0

        for (i in 1..maxc) {
            if (cc[i] == 0) {
                continue
            }
            if (cns < i) {
                break
            }
            for (j in 0 until cc[i]) {
                if (cns >= i) {
                    cns -= i
                    c++
                }
            }
        }
        return c
    }
}
