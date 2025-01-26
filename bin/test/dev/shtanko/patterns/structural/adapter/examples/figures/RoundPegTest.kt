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

package dev.shtanko.patterns.structural.adapter.examples.figures

import dev.shtanko.patterns.structural.adapter.examples.figures.round.RoundPeg
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoundPegTest {

    @Test
    fun `test round peg radius is set correctly`() {
        // Arrange
        val radius = 5.0
        val roundPeg = RoundPeg(radius)
        // Act
        val actualRadius = roundPeg.radius
        // Assert
        assertEquals(radius, actualRadius, 0.0)
    }

    @Test
    fun `test round peg radius is updated correctly`() {
        // Arrange
        val initialRadius = 2.0
        val updatedRadius = 5.0
        val roundPeg = RoundPeg(initialRadius)
        // Act
        roundPeg.radius = updatedRadius
        val actualRadius = roundPeg.radius
        // Assert
        assertEquals(updatedRadius, actualRadius, 0.0)
    }
}
