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

import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.Deque
import java.util.LinkedList

/**
 * 948. Bag of Tokens
 * @see <a href="https://leetcode.com/problems/bag-of-tokens">Source</a>
 */
@Medium("https://leetcode.com/problems/bag-of-tokens")
fun interface BagOfTokens {
    operator fun invoke(tokens: IntArray, power: Int): Int
}

@Iterative
val bagOfTokensDeque = BagOfTokens { tokens, power ->
    var score = 0
    tokens.sort()
    var pow = power
    val deque: Deque<Int> = LinkedList()

    for (token in tokens) {
        deque.add(token)
    }

    while (deque.isNotEmpty()) {
        // When we have enough power, play token face-up
        if (pow >= deque.peekFirst()) {
            pow -= deque.pollFirst()
            score++
            // We don't have enough power to play a token face-up
            // When there is at least one token remaining,
            // and we have enough score, play token face-down
        } else if (deque.size > 1 && score > 0) {
            pow += deque.pollLast()
            score--
            // We don't have enough score, power, or tokens
            // to play face-up or down and increase our score
        } else {
            return@BagOfTokens score
        }
    }
    return@BagOfTokens score
}
