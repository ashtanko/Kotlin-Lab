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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2914. Minimum Number of Changes to Make Binary String Beautiful
 * @see <a href="https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/">Source</a>
 */
@Medium("https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful")
fun interface MinChanges {
    operator fun invoke(str: String): Int
}

class MinChangesGreedy : MinChanges {
    override fun invoke(str: String): Int {
        var minChangesRequired = 0

        // Check pairs of characters (i, i+1) with step size 2
        for (i in 0 until str.length - 1 step 2) {
            // If characters in the current pair don't match,
            // we need one change to make them equal
            if (str[i] != str[i + 1]) {
                minChangesRequired++
            }
        }

        return minChangesRequired
    }
}
