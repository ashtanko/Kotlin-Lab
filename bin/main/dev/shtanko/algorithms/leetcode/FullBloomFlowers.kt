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

import java.util.PriorityQueue
import java.util.TreeMap

/**
 * 2251. Number of Flowers in Full Bloom
 * @see <a href="https://leetcode.com/problems/number-of-flowers-in-full-bloom">Source</a>
 */
interface FullBloomFlowers {
    operator fun invoke(flowers: Array<IntArray>, people: IntArray): IntArray
}

/**
 * Approach 1: Heap/Priority Queue
 */
class FullBloomFlowersPQ : FullBloomFlowers {
    override fun invoke(flowers: Array<IntArray>, people: IntArray): IntArray {
        val sortedPeople = people.copyOf()
        sortedPeople.sort()

        val sortedFlowers = flowers.sortedWith(compareBy({ it[0] }, { it[1] }))
        val dic = mutableMapOf<Int, Int>()
        val heap = PriorityQueue<Int>()

        var i = 0
        for (person in sortedPeople) {
            while (i < sortedFlowers.size && sortedFlowers[i][0] <= person) {
                heap.add(sortedFlowers[i][1])
                i++
            }

            while (heap.isNotEmpty() && heap.peek() < person) {
                heap.poll()
            }

            dic[person] = heap.size
        }

        val ans = IntArray(people.size)
        for (j in people.indices) {
            ans[j] = dic[people[j]] ?: 0
        }

        return ans
    }
}

/**
 * Approach 2: Difference Array + Binary Search
 */
class FullBloomFlowersBS : FullBloomFlowers {
    override fun invoke(flowers: Array<IntArray>, people: IntArray): IntArray {
        val difference = TreeMap<Int, Int>()
        difference[0] = 0

        for (flower in flowers) {
            val start = flower[0]
            val end = flower[1] + 1
            difference[start] = difference.getOrDefault(start, 0) + 1
            difference[end] = difference.getOrDefault(end, 0) - 1
        }

        val positions: MutableList<Int> = ArrayList()
        val prefix: MutableList<Int> = ArrayList()
        var curr = 0

        for (key in difference.keys) {
            positions.add(key)
            curr += difference[key]!!
            prefix.add(curr)
        }

        val ans = IntArray(people.size)
        for (j in people.indices) {
            val i = binarySearch(positions, people[j]) - 1
            ans[j] = prefix[i]
        }

        return ans
    }
}

/**
 * Approach 3: Simpler Binary Search
 */
class FullBloomFlowersSimplerBS : FullBloomFlowers {
    override fun invoke(flowers: Array<IntArray>, people: IntArray): IntArray {
        val starts: MutableList<Int> = ArrayList()
        val ends: MutableList<Int> = ArrayList()

        for (flower in flowers) {
            starts.add(flower[0])
            ends.add(flower[1] + 1)
        }

        starts.sort()
        ends.sort()
        val ans = IntArray(people.size)

        for (index in people.indices) {
            val person = people[index]
            val i: Int = binarySearch(starts, person)
            val j: Int = binarySearch(ends, person)
            ans[index] = i - j
        }

        return ans
    }
}

internal fun binarySearch(arr: List<Int>, target: Int): Int {
    var left = 0
    var right = arr.size
    while (left < right) {
        val mid = (left + right) / 2
        if (target < arr[mid]) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    return left
}
