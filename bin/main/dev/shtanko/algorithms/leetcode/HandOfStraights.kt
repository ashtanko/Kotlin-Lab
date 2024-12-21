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

import java.util.TreeMap

/**
 * 846. Hand of Straights
 * @see <a href="https://leetcode.com/problems/hand-of-straights/">Source</a>
 */
fun interface HandOfStraights {
    operator fun invoke(hand: IntArray, groupSize: Int): Boolean
}

class HandOfStraightsMap : HandOfStraights {
    override fun invoke(hand: IntArray, groupSize: Int): Boolean {
        val count: TreeMap<Int, Int> = TreeMap()
        for (card in hand) {
            if (!count.containsKey(card)) {
                count[card] = 1
            } else {
                count.replace(card, count.getOrDefault(card, 0) + 1)
            }
        }

        while (count.isNotEmpty()) {
            val first = count.firstKey()
            for (card in first until first + groupSize) {
                if (!count.containsKey(card)) return false
                val c = count.getOrDefault(card, 0)
                if (c == 1) {
                    count.remove(card)
                } else {
                    count.replace(card, c - 1)
                }
            }
        }

        return true
    }
}

class ReverseDecrement : HandOfStraights {
    override fun invoke(hand: IntArray, groupSize: Int): Boolean {
        if (hand.isEmpty() && groupSize == 0) return true
        if (!isValidHandSize(hand.size, groupSize)) {
            return false
        }
        val cardCount = buildCardCountMap(hand)

        return canFormGroups(hand, groupSize, cardCount)
    }

    private fun isValidHandSize(handSize: Int, groupSize: Int): Boolean {
        return handSize > 0 && handSize % groupSize == 0
    }

    private fun buildCardCountMap(hand: IntArray): HashMap<Int, Int> {
        val cardCount: HashMap<Int, Int> = HashMap()
        for (card in hand) {
            val count = cardCount.getOrDefault(card, 0)
            cardCount[card] = count + 1
        }
        return cardCount
    }

    private fun findStartCard(cardCount: HashMap<Int, Int>, card: Int): Int {
        var startCard = card
        while (cardCount.getOrDefault(startCard - 1, 0) > 0) {
            startCard--
        }
        return startCard
    }

    private fun canFormGroups(hand: IntArray, groupSize: Int, cardCount: HashMap<Int, Int>): Boolean {
        for (card in hand) {
            val startCard = findStartCard(cardCount, card)
            if (!checkAndDecrementGroups(cardCount, startCard, groupSize, card)) {
                return false
            }
        }
        return true
    }

    private fun checkAndDecrementGroups(
        cardCount: HashMap<Int, Int>,
        startCard: Int,
        groupSize: Int,
        card: Int,
    ): Boolean {
        var currentStartCard = startCard
        while (currentStartCard <= card) {
            while (cardCount.getOrDefault(currentStartCard, 0) > 0) {
                for (nextCard in currentStartCard until currentStartCard + groupSize) {
                    if (cardCount.getOrDefault(nextCard, 0) == 0) {
                        return false
                    }
                    cardCount[nextCard] = cardCount.getOrDefault(nextCard, 0) - 1
                }
            }
            currentStartCard++
        }
        return true
    }
}
