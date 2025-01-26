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
 * 926. Flip String to Monotone Increasing
 * @see <a href="https://leetcode.com/problems/flip-string-to-monotone-increasing/">Source</a>
 */
fun interface MinFlipsMonoIncr {
    operator fun invoke(s: String): Int
}

class MinFlipsMonoIncrOnePass : MinFlipsMonoIncr {
    override operator fun invoke(s: String): Int {
        if (s.isEmpty()) return 0

        val sChars: CharArray = s.toCharArray()
        var flipCount = 0
        var onesCount = 0

        for (i in sChars.indices) {
            if (sChars[i] == '0') {
                if (onesCount == 0) continue else flipCount++
            } else {
                onesCount++
            }
            if (flipCount > onesCount) {
                flipCount = onesCount
            }
        }
        return flipCount
    }
}
