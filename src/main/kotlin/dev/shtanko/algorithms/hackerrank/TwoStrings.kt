/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.algorithms.hackerrank

/**
 * [Two Strings](https://www.hackerrank.com/challenges/two-strings/problem)
 * @see <a href="https://www.hackerrank.com/challenges/two-strings/problem">Two Strings</a>
 *
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
data object TwoStrings {
    operator fun invoke(s1: String, s2: String): String {
        val set = HashSet<Char>()
        for (c in s1) {
            set.add(c)
        }
        for (c in s2) {
            if (c in set) {
                return "YES"
            }
        }
        return "NO"
    }
}
