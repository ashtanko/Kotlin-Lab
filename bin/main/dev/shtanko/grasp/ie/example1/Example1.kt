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

package dev.shtanko.grasp.ie.example1

class Item(val name: String, val price: Double)

class Order {
    private val items = mutableListOf<Item>()

    fun addItem(item: Item) {
        items.add(item)
    }

    fun calculateTotalCost(): Double {
        var totalCost = 0.0
        for (item in items) {
            totalCost += item.price
        }
        return totalCost
    }
}

private const val PRODUCT_A_PRICE = 10.0
private const val PRODUCT_B_PRICE = 15.0
private const val PRODUCT_C_PRICE = 20.0

fun main() {
    val order = Order()

    order.addItem(Item("Product A", PRODUCT_A_PRICE))
    order.addItem(Item("Product B", PRODUCT_B_PRICE))
    order.addItem(Item("Product C", PRODUCT_C_PRICE))

    val totalCost = order.calculateTotalCost()
    println("Total cost of the order: $totalCost")
}
