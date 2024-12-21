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

import kotlin.math.max
import kotlin.math.min

/**
 * 1423. Maximum Points You Can Obtain from Cards
 * @see <a href="https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/">Source</a>
 */
fun interface MaxScore {
    operator fun invoke(cardPoints: IntArray, k: Int): Int
}

/**
 * Approach 1: Dynamic Programming
 * Time complexity: O(k).
 * Space complexity: O(k).
 */
class MaxScoreDP : MaxScore {
    override operator fun invoke(cardPoints: IntArray, k: Int): Int {
        val n: Int = cardPoints.size

        val frontSetOfCards = IntArray(k + 1)
        val rearSetOfCards = IntArray(k + 1)

        for (i in 0 until k) {
            frontSetOfCards[i + 1] = cardPoints[i] + frontSetOfCards[i]
            rearSetOfCards[i + 1] = cardPoints[n - i - 1] + rearSetOfCards[i]
        }

        var maxScore = 0
        // Each i represents the number of cards we take from the front.
        for (i in 0..k) {
            val currentScore = frontSetOfCards[i] + rearSetOfCards[k - i]
            maxScore = max(maxScore, currentScore)
        }

        return maxScore
    }
}

/**
 * Approach 2: Dynamic Programming - Space Optimized
 * Time complexity: O(k).
 * Space complexity: O(1).
 */
class MaxScoreDPSpaceOptimized : MaxScore {
    override operator fun invoke(cardPoints: IntArray, k: Int): Int {
        var frontScore = 0
        var rearScore = 0
        val n: Int = cardPoints.size

        for (i in 0 until k) {
            frontScore += cardPoints[i]
        }

        // take all k cards from the beginning
        var maxScore = frontScore

        // take i from the beginning and k - i from the end
        for (i in k - 1 downTo 0) {
            rearScore += cardPoints[n - (k - i)]
            frontScore -= cardPoints[i]
            val currentScore = rearScore + frontScore
            maxScore = max(maxScore, currentScore)
        }

        return maxScore
    }
}

/**
 * Approach 3: Sliding Window
 * Time complexity: O(n).
 * Space complexity: O(1).
 */
class MaxScoreSlidingWindow : MaxScore {
    override operator fun invoke(cardPoints: IntArray, k: Int): Int {
        var startingIndex = 0
        var presentSubarrayScore = 0
        val n: Int = cardPoints.size
        val requiredSubarrayLength = n - k
        var minSubarrayScore: Int
        var totalScore = 0

        // Total score obtained on selecting all the cards.
        for (i in cardPoints) {
            totalScore += i
        }

        minSubarrayScore = totalScore

        if (k == n) {
            return totalScore
        }

        for (i in 0 until n) {
            presentSubarrayScore += cardPoints[i]
            val presentSubarrayLength = i - startingIndex + 1
            // If a valid sub array (having size cardsPoints.length - k) is possible.
            if (presentSubarrayLength == requiredSubarrayLength) {
                minSubarrayScore = min(minSubarrayScore, presentSubarrayScore)
                presentSubarrayScore -= cardPoints[startingIndex++]
            }
        }
        return totalScore - minSubarrayScore
    }
}
