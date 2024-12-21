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

import dev.shtanko.patterns.structural.adapter.examples.figures.round.RoundHole
import dev.shtanko.patterns.structural.adapter.examples.figures.round.RoundPeg
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RoundHoleTest {

    @Test
    fun `test round peg fits in round hole`() {
        // Arrange
        val roundHole = RoundHole(5.0)
        val roundPeg = RoundPeg(2.0)
        // Act
        val actualFits = roundHole.fits(roundPeg)
        // Assert
        assertTrue(actualFits)
    }

    @Test
    fun `test round peg does not fit in smaller round hole`() {
        // Arrange
        val roundHole = RoundHole(2.0)
        val roundPeg = RoundPeg(5.0)
        // Act
        val actualFits = roundHole.fits(roundPeg)
        // Assert
        assertFalse(actualFits)
    }
}
