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

package dev.shtanko.algorithms.leetcode

/**
 * 2678. Number of Senior Citizens
 * @see <a href="https://leetcode.com/problems/number-of-senior-citizens">Source</a>
 */
fun interface CountSeniors {
    operator fun invoke(details: Array<String>): Int
}

class CountSeniorsBruteForce : CountSeniors {
    override fun invoke(details: Array<String>): Int {
        return details.map {
            it.getAge()
        }.filter {
            it > MIN_AGE
        }.size
    }

    private fun String.getAge(): Int {
        return substring(AGE_OFFSET, length - 2).toInt()
    }

    companion object {
        private const val MIN_AGE = 60
        private const val AGE_OFFSET = 11
    }
}
