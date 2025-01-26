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
 * 459. Repeated Substring Pattern
 * @see <a href="https://leetcode.com/problems/repeated-substring-pattern/">Source</a>
 */
fun interface RepeatedSubstringPattern {
    operator fun invoke(s: String): Boolean
}

/**
 * Approach 1: Using Divisors
 */
class RepeatedSubstringPatternDivisors : RepeatedSubstringPattern {
    override operator fun invoke(s: String): Boolean {
        val n: Int = s.length
        for (i in 1..n / 2) {
            if (n % i == 0) {
                val pattern = StringBuilder()
                for (j in 0 until n / i) {
                    pattern.append(s.substring(0, i))
                }
                if (s == pattern.toString()) {
                    return true
                }
            }
        }
        return false
    }
}

/**
 * Approach 2: String Concatenation
 */
class RepeatedSubstringPatternConcat : RepeatedSubstringPattern {
    override operator fun invoke(s: String): Boolean {
        val combined = s.plus(s)
        return combined.substring(1, combined.length - 1).contains(s)
    }
}
