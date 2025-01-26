/*
 * Copyright 2021 Oleksii Shtanko
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

import kotlin.math.min

/**
 * Put Boxes Into the Warehouse I
 * @see <a href="https://leetcode.com/problems/put-boxes-into-the-warehouse-i">Source</a>
 */
fun interface MaxBoxesInWarehouse {
    operator fun invoke(boxes: IntArray, warehouse: IntArray): Int
}

/**
 * Approach 1: Add Smallest Boxes to the Rightmost Warehouse Rooms
 */
class MaxBoxesInWarehouseAdd : MaxBoxesInWarehouse {
    override operator fun invoke(boxes: IntArray, warehouse: IntArray): Int {
        // Preprocess the height of the warehouse rooms to get usable heights
        for (i in 1 until warehouse.size) {
            warehouse[i] = min(warehouse[i - 1], warehouse[i])
        }

        // Iterate through boxes from smallest to largest
        boxes.sort()

        var count = 0

        for (i in warehouse.size - 1 downTo 0) {
            // Count the boxes that can fit in the current warehouse room
            if (count < boxes.size && boxes[count] <= warehouse[i]) {
                count++
            }
        }
        return count
    }
}

/**
 * Approach 2: Add Largest Possible Boxes from Left to Right
 */
class MaxBoxesInWarehouseLPB : MaxBoxesInWarehouse {
    override operator fun invoke(boxes: IntArray, warehouse: IntArray): Int {
        var i: Int = boxes.size - 1
        var count = 0
        boxes.sort()

        for (room in warehouse) {
            // Iterate through boxes from largest to smallest
            // Discard boxes that doesn't fit in the current warehouse
            while (i >= 0 && boxes[i] > room) {
                i--
            }
            if (i == -1) return count
            count++
            i--
        }

        return count
    }
}
