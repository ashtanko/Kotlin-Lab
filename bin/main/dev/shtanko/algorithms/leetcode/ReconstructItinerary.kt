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
import java.util.PriorityQueue
import java.util.Stack

/**
 * 332. Reconstruct Itinerary
 * @see <a href="https://leetcode.com/problems/reconstruct-itinerary">Source</a>
 */
fun interface ReconstructItinerary {
    operator fun invoke(tickets: List<List<String>>): List<String>
}

class ReconstructItineraryRecursive : ReconstructItinerary {

    private val targets: MutableMap<String, PriorityQueue<String>> = HashMap()
    private val route: MutableList<String> = LinkedList()

    override fun invoke(tickets: List<List<String>>): List<String> {
        for (ticket in tickets) targets.computeIfAbsent(ticket[0]) { PriorityQueue() }.add(ticket[1])
        visit("JFK")
        return route
    }

    private fun visit(airport: String) {
        while (targets.containsKey(airport) && targets[airport]?.isEmpty()?.not() == true) {
            targets[airport]?.let { visit(it.poll()) }
        }
        route.add(0, airport)
    }
}

class ReconstructItineraryIterative : ReconstructItinerary {
    override fun invoke(tickets: List<List<String>>): List<String> {
        val targets: MutableMap<String, PriorityQueue<String?>> = HashMap()
        for (ticket in tickets) targets.computeIfAbsent(ticket[0]) { PriorityQueue() }.add(ticket[1])
        val route: MutableList<String> = LinkedList()
        val stack: Stack<String> = Stack()
        stack.push("JFK")
        while (!stack.empty()) {
            while (targets.containsKey(stack.peek()) && targets[stack.peek()]?.isEmpty()?.not() == true) {
                stack.push(targets[stack.peek()]?.poll())
            }
            route.add(0, stack.pop())
        }
        return route
    }
}
