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

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class CatalogTest {
    @Test
    fun testCreateProduct() {
        val catalog = Catalog()

        val product = catalog.createProduct("Test Product", 25.0)

        assertEquals("Test Product", product.name)
        assertEquals(25.0, product.price, 0.01) // Allowing a small delta for floating-point precision
    }
}
