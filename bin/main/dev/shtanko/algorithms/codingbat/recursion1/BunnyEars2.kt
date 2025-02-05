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
 * Recursion-1 > bunnyEars2
 * @see <a href="https://codingbat.com/prob/p107330">Source</a>
 */
fun interface BunnyEars2 {
    operator fun invoke(bunnies: Int): Int
}

class BunnyEars2Iterative : BunnyEars2 {
    override fun invoke(bunnies: Int): Int {
        var res = 0
        repeat(bunnies) {
            res += if (it % 2 == 0) {
                2
            } else {
                3
            }
        }
        return res
    }
}

class BunnyEars2Recursive : BunnyEars2 {
    override fun invoke(bunnies: Int): Int {
        if (bunnies == 0) {
            return 0
        } else if (bunnies == 1) {
            return 2
        }
        return if (bunnies % 2 == 1) {
            2 + invoke(bunnies - 1)
        } else {
            3 + invoke(bunnies - 1)
        }
    }
}

class BunnyEars2Memo : BunnyEars2 {

    private val memo: MutableMap<Int, Int> = mutableMapOf()

    override fun invoke(bunnies: Int): Int {
        if (bunnies == 0) {
            return 0
        } else if (bunnies == 1) {
            return 2
        }

        // Check if the result for the current input is already memoized
        if (memo.containsKey(bunnies)) {
            return memo.getOrDefault(bunnies, 0)
        }

        // Recursive case for odd-numbered bunnies
        val result = if (bunnies % 2 == 1) {
            2 + invoke(bunnies - 1)
        } else {
            // Recursive case for even-numbered bunnies
            3 + invoke(bunnies - 1)
        }

        // Memoize the result before returning
        memo[bunnies] = result
        return result
    }
}

class BunnyEars2BottomUp : BunnyEars2 {
    override fun invoke(bunnies: Int): Int {
        if (bunnies == 0) {
            return 0
        }
        return mem(bunnies)
    }

    private fun mem(bunnies: Int): Int {
        val cache = IntArray(bunnies + 1)
        for (i in 1..bunnies) {
            val value = if (i % 2 == 1) {
                2
            } else {
                3
            }
            cache[i] = value + cache[i - 1]
        }
        return cache[bunnies]
    }
}

class BunnyEars2TopDown : BunnyEars2 {

    private val cache = arrayOfNulls<Int>(SIZE)

    override fun invoke(bunnies: Int): Int {
        if (bunnies == 0) {
            return 0
        }
        cache[0] = 0
        return mem(bunnies)
    }

    private fun mem(bunnies: Int): Int {
        if (cache[bunnies] != null) {
            return cache[bunnies] ?: 0
        }
        val result = if (bunnies % 2 == 1) {
            2 + mem(bunnies - 1)
        } else {
            // Recursive case for even-numbered bunnies
            3 + mem(bunnies - 1)
        }
        cache[bunnies] = result
        return mem(bunnies)
    }

    private companion object {
        private const val SIZE = 1000
    }
}
