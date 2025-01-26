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
import java.util.SortedSet
import java.util.TreeSet

/**
 * 2336. Smallest Number in Infinite Set
 * @see <a href="https://leetcode.com/problems/smallest-number-in-infinite-set">Source</a>
 */
interface SmallestInfiniteSet {
    fun popSmallest(): Int

    fun addBack(num: Int)
}

/**
 * Approach 1: Hashset + Heap
 */
class SmallestInfiniteSetHash : SmallestInfiniteSet {

    private var isPresent: HashSet<Int> = HashSet()
    private var addedIntegers: PriorityQueue<Int> = PriorityQueue()
    private var currentInteger: Int = 1

    override fun popSmallest(): Int {
        val answer: Int
        // If there are numbers in the min-heap,
        // top element is lowest among all the available numbers.
        if (addedIntegers.isNotEmpty()) {
            answer = addedIntegers.poll()
            isPresent.remove(answer)
        } else {
            answer = currentInteger
            currentInteger += 1
        }
        return answer
    }

    override fun addBack(num: Int) {
        if (currentInteger <= num || isPresent.contains(num)) {
            return
        }
        // We push 'num' in the min-heap if it isn't already present.
        addedIntegers.add(num)
        isPresent.add(num)
    }
}

class SmallestInfiniteSetSortedSet : SmallestInfiniteSet {

    private val addedIntegers: SortedSet<Int> = TreeSet()
    private var currentInteger: Int = 1

    override fun popSmallest(): Int {
        val answer: Int
        // If there are numbers in the sorted-set,
        // top element is lowest among all the available numbers.
        if (addedIntegers.isNotEmpty()) {
            answer = addedIntegers.first()
            addedIntegers.remove(answer)
        } else {
            answer = currentInteger
            currentInteger += 1
        }
        return answer
    }

    override fun addBack(num: Int) {
        if (currentInteger <= num || addedIntegers.contains(num)) {
            return
        }
        // We push 'num' in the sorted-set if it isn't already present.
        addedIntegers.add(num)
    }
}
