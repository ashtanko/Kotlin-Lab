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

import java.util.LinkedList
import java.util.Queue

private const val RADIANT = "Radiant"
private const val DIRE = "Dire"

/**
 * 649. Dota2 Senate
 * @see <a href="https://leetcode.com/problems/dota2-senate/">Source</a>
 */
fun interface Dota2Senate {
    operator fun invoke(senate: String): String
}

class Dota2SenateGreedy : Dota2Senate {
    override fun invoke(senate: String): String {
        // Convert to StringBuilder for easy deletion
        val senateArray = StringBuilder(senate)

        // Count of Each Type of Senator to check for Winner
        var rCount = 0
        var dCount = 0
        for (element in senateArray) {
            if (element == 'R') {
                rCount++
            } else {
                dCount++
            }
        }

        // Turn of Senator at this index
        var turn = 0

        // While No Winner
        while (rCount > 0 && dCount > 0) {
            // Ban the next opponent, starting at one index ahead
            // Taking MOD to loop around.
            // If index of banned senator is before current index,
            // then we need to decrement turn by 1, as we have removed
            // a senator from list
            if (senateArray[turn] == 'R') {
                val bannedSenatorBefore = ban(senateArray, 'D', (turn + 1) % senateArray.length)
                if (bannedSenatorBefore) {
                    turn--
                }
                dCount--
            } else {
                val bannedSenatorBefore = ban(senateArray, 'R', (turn + 1) % senateArray.length)
                if (bannedSenatorBefore) {
                    turn--
                }
                rCount--
            }

            // Increment Turn
            turn = (turn + 1) % senateArray.length
        }

        // Return Winner
        return if (rCount > 0) {
            RADIANT
        } else {
            DIRE
        }
    }

    // Ban the candidate "toBan", immediate next to "startAt"
    // If have to loop around, then it means next turn will be of
    // senator at same index. Returns loop around boolean
    private fun ban(senateArray: StringBuilder, toBan: Char, startAt: Int): Boolean {
        var loopAround = false
        var pointer = startAt
        while (true) {
            if (pointer == 0) {
                loopAround = true
            }
            if (senateArray[pointer] == toBan) {
                senateArray.deleteCharAt(pointer)
                break
            }
            pointer = (pointer + 1) % senateArray.length
        }
        return loopAround
    }
}

class Dota2SenateBooleanArray : Dota2Senate {
    override fun invoke(senate: String): String {
        // To mark Banned Senators
        val banned = BooleanArray(senate.length)

        // Count of Each Type of Senator who are not-banned
        var rCount = 0
        var dCount = 0
        for (element in senate) {
            if (element == 'R') {
                rCount++
            } else {
                dCount++
            }
        }

        // Turn of Senator at this Index
        var turn = 0

        // While both parties have at least one senator
        while (rCount > 0 && dCount > 0) {
            if (!banned[turn]) {
                if (senate[turn] == 'R') {
                    ban(senate, banned, 'D', (turn + 1) % senate.length)
                    dCount--
                } else {
                    ban(senate, banned, 'R', (turn + 1) % senate.length)
                    rCount--
                }
            }
            turn = (turn + 1) % senate.length
        }

        return if (dCount == 0) RADIANT else DIRE
    }

    // Ban the candidate "toBan", immediate next to "startAt"
    private fun ban(senate: String, banned: BooleanArray, toBan: Char, startAt: Int) {
        // Find the next eligible candidate to ban
        var pointer = startAt
        while (true) {
            if (senate[pointer] == toBan && !banned[pointer]) {
                banned[pointer] = true
                break
            }
            pointer = (pointer + 1) % senate.length
        }
    }
}

class Dota2SenateTwoQueues : Dota2Senate {
    override fun invoke(senate: String): String {
        // Number of Senator
        val n: Int = senate.length

        // Queues with Senator's Index.
        // Index will be used to find the next turn of Senator
        val rQueue: Queue<Int> = LinkedList()
        val dQueue: Queue<Int> = LinkedList()

        // Populate the Queues
        for (i in 0 until n) {
            if (senate[i] == 'R') {
                rQueue.add(i)
            } else {
                dQueue.add(i)
            }
        }

        // While both parties have at least one Senator
        while (rQueue.isNotEmpty() && dQueue.isNotEmpty()) {
            // Pop the Next-Turn Senate from both Q.
            val rTurn: Int = rQueue.poll()
            val dTurn: Int = dQueue.poll()

            // ONE having a larger index will be banned by a lower index
            //  will again get Turn, so EN-Queue again
            // But ensure its turn comes in the next round only
            if (dTurn < rTurn) {
                dQueue.add(dTurn + n)
            } else {
                rQueue.add(rTurn + n)
            }
        }

        // One's which Empty is not winner
        return if (rQueue.isEmpty()) DIRE else RADIANT
    }
}

class Dota2SenateSingleQueue : Dota2Senate {
    override fun invoke(senate: String): String {
        // Number of Senators of each party
        var rCount = 0
        var dCount = 0

        // Floating Ban Count
        var dFloatingBan = 0
        var rFloatingBan = 0

        // Queue of Senators
        val q: Queue<Char> = LinkedList()
        for (c in senate.toCharArray()) {
            q.add(c)
            if (c == 'R') rCount++ else dCount++
        }

        // While any party has eligible Senators
        while (rCount > 0 && dCount > 0) {
            // Pop the senator with turn
            val curr = q.poll()

            // If eligible, float the ban on the other party, enqueue again.
            // If not, decrement the floating ban and count of the party.
            if (curr == 'D') {
                if (dFloatingBan > 0) {
                    dFloatingBan--
                    dCount--
                } else {
                    rFloatingBan++
                    q.add('D')
                }
            } else {
                if (rFloatingBan > 0) {
                    rFloatingBan--
                    rCount--
                } else {
                    dFloatingBan++
                    q.add('R')
                }
            }
        }

        // Return the party with eligible Senators
        return if (rCount > 0) RADIANT else DIRE
    }
}
