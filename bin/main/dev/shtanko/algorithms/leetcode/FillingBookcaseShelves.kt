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
 * 1105. Filling Bookcase Shelves
 * @see <a href="https://leetcode.com/problems/filling-bookcase-shelves">Source</a>
 */
fun interface FillingBookcaseShelves {
    operator fun invoke(books: Array<IntArray>, shelfWidth: Int): Int
}

/**
 * Approach 1: Top-Down Dynamic Programming
 */
class FillingBookcaseShelvesTopDown : FillingBookcaseShelves {
    override fun invoke(books: Array<IntArray>, shelfWidth: Int): Int {
        val memo = Array(books.size) { IntArray(shelfWidth + 1) }
        return dpHelper(books, shelfWidth, memo, 0, shelfWidth, 0)
    }

    private fun dpHelper(
        books: Array<IntArray>,
        shelfWidth: Int,
        memo: Array<IntArray>,
        i: Int,
        remainingShelfWidth: Int,
        maxHeight: Int,
    ): Int {
        val currentBook = books[i]
        val maxHeightUpdated = maxOf(maxHeight, currentBook[1])
        if (i == books.size - 1) {
            // For the last book, store it in the current shelf if possible,
            // or start a new shelf with it
            if (remainingShelfWidth >= currentBook[0]) return maxHeightUpdated
            return maxHeight + currentBook[1]
        }
        // Return answer if already computed
        if (memo[i][remainingShelfWidth] != 0) {
            return memo[i][remainingShelfWidth]
        } else {
            // Calculate the height of the bookcase if we put the current book on the new shelf
            val option1Height = maxHeight + dpHelper(
                books,
                shelfWidth,
                memo,
                i + 1,
                shelfWidth - currentBook[0],
                currentBook[1],
            )
            if (remainingShelfWidth >= currentBook[0]) {
                // Calculate height of the bookcase if we put the current book on the current shelf
                val option2Height = dpHelper(
                    books,
                    shelfWidth,
                    memo,
                    i + 1,
                    remainingShelfWidth - currentBook[0],
                    maxHeightUpdated,
                )
                // Store result in cache
                memo[i][remainingShelfWidth] = minOf(option1Height, option2Height)
                return memo[i][remainingShelfWidth]
            }
            // Store result in cache
            memo[i][remainingShelfWidth] = option1Height
            return memo[i][remainingShelfWidth]
        }
    }
}

/**
 * Approach 2: Bottom-Up Dynamic Programming
 */
class FillingBookcaseShelvesBottomUp : FillingBookcaseShelves {
    override fun invoke(books: Array<IntArray>, shelfWidth: Int): Int {
        val dp = IntArray(books.size + 1)
        // base cases
        dp[0] = 0
        dp[1] = books[0][1]

        for (i in 2..books.size) {
            // new shelf built to hold current book
            var remainingShelfWidth = shelfWidth - books[i - 1][0]
            var maxHeight = books[i - 1][1]
            dp[i] = books[i - 1][1] + dp[i - 1]

            var j = i - 1
            // calculate the height when previous books are added onto a new shelf
            while (j > 0 && remainingShelfWidth - books[j - 1][0] >= 0) {
                maxHeight = maxOf(maxHeight, books[j - 1][1])
                remainingShelfWidth -= books[j - 1][0]
                dp[i] = minOf(dp[i], maxHeight + dp[j - 1])
                j--
            }
        }

        return dp[books.size]
    }
}
