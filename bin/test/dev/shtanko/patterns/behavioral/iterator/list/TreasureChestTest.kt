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

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class TreasureChestTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `iterator test`(expectedItem: Item) {
        val chest = TreasureChest()
        val iterator = chest.iterator(expectedItem.type)
        assertNotNull(iterator)
        while (iterator.hasNext()) {
            val item = iterator.next()
            assertNotNull(item)
            assertEquals(expectedItem.type, item?.type)
            val name = item.toString()
            assertNotNull(name)
            if (expectedItem.toString() == name) {
                return
            }
        }

        fail<Any>("Expected to find item [$expectedItem] using iterator, but we didn't.")
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `get items test`(expectedItem: Item) {
        val chest = TreasureChest()
        val items = chest.items
        assertNotNull(items)
        for (item in items) {
            assertNotNull(item)
            assertNotNull(item.type)
            assertNotNull(item.toString())

            val sameType = expectedItem.type == item.type
            val sameName = expectedItem.toString() == item.toString()
            if (sameType && sameName) {
                return
            }
        }
        fail<Any>("Expected to find item [$expectedItem] in the item list, but we didn't.")
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = listOf(
            Arguments.of(Item(ItemType.POTION, "Potion of courage")),
            Arguments.of(Item(ItemType.RING, "Ring of shadows")),
            Arguments.of(Item(ItemType.POTION, "Potion of wisdom")),
            Arguments.of(Item(ItemType.POTION, "Potion of blood")),
            Arguments.of(Item(ItemType.WEAPON, "Sword of silver +1")),
            Arguments.of(Item(ItemType.POTION, "Potion of rust")),
            Arguments.of(Item(ItemType.POTION, "Potion of healing")),
            Arguments.of(Item(ItemType.RING, "Ring of armor")),
            Arguments.of(Item(ItemType.WEAPON, "Steel halberd")),
            Arguments.of(Item(ItemType.WEAPON, "Dagger of poison")),
        ).stream()
    }
}
