/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.patterns.creational.builder.examples.product

/**
 * The `ProductBuilder` class is used to construct a `Product` object step by step.
 * This class follows the Builder design pattern.
 */
class ProductBuilder {
    private val product = Product()

    /**
     * Sets the name of the product.
     * @param name The name of the product.
     * @return The current ProductBuilder instance.
     */
    fun setName(name: String) = apply { product.name = name }

    /**
     * Sets the price of the product.
     * @param price The price of the product.
     * @return The current ProductBuilder instance.
     */
    fun setPrice(price: Double) = apply { product.price = price }

    /**
     * Sets the expiration date of the product.
     * @param expirationDate The expiration date of the product.
     * @return The current ProductBuilder instance.
     */
    fun setExpirationDate(expirationDate: String) = apply { product.expirationDate = expirationDate }

    /**
     * Builds the product with the set properties and returns it.
     * @return The constructed Product object.
     */
    fun build() = product
}
