/*
 * Copyright 2021 Oleksii Shtanko
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

import java.util.LinkedList
import kotlin.math.sqrt

/**
 * 254. Factor Combinations
 */
fun interface FactorCombinations {
    operator fun invoke(num: Int): List<List<Int>>
}

class FactorCombinationsImpl : FactorCombinations {
    override fun invoke(num: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        helper(result, ArrayList(), num, 2)
        return result
    }

    private fun helper(result: MutableList<List<Int>>, item: MutableList<Int>, n: Int, start: Int) {
        if (n <= 1) {
            if (item.size > 1) {
                result.add(ArrayList(item))
            }
            return
        }
        for (i in start..n) {
            if (n % i == 0) {
                item.add(i)
                helper(result, item, n / i, i)
                item.removeAt(item.size - 1)
            }
        }
    }
}

class FactorCombinationsImpl2 : FactorCombinations {
    override fun invoke(num: Int): List<List<Int>> {
        val ret: MutableList<List<Int>> = LinkedList<List<Int>>()
        if (num <= 3) return ret
        val path: MutableList<Int> = LinkedList<Int>()
        getFactors(2, num, path, ret)
        return ret
    }

    private fun getFactors(start: Int, n: Int, path: MutableList<Int>, ret: MutableList<List<Int>>) {
        var i = start
        while (i <= sqrt(n.toDouble())) {
            // The previous factor is no bigger than the next
            if (n % i == 0 && n / i >= i) {
                path.add(i)
                path.add(n / i)
                ret.add(LinkedList(path))
                path.removeAt(path.size - 1)
                getFactors(i, n / i, path, ret)
                path.removeAt(path.size - 1)
            }
            i++
        }
    }
}
