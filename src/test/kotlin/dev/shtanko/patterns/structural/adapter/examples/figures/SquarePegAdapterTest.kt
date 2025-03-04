/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.patterns.structural.adapter.examples.figures

import dev.shtanko.patterns.structural.adapter.examples.figures.adapters.SquarePegAdapter
import dev.shtanko.patterns.structural.adapter.examples.figures.square.SquarePeg
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SquarePegAdapterTest {

    @Test
    fun `test adapter converts square peg to round peg`() {
        // Arrange
        val squarePeg = SquarePeg(5.0)
        val adapter = SquarePegAdapter(squarePeg)
        // Act
        val actualRadius = adapter.radius
        // Assert
        assertEquals(3.536, actualRadius, 0.001)
    }
}
