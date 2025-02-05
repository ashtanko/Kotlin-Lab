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

import java.util.LinkedList
import java.util.Queue

/**
 * 950. Reveal Cards In Increasing Order
 * @see <a href="https://leetcode.com/problems/reveal-cards-in-increasing-order">Source</a>
 */
fun interface RevealCardsInIncreasingOrder {
    operator fun invoke(deck: IntArray): IntArray
}

class RevealCardsInIncreasingOrderTwoPointers : RevealCardsInIncreasingOrder {
    override fun invoke(deck: IntArray): IntArray {
        val size = deck.size
        val result = IntArray(size)
        var skip = false
        var indexInDeck = 0
        var indexInResult = 0

        deck.sort()

        while (indexInDeck < size) {
            // There is an available gap in result
            if (result[indexInResult] == 0) {
                // Add a card to result
                if (!skip) {
                    result[indexInResult] = deck[indexInDeck]
                    indexInDeck++
                }

                // Toggle skip to alternate between adding and skipping cards
                skip = !skip
            }
            // Progress to next index of result array
            indexInResult = (indexInResult + 1) % size
        }
        return result
    }
}

class RevealCardsInIncreasingOrderQueue : RevealCardsInIncreasingOrder {
    override fun invoke(deck: IntArray): IntArray {
        val size: Int = deck.size
        val queue: Queue<Int> = LinkedList()

        // Create a queue of indexes
        for (i in 0 until size) {
            queue.add(i)
        }

        deck.sort()

        // Put cards at correct index in result
        val result = IntArray(size)
        for (i in 0 until size) {
            // Reveal Card and place in result
            result[queue.poll()] = deck[i]

            // Move next card to bottom
            queue.add(queue.poll())
        }
        return result
    }
}
