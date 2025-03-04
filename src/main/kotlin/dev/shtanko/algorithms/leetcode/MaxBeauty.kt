/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2070. Most Beautiful Item for Each Query
 * @see <a href="https://leetcode.com/problems/most-beautiful-item-for-each-query/">Source</a>
 */
@Medium("https://leetcode.com/problems/most-beautiful-item-for-each-query")
fun interface MaxBeauty {
    operator fun invoke(items: Array<IntArray>, queries: IntArray): IntArray
}

class MaxBeautySortingBinarySearch : MaxBeauty {
    override fun invoke(items: Array<IntArray>, queries: IntArray): IntArray {
        val sortedItems = items.sortedBy { it[0] }.toMutableList()

        var maxBeauty = sortedItems[0][1]
        for (i in sortedItems.indices) {
            maxBeauty = maxOf(maxBeauty, sortedItems[i][1])
            sortedItems[i][1] = maxBeauty
        }

        return queries.map { binarySearch(sortedItems, it) }.toTypedArray().toIntArray()
    }

    private fun binarySearch(items: List<IntArray>, targetPrice: Int): Int {
        var left = 0
        var right = items.size - 1
        var maxBeauty = 0

        while (left <= right) {
            val mid = (left + right) / 2
            if (items[mid][0] > targetPrice) {
                right = mid - 1
            } else {
                // Found viable price. Keep moving to right
                maxBeauty = maxOf(maxBeauty, items[mid][1])
                left = mid + 1
            }
        }
        return maxBeauty
    }
}

class MaxBeautySortingQueries : MaxBeauty {
    override fun invoke(items: Array<IntArray>, queries: IntArray): IntArray {
        val ans = IntArray(queries.size)

        // Sort both items and queries in ascending order
        items.sortBy { it[0] }

        // Create an array of queries with their original indices
        val queriesWithIndices = queries.mapIndexed { index, value -> intArrayOf(value, index) }.toTypedArray()

        // Sort queries with their original indices
        queriesWithIndices.sortBy { it[0] }

        var itemIndex = 0
        var maxBeauty = 0

        for (i in queriesWithIndices.indices) {
            val query = queriesWithIndices[i][0]
            val originalIndex = queriesWithIndices[i][1]

            // Process items whose beauty can be considered for the current query
            while (itemIndex < items.size && items[itemIndex][0] <= query) {
                maxBeauty = maxOf(maxBeauty, items[itemIndex][1])
                itemIndex++
            }

            // Assign the result to the corresponding index in the answer array
            ans[originalIndex] = maxBeauty
        }

        return ans
    }
}
