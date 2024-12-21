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

package dev.shtanko.algorithms.codingbat.recursion1

/**
 * Recursion-1 > powerN
 * @see <a href="https://codingbat.com/prob/p158888">Source</a>
 */
internal fun interface PowerN {
    operator fun invoke(base: Int, num: Int): Int
}

class PowerNIterative : PowerN {
    override fun invoke(base: Int, num: Int): Int {
        var res = 1
        var exponent = num
        while (exponent > 0) {
            res *= base
            exponent--
        }
        return res
    }
}

class PowerNRecursive : PowerN {
    override fun invoke(base: Int, num: Int): Int {
        if (num == 0) {
            return 1
        }
        return base * invoke(base, num - 1)
    }
}

class PowerNMemo : PowerN {
    private val memo: MutableMap<Int, Int> = mutableMapOf()

    override fun invoke(base: Int, num: Int): Int {
        if (num == 0) {
            return 1
        }

        if (memo.containsKey(num)) {
            return memo.getOrDefault(num, 0)
        }
        val result = base * invoke(base, num - 1)
        memo[num] = result
        return result
    }
}
