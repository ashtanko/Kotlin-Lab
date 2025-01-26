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

import java.util.Locale

/**
 * Binary Watch
 * @see <a href="https://leetcode.com/problems/binary-watch/">Source</a>
 */
object BinaryWatch {
    private const val SKIP_HOUR_1 = 11
    private const val SKIP_HOUR_2 = 59

    operator fun invoke(num: Int): List<String> {
        val result = ArrayList<String>()
        for (i in 0..SKIP_HOUR_1) {
            for (j in 0..SKIP_HOUR_2) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    result.add(String.format(Locale.getDefault(), "%d:%02d", i, j))
                }
            }
        }
        return result
    }
}
