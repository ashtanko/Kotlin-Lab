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

/**
 * 17. Letter Combinations of a Phone Number
 * @see <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number">Source</a>
 */
fun interface LetterCombinations {
    operator fun invoke(digits: String): List<String>
}

class LetterCombinationsRecursion : LetterCombinations {
    companion object {
        private const val DIGIT_OFFSET = 50
    }

    override operator fun invoke(digits: String): List<String> {
        val arr = arrayOf("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
        return digits.mapNotNull { arr[it.code - DIGIT_OFFSET].toList() }.getCartesianProduct()
            .map { String(it.toCharArray()) }
    }

    private fun <T> Collection<Iterable<T>>.getCartesianProduct(): List<List<T>> =
        if (isEmpty()) {
            emptyList()
        } else {
            drop(1)
                .fold(first().map(::listOf)) { acc, iterable ->
                    acc.flatMap { list -> iterable.map(list::plus) }
                }
        }
}
