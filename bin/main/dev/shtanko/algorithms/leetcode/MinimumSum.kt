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

/**
 * 2160. Minimum Sum of Four Digit Number After Splitting Digits
 */
fun interface MinimumSum {
    operator fun invoke(num: Int): Int
}

class MinimumSumGreedy : MinimumSum {
    override operator fun invoke(num: Int): Int {
        val ch = num.toString().toCharArray().sorted()
        return ("" + ch[0] + ch[2]).toInt() + ("" + ch[1] + ch[3]).toInt()
    }
}
