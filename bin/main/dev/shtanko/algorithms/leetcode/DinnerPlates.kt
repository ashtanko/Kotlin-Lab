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

import java.util.PriorityQueue
import java.util.Stack
import java.util.TreeSet

/**
 * 1172. Dinner Plate Stacks
 */
interface DinnerPlatesDS {
    fun push(value: Int)
    fun pop(): Int
    fun popAtStack(index: Int): Int
}

class DinnerPlates(val capacity: Int) : DinnerPlatesDS {

    private val pq: PriorityQueue<Int> = PriorityQueue()
    private val map: MutableMap<Int, Stack<Int>> = HashMap()
    private var max = 0

    init {
        map[max] = Stack()
    }

    override fun push(value: Int) {
        if (pq.isEmpty()) {
            if (map[max]?.size == capacity) {
                max++
            }
            map.putIfAbsent(max, Stack())
            map[max]?.push(value)
        } else {
            val index = pq.poll()
            map[index]?.push(value)
        }
    }

    override fun pop(): Int {
        if (max == -1) {
            max = 0
            return -1
        }
        if (map[max]?.size == 0) {
            max--
            return pop()
        }
        val top = map[max]?.pop()
        if (map[max]?.size == 0) {
            max--
        }
        return top ?: -1
    }

    override fun popAtStack(index: Int): Int {
        if (!map.containsKey(index) || map[index]?.size == 0) {
            return -1
        }
        val res = map[index]?.pop()
        pq.offer(index)
        return res ?: -1
    }
}

class DinnerPlatesTree(val capacity: Int) : DinnerPlatesDS {
    private val stacks: Stack<Stack<Int>> = Stack()
    private val set = TreeSet<Int>()

    override fun push(value: Int) {
        if (set.isNotEmpty()) {
            val idx = set.iterator().next()
            stacks[idx].push(value)
            if (stacks[idx].size == capacity) {
                set.remove(idx)
            }
        } else {
            if (stacks.isEmpty() || stacks.peek().size == capacity) {
                stacks.add(Stack())
                stacks.peek().add(value)
            } else {
                stacks.peek().add(value)
            }
        }
    }

    override fun pop(): Int {
        if (stacks.isNotEmpty()) {
            val k = stacks.peek().pop()
            while (stacks.isNotEmpty() && stacks.peek().isEmpty()) {
                set.remove(stacks.size - 1)
                stacks.pop()
            }
            return k
        }
        return -1
    }

    override fun popAtStack(index: Int): Int {
        if (index >= stacks.size || stacks[index].isEmpty()) {
            return -1
        }
        if (index == stacks.size - 1) {
            return pop()
        }
        set.add(index)
        return stacks[index].pop()
    }
}
