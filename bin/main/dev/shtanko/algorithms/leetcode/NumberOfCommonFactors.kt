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

import dev.shtanko.algorithms.math.gcd

/**
 * 2427. Number of Common Factors
 * @see <a href="https://leetcode.com/problems/number-of-common-factors/">Source</a>
 */
fun interface NumberOfCommonFactors {
    fun commonFactors(a: Int, b: Int): Int
}

class NumberOfCommonFactorsNaive : NumberOfCommonFactors {
    override fun commonFactors(a: Int, b: Int): Int {
        val listA = mutableListOf<Int>()
        val listB = mutableListOf<Int>()
        val result = mutableListOf<Int>()
        for (i in 1..a) {
            if (a % i == 0) {
                listA.add(i)
            }
        }

        for (i in 1..b) {
            if (b % i == 0) {
                listB.add(i)
            }
        }

        if (listA.size > listB.size) {
            listA.forEach {
                if (listB.contains(it) && listA.contains(it)) {
                    result.add(it)
                }
            }
        } else {
            listB.forEach {
                if (listA.contains(it) && listB.contains(it)) {
                    result.add(it)
                }
            }
        }

        return result.size
    }
}

class NumberOfCommonFactorsBruteForce : NumberOfCommonFactors {
    override fun commonFactors(a: Int, b: Int): Int {
        var ans = 1
        for (i in 2..(a to b).gcd()) {
            if (a % i == 0 && b % i == 0) {
                ans++
            }
        }
        return ans
    }
}
