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
 * 1750. Minimum Length of String After Deleting Similar Ends
 * @see <a href="https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends">Source</a>
 */
fun interface MinLenOfStrAfterDeletingSimilarEnds {
    operator fun invoke(str: String): Int
}

val minLenOfStrTwoPointers = MinLenOfStrAfterDeletingSimilarEnds { str ->
    var begin = 0
    var end: Int = str.length - 1

    // Delete similar ends until the ends differ, or they meet in the middle
    while (begin < end && str[begin] == str[end]) {
        val char: Char = str[begin]

        // Delete consecutive occurrences of c from prefix
        while (begin <= end && str[begin] == char) {
            begin++
        }

        // Delete consecutive occurrences of c from suffix
        while (end > begin && str[end] == char) {
            end--
        }
    }

    // Return the number of remaining characters
    return@MinLenOfStrAfterDeletingSimilarEnds end - begin + 1
}

class MinLenOfStrTailRec : MinLenOfStrAfterDeletingSimilarEnds {
    override fun invoke(str: String): Int {
        return deleteSimilarEnds(str, 0, str.length - 1)
    }

    // Deletes similar ends and returns remaining length
    private tailrec fun deleteSimilarEnds(str: String, begin: Int, end: Int): Int {
        // The ends differ or meet in the middle
        var mBegin = begin
        var mEnd = end
        return if (mBegin >= mEnd || str[mBegin] != str[mEnd]) {
            mEnd - mBegin + 1
        } else {
            val char = str[mBegin]

            // Delete consecutive occurrences of c from prefix
            while (mBegin <= mEnd && str[mBegin] == char) {
                mBegin++
            }

            // Delete consecutive occurrences of c from suffix
            while (mEnd > mBegin && str[mEnd] == char) {
                mEnd--
            }

            deleteSimilarEnds(str, mBegin, mEnd)
        }
    }
}
