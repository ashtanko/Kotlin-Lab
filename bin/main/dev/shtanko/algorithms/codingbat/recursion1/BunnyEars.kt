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
 * Recursion-1 > bunnyEars
 * @see <a href="https://codingbat.com/prob/p183649">Source</a>
 */
fun interface BunnyEars {
    operator fun invoke(bunnies: Int): Int
}

class BunnyEarsIterative : BunnyEars {
    override fun invoke(bunnies: Int): Int {
        var res = 0
        repeat(bunnies) {
            res += 2
        }
        return res
    }
}

class BunnyEarsRecursive : BunnyEars {
    override fun invoke(bunnies: Int): Int {
        if (bunnies == 0) {
            return 0
        }
        return 2 + invoke(bunnies - 1)
    }
}

class BunnyEarsMemo : BunnyEars {
    override fun invoke(bunnies: Int): Int {
        if (bunnies == 0) {
            return 0
        }
        return mem(bunnies)
    }

    private fun mem(bunnies: Int): Int {
        val cache = IntArray(bunnies + 1)
        for (i in 1..bunnies) {
            cache[i] = 2 + cache[i - 1]
        }
        return cache[bunnies]
    }
}

class BunnyEarsTopDown : BunnyEars {

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
        cache[bunnies] = 2 + mem(bunnies - 1)
        return mem(bunnies)
    }

    private companion object {
        private const val SIZE = 1000
    }
}
