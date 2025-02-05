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

package dev.shtanko.grasp.creator.example1

class Product(val name: String, val price: Double)

class Catalog {
    fun createProduct(name: String, price: Double): Product {
        return Product(name, price)
    }
}

private const val PRODUCT_A_PRICE = 10.0
private const val PRODUCT_B_PRICE = 15.0
private const val PRODUCT_C_PRICE = 20.0

fun main() {
    val catalog = Catalog()

    val product1 = catalog.createProduct("Product A", PRODUCT_A_PRICE)
    val product2 = catalog.createProduct("Product B", PRODUCT_B_PRICE)
    val product3 = catalog.createProduct("Product C", PRODUCT_C_PRICE)

    println("Product 1: ${product1.name}, Price: ${product1.price}")
    println("Product 2: ${product2.name}, Price: ${product2.price}")
    println("Product 3: ${product3.name}, Price: ${product3.price}")
}
