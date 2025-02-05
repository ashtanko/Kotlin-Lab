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

import dev.shtanko.algorithms.DECIMAL

/**
 * Recursion-1 > count7
 * @see <a href="https://codingbat.com/prob/p101409">Source</a>
 */
internal fun interface CountSeven {
    operator fun invoke(num: Int): Int
}

class CountSevenIterative : CountSeven {
    override fun invoke(num: Int): Int {
        var res = 0
        var currentNum = num
        while (currentNum != 0) {
            if (currentNum % DECIMAL == 7) {
                res++
            }
            currentNum /= DECIMAL
        }
        return res
    }
}

class CountSevenRecursive : CountSeven {
    override fun invoke(num: Int): Int {
        if (num == 0) {
            return 0
        }
        return if (num % DECIMAL == 7) {
            1 + invoke(num / DECIMAL)
        } else {
            invoke(num / DECIMAL)
        }
    }
}

class CountSevenMemo : CountSeven {

    private val memo: MutableMap<Int, Int> = mutableMapOf()

    override fun invoke(num: Int): Int {
        if (num == 0) {
            return 0
        }

        if (memo.containsKey(num)) {
            return memo.getOrDefault(num, 0)
        }
        val result = if (num % DECIMAL == 7) {
            1 + invoke(num / DECIMAL)
        } else {
            invoke(num / DECIMAL)
        }
        memo[num] = result
        return result
    }
}
