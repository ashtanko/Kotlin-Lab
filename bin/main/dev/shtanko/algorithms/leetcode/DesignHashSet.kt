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

import java.util.BitSet
import kotlin.math.abs

interface DesignHashSet {
    fun add(key: Int)
    fun remove(key: Int)
    fun contains(key: Int): Boolean
}

class DesignHashSetBitSet : DesignHashSet {

    private val bitSet = BitSet(BITS + 1)
    override fun add(key: Int) {
        bitSet[key] = true
    }

    override fun remove(key: Int) {
        bitSet[key] = false
    }

    override fun contains(key: Int): Boolean {
        return bitSet[key]
    }

    companion object {
        private const val BITS = 1_000_000
    }
}

class DesignHashSetSimple : DesignHashSet {

    private var buckets = Array<MutableList<Int>>(INITIAL_CAPACITY) { mutableListOf() }
    private var threshold = INITIAL_CAPACITY * LOAD_FACTOR
    private var count = 0
    override fun add(key: Int) {
        if (!contains(key)) {
            buckets[index(key)].add(key)
            count++

            if (count >= threshold) {
                rebalanced()
            }
        }
    }

    override fun remove(key: Int) {
        buckets[index(key)].remove(key)
        count--
    }

    override fun contains(key: Int): Boolean {
        return buckets[index(key)].contains(key)
    }

    private fun index(key: Int): Int {
        return abs(key.hashCode()) % buckets.size
    }

    private fun rebalanced() {
        val newBuckets = Array<MutableList<Int>>(buckets.size * 2) { mutableListOf() }

        buckets.forEach { list ->
            list.forEach { key ->
                newBuckets[key.hashCode() % newBuckets.size].add(key)
            }
        }

        buckets = newBuckets
        threshold = buckets.size * LOAD_FACTOR
    }

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }
}

class DesignHashSetImpl : DesignHashSet {

    private var capacity = DEFAULT_CAPACITY
    private var cnt = 0
    var list = arrayOfNulls<DesignList>(capacity)

    init {
        for (i in 0 until capacity) {
            list[i] = DesignList()
        }
    }

    override fun add(key: Int) {
        if (contains(key)) return
        if (cnt < capacity) {
            addTail(key)
        } else { // rehash
            capacity *= 2
            val copy = list
            val newList = arrayOfNulls<DesignList>(capacity)
            for (i in 0 until capacity) {
                newList[i] = DesignList()
            }
            list = newList
            for (i in copy.indices) {
                var head: DesignListNode? = copy[i]!!.head
                while (head!!.next != null && head.next?.value != null) {
                    addTail(head.next?.value!!)
                    head = head.next
                }
            }
            addTail(key)
        }
        cnt++
    }

    override fun remove(key: Int) {
        if (!contains(key)) return
        cnt--
        val idx = key % capacity
        var head: DesignListNode? = list[idx]!!.head
        while (head!!.next != null && head.next?.value != null) {
            if (head.next?.value == key) {
                val next = head.next
                next!!.next?.pre = head
                head.next = next.next
                next.pre = null
                next.next = null
                break
            }
            head = head.next
        }
    }

    override fun contains(key: Int): Boolean {
        val idx = key % capacity
        var head: DesignListNode? = list[idx]!!.head
        while (head!!.next != null && head.next?.value != null) {
            if (head.next?.value == key) return true
            head = head.next
        }
        return false
    }

    private fun addTail(key: Int) {
        val idx = key % capacity
        val tail = list[idx]!!.tail
        val pre: DesignListNode = tail.pre ?: return
        val cur = DesignListNode(key)
        pre.next = cur
        cur.pre = pre
        cur.next = tail
        tail.pre = cur
    }

    companion object {
        private const val DEFAULT_CAPACITY = 32
    }
}

class DesignList {
    var head = DesignListNode()
    var tail = DesignListNode()

    init {
        head.next = tail
        tail.pre = head
    }
}

data class DesignListNode(var value: Int? = null) {
    var next: DesignListNode? = null
    var pre: DesignListNode? = null
}
