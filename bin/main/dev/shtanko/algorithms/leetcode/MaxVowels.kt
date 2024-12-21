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
 * 1456. Maximum Number of Vowels in a Substring of Given Length
 * @see <a href="https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length">
 *     Source</a>
 */
fun interface MaxVowels {
    operator fun invoke(s: String, k: Int): Int
}

class MaxVowelsSlidingWindow : MaxVowels {
    override operator fun invoke(s: String, k: Int): Int {
        val vowels = setOf('a', 'e', 'i', 'o', 'u')

        // Build the window of size k, count the number of vowels it contains.
        var count = 0
        for (i in 0 until k) {
            count += if (vowels.contains(s[i])) 1 else 0
        }
        var answer = count

        // Slide the window to the right, focus on the added character and the
        // removed character and update "count". Record the largest "count".
        for (i in k until s.length) {
            count += if (vowels.contains(s[i])) 1 else 0
            count -= if (vowels.contains(s[i - k])) 1 else 0
            answer = Math.max(answer, count)
        }

        return answer
    }
}
