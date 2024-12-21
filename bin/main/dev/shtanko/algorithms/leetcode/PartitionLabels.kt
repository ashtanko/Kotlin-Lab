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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import kotlin.math.max

/**
 * Approach 1: Greedy
 * Time Complexity: O(N).
 * Space Complexity: O(1).
 */
class PartitionLabels {
    operator fun invoke(s: String): List<Int> {
        val last = IntArray(ALPHABET_LETTERS_COUNT)
        for (i in s.indices) {
            last[s[i] - 'a'] = i
        }
        var j = 0
        var anchor = 0
        val ans: MutableList<Int> = ArrayList()
        for (i in s.indices) {
            j = max(j, last[s[i] - 'a'])
            if (i == j) {
                ans.add(i - anchor + 1)
                anchor = i + 1
            }
        }
        return ans
    }
}
