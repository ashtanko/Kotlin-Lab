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
import kotlin.math.min

/**
 * 2073. Time Needed to Buy Tickets
 * @see <a href="https://leetcode.com/problems/time-needed-to-buy-tickets/">Source</a>
 */
fun interface TimeNeededToBuyTickets {
    operator fun invoke(tickets: IntArray, k: Int): Int
}

class TimeNeededToBuyTicketsQueue : TimeNeededToBuyTickets {
    override fun invoke(tickets: IntArray, k: Int): Int {
        val queue: Queue<Int> = LinkedList()
        // Initialize the queue with ticket indices
        for (i in tickets.indices) {
            queue.add(i)
        }

        var time = 0
        // Loop until the queue is empty
        while (queue.isNotEmpty()) {
            // Increment the time counter for each iteration
            time++

            // Get the front element of the queue
            val front: Int = queue.poll()

            // Buy a ticket for the front person
            tickets[front]--

            // If person k bought all their tickets, return time
            if (k == front && tickets[front] == 0) {
                return time
            }

            // Re-add the current index to the queue for the next iteration
            if (tickets[front] != 0) {
                queue.add(front)
            }
        }

        return time
    }
}

class TimeNeededToBuyTicketsNoQueue : TimeNeededToBuyTickets {
    override fun invoke(tickets: IntArray, k: Int): Int {
        val n: Int = tickets.size
        var time = 0

        // If person k only needs one ticket, return the time to buy it
        if (tickets[k] == 1) return k + 1

        // Continue buying tickets until person k buys all their tickets
        while (tickets[k] > 0) {
            for (i in 0 until n) {
                // Buy a ticket at index 'i' if available
                if (tickets[i] != 0) {
                    tickets[i]--
                    time++
                }
                // If person k bought all their rickets, return the time
                if (tickets[k] == 0) return time
            }
        }

        return time
    }
}

class TimeNeededToBuyTicketsOnePass : TimeNeededToBuyTickets {
    override fun invoke(tickets: IntArray, k: Int): Int {
        var time = 0

        for (i in tickets.indices) {
            // If the current person is before or at the desired person 'k'
            time += if (i <= k) {
                // Buy the minimum of tickets available at person 'k' and the current person
                min(tickets[k], tickets[i])
            } else {
                // If the current person is after 'k', buy the minimum of
                // (tickets available at person 'k' - 1) and the current person
                min(tickets[k] - 1, tickets[i])
            }
        }

        return time
    }
}
