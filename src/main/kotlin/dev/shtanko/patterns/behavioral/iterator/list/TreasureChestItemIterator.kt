/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.patterns.behavioral.iterator.list

import dev.shtanko.patterns.behavioral.iterator.CustomIterator

data class TreasureChestItemIterator(
    val chest: TreasureChest,
    val type: ItemType,
    var idx: Int = -1,
) : CustomIterator<Item> {

    override fun hasNext(): Boolean {
        return findNextIdx() != -1
    }

    override fun next(): Item? {
        idx = findNextIdx()
        return if (idx != -1) {
            chest.items[idx]
        } else {
            null
        }
    }

    private fun findNextIdx(): Int {
        val items = chest.items
        var tempIdx = idx
        while (true) {
            tempIdx++
            if (tempIdx >= items.size) {
                tempIdx = -1
                break
            }
            if (type == ItemType.ANY || items[tempIdx].type == type) {
                break
            }
        }
        return tempIdx
    }
}
