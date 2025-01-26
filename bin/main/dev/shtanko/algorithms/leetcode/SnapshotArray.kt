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

import java.util.TreeMap

/**
 * 1146. Snapshot Array
 * @see <a href="https://leetcode.com/problems/snapshot-array">Source</a>
 */
sealed interface SnapshotArray {
    fun set(index: Int, value: Int)

    fun snap(): Int

    fun get(index: Int, snapId: Int): Int
}

class SnapshotArrayImpl(length: Int) : SnapshotArray {
    private var snapId = 0
    private val historyRecords: Array<TreeMap<Int, Int>> = Array(length) { TreeMap() }

    init {
        for (i in 0 until length) {
            historyRecords[i][0] = 0
        }
    }

    override fun set(index: Int, value: Int) {
        historyRecords[index][snapId] = value
    }

    override fun snap(): Int {
        return snapId++
    }

    override fun get(index: Int, snapId: Int): Int {
        return historyRecords[index].floorEntry(snapId).value
    }
}
