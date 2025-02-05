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

import java.util.Random

/**
 * 380. Insert Delete GetRandom O(1)
 * @see <a href="https://leetcode.com/problems/insert-delete-getrandom-o1">Source</a>
 */
class RandomizedSet {

    private val nums: ArrayList<Int> = ArrayList()
    private val locs: HashMap<Int, Int> = HashMap()
    private val rand: Random = Random()

    fun insert(value: Int): Boolean {
        val contain: Boolean = locs.containsKey(value)
        if (contain) return false
        locs[value] = nums.size
        nums.add(value)
        return true
    }

    fun remove(value: Int): Boolean {
        val contain: Boolean = locs.containsKey(value)
        if (!contain) return false
        val loc: Int = locs.getOrDefault(value, 0)
        if (loc < nums.size - 1) { // Not the last one, then swap the last one with this val
            val lastOne: Int = nums[nums.size - 1]
            nums[loc] = lastOne
            locs[lastOne] = loc
        }
        locs.remove(value)
        nums.removeAt(nums.size - 1)
        return true
    }

    fun getRandom(): Int {
        return nums[rand.nextInt(nums.size)]
    }

    fun get(): ArrayList<Int> {
        return nums
    }
}
