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

package dev.shtanko.patterns.behavioral.iterator.list

import dev.shtanko.patterns.behavioral.iterator.CustomIterator

class TreasureChest {

    val items = listOf(
        Item(ItemType.POTION, "Potion of courage"),
        Item(ItemType.RING, "Ring of shadows"),
        Item(ItemType.POTION, "Potion of wisdom"),
        Item(ItemType.POTION, "Potion of blood"),
        Item(ItemType.WEAPON, "Sword of silver +1"),
        Item(ItemType.POTION, "Potion of rust"),
        Item(ItemType.POTION, "Potion of healing"),
        Item(ItemType.RING, "Ring of armor"),
        Item(ItemType.WEAPON, "Steel halberd"),
        Item(ItemType.WEAPON, "Dagger of poison"),
    )

    fun iterator(itemType: ItemType): CustomIterator<Item> {
        return TreasureChestItemIterator(this, itemType)
    }
}
