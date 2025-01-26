/*
 * Copyright 2020 Oleksii Shtanko
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
 * Least Frequently Used Cache
 */
interface LFUCache {
    fun get(key: Int): Int

    fun put(key: Int, value: Int)
}

class LFUCacheImpl(capacity: Int) : LFUCache {

    private val vals = hashMapOf<Int, Int>()
    private val counts = hashMapOf<Int, Int>()
    private val lists = hashMapOf<Int, LinkedHashSet<Int>>()
    private var cap = capacity
    private var min = -1

    init {
        lists[1] = LinkedHashSet()
    }

    override fun get(key: Int): Int {
        if (!vals.containsKey(key)) {
            return -1
        }
        val count = counts[key] ?: 0
        counts[key] = count + 1
        lists[count]?.remove(key)
        if (count == min && lists[count]?.size == 0) {
            min++
        }
        if (!lists.containsKey(count + 1)) {
            lists[count + 1] = LinkedHashSet()
        }
        lists[count + 1]?.add(key)
        return vals[key] ?: -1
    }

    override fun put(key: Int, value: Int) {
        if (cap <= 0) return
        if (vals.containsKey(key)) {
            vals[key] = value
            get(key)
            return
        }
        if (vals.size >= cap) {
            val evit = lists[min]?.iterator()?.next()
            lists[min]?.remove(evit)
            vals.remove(evit)
        }
        vals[key] = value
        counts[key] = 1
        min = 1
        lists[1]?.add(key)
    }
}
