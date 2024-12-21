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

/**
 * 231. Power of Two
 * @see <a href="https://leetcode.com/problems/power-of-two">Source</a>
 */
fun interface PowerOfTwo {
    operator fun invoke(num: Int): Boolean
}

class PowerOfTwoIterative : PowerOfTwo {
    override fun invoke(num: Int): Boolean {
        var num1 = num
        if (num1 <= 0) return false
        while (num1 % 2 == 0) num1 /= 2
        return num1 == 1
    }
}

class PowerOfTwoRecursive : PowerOfTwo {
    override fun invoke(num: Int): Boolean {
        if (num <= 0) return false
        if (num == 1) return true
        if (num % 2 != 0) return false
        return invoke(num / 2)
    }
}

class PowerOfTwoTailrec : PowerOfTwo {
    override fun invoke(num: Int): Boolean {
        tailrec fun isPowerOfTwoHelper(n: Int): Boolean {
            if (n <= 0) return false
            if (n == 1) return true
            if (n % 2 != 0) return false
            return isPowerOfTwoHelper(n / 2)
        }
        return isPowerOfTwoHelper(num)
    }
}

class PowerOfTwoMemo : PowerOfTwo {
    override fun invoke(num: Int): Boolean {
        val memo = mutableMapOf<Int, Boolean>()

        fun isPowerOfTwoHelper(n: Int): Boolean {
            if (n <= 0) return false
            if (n == 1) return true
            if (n % 2 != 0) return false

            return memo.getOrPut(n) { isPowerOfTwoHelper(n / 2) }
        }

        return isPowerOfTwoHelper(num)
    }
}

class PowerOfTwoBitwise : PowerOfTwo {
    override fun invoke(num: Int): Boolean {
        return if (num < 1) {
            false
        } else {
            0 == num - 1 and num
        }
    }
}

class PowerOfTwoMathOneLine : PowerOfTwo {
    override fun invoke(num: Int) = num > 0 && (num and num - 1) == 0
}

class PowerOfTwoMath : PowerOfTwo {
    override fun invoke(num: Int) = num > 0 && Integer.bitCount(num) == 1
}
