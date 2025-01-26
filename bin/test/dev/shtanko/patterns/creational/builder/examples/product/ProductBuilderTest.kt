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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductBuilderTest {

    @Test
    fun `test product builder`() {
        val product = ProductBuilder()
            .setName("Apple")
            .setPrice(1.0)
            .setExpirationDate("2023-01-01")
            .build()

        assertEquals("Apple", product.name)
        assertEquals(1.0, product.price)
        assertEquals("2023-01-01", product.expirationDate)
    }
}
