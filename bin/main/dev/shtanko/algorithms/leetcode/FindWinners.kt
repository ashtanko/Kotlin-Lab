/*
 * Copyright 2022 Oleksii Shtanko
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
 * 2225. Find Players With Zero or One Losses
 * @see <a href="https://leetcode.com/problems/find-players-with-zero-or-one-losses/">Source</a>
 */
fun interface FindWinners {
    operator fun invoke(matches: Array<IntArray>): List<List<Int>>
}

/**
 * Approach 1: Hash Set
 */
class FindWinnersHashSet : FindWinners {
    override operator fun invoke(matches: Array<IntArray>): List<List<Int>> {
        val zeroLoss: MutableSet<Int> = HashSet()
        val oneLoss: MutableSet<Int> = HashSet()
        val moreLosses: MutableSet<Int> = HashSet()
        for (match in matches) {
            val winner = match[0]
            val loser = match[1]
            // Add winner.
            if (!oneLoss.contains(winner) && !moreLosses.contains(winner)) {
                zeroLoss.add(winner)
            }
            // Add or move loser.
            when {
                zeroLoss.contains(loser) -> {
                    zeroLoss.remove(loser)
                    oneLoss.add(loser)
                }

                oneLoss.contains(loser) -> {
                    oneLoss.remove(loser)
                    moreLosses.add(loser)
                }

                moreLosses.contains(loser) -> {
                    continue
                }

                else -> {
                    oneLoss.add(loser)
                }
            }
        }
        val answer: List<MutableList<Int>> = listOf(ArrayList(), ArrayList())
        answer[0].addAll(zeroLoss)
        answer[1].addAll(oneLoss)
        answer[0].sort()
        answer[1].sort()
        return answer
    }
}

/**
 * Approach 2: Hash Set + Hash Map
 */
class FindWinnersSetMap : FindWinners {
    override operator fun invoke(matches: Array<IntArray>): List<List<Int>> {
        val seen: MutableSet<Int> = HashSet()
        val lossesCount: MutableMap<Int, Int> = HashMap()

        for (match in matches) {
            val winner = match[0]
            val loser = match[1]
            seen.add(winner)
            seen.add(loser)
            lossesCount[loser] = (lossesCount[loser] ?: 0) + 1
        }

        // Add players with 0 or 1 loss to the corresponding list.
        val answer: List<MutableList<Int>> = listOf(ArrayList(), ArrayList())
        for (player in seen) {
            if (!lossesCount.containsKey(player)) {
                answer[0].add(player)
            } else if (lossesCount[player] == 1) {
                answer[1].add(player)
            }
        }

        answer[0].sort()
        answer[1].sort()

        return answer
    }
}

/**
 * Approach 3: Hash Map
 */
class FindWinnersMap : FindWinners {
    override operator fun invoke(matches: Array<IntArray>): List<List<Int>> {
        val lossesCount: MutableMap<Int, Int> = HashMap()
        for (match in matches) {
            val winner = match[0]
            val loser = match[1]
            lossesCount[winner] = lossesCount[winner] ?: 0
            lossesCount[loser] = (lossesCount[loser] ?: 0) + 1
        }

        val answer: List<MutableList<Int>> = listOf(ArrayList(), ArrayList())
        for (player in lossesCount.keys) if (lossesCount[player] == 0) {
            answer[0].add(player)
        } else if (lossesCount[player] == 1) {
            answer[1].add(player)
        }

        answer[0].sort()
        answer[1].sort()

        return answer
    }
}

/**
 * Approach 4: Counting with Array
 */
class FindWinnersCounting : FindWinners {

    companion object {
        private const val LIMIT = 100001
    }

    override operator fun invoke(matches: Array<IntArray>): List<List<Int>> {
        val lossesCount = IntArray(LIMIT) { -1 }

        for (match in matches) {
            val winner = match[0]
            val loser = match[1]
            if (lossesCount[winner] == -1) {
                lossesCount[winner] = 0
            }
            if (lossesCount[loser] == -1) {
                lossesCount[loser] = 1
            } else {
                lossesCount[loser]++
            }
        }

        val answer: List<MutableList<Int>> = listOf(ArrayList(), ArrayList())
        for (i in 1 until LIMIT) {
            if (lossesCount[i] == 0) {
                answer[0].add(i)
            } else if (lossesCount[i] == 1) {
                answer[1].add(i)
            }
        }

        return answer
    }
}
