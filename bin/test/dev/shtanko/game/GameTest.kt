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

package dev.shtanko.game

import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.lessThan
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.ValueSource

internal class GameTest {

    internal class ShiftCellsDownInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(2, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(4, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 2, 0),
                    intArrayOf(0, 0, 2, 0),
                    intArrayOf(0, 0, 2, 0),
                    intArrayOf(0, 0, 2, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 4, 0),
                    intArrayOf(0, 0, 4, 0),
                ),
            ),
        )
    }

    internal class ShiftCellsUpInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 2, 0, 0),
                    intArrayOf(0, 2, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 4, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(2, 4, 8, 16),
                ),
                arrayOf(
                    intArrayOf(4, 8, 16, 32),
                    intArrayOf(4, 8, 16, 32),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(16, 32, 64, 128),
                    intArrayOf(16, 32, 64, 128),
                    intArrayOf(16, 32, 64, 128),
                    intArrayOf(16, 32, 64, 128),
                ),
                arrayOf(
                    intArrayOf(32, 64, 128, 256),
                    intArrayOf(32, 64, 128, 256),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
        )
    }

    internal class ShiftCellsRightInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(4, 4, 4, 4),
                    intArrayOf(8, 8, 8, 8),
                    intArrayOf(16, 16, 16, 16),
                ),
                arrayOf(
                    intArrayOf(0, 0, 4, 4),
                    intArrayOf(0, 0, 8, 8),
                    intArrayOf(0, 0, 16, 16),
                    intArrayOf(0, 0, 32, 32),
                ),
            ),
        )
    }

    internal class ShiftCellsLeftInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(4, 4, 4, 4),
                    intArrayOf(8, 8, 8, 8),
                    intArrayOf(16, 16, 16, 16),
                ),
                arrayOf(
                    intArrayOf(4, 4, 0, 0),
                    intArrayOf(8, 8, 0, 0),
                    intArrayOf(16, 16, 0, 0),
                    intArrayOf(32, 32, 0, 0),
                ),
            ),
        )
    }

    internal class ManipulateGridInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "a",
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(4, 4, 4, 4),
                    intArrayOf(8, 8, 8, 8),
                    intArrayOf(16, 16, 16, 16),
                ),
                arrayOf(
                    intArrayOf(4, 4, 0, 0),
                    intArrayOf(8, 8, 0, 0),
                    intArrayOf(16, 16, 0, 0),
                    intArrayOf(32, 32, 0, 0),
                ),
            ),
            Arguments.of(
                "s",
                arrayOf(
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(4, 8, 16, 32),
                    intArrayOf(4, 8, 16, 32),
                ),
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(4, 8, 16, 32),
                    intArrayOf(8, 16, 32, 64),
                ),
            ),
            Arguments.of(
                "d",
                arrayOf(
                    intArrayOf(4, 4, 2, 2),
                    intArrayOf(8, 8, 4, 4),
                    intArrayOf(16, 16, 8, 8),
                    intArrayOf(32, 32, 16, 16),
                ),
                arrayOf(
                    intArrayOf(0, 0, 8, 4),
                    intArrayOf(0, 0, 16, 8),
                    intArrayOf(0, 0, 32, 16),
                    intArrayOf(0, 0, 64, 32),
                ),
            ),
            Arguments.of(
                "w",
                arrayOf(
                    intArrayOf(2, 8, 32, 256),
                    intArrayOf(2, 8, 32, 256),
                    intArrayOf(4, 16, 128, 512),
                    intArrayOf(4, 16, 128, 512),
                ),
                arrayOf(
                    intArrayOf(4, 16, 64, 512),
                    intArrayOf(8, 32, 256, 1024),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
        )
    }

    internal class UpdateGridInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                0 to 0,
                2,
                arrayOf(
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                0 to 1,
                2,
                arrayOf(
                    intArrayOf(0, 2, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                1 to 0,
                2,
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(2, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                3 to 3,
                2,
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 2),
                ),
            ),
        )
    }

    internal class GridInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                    intArrayOf(2, 2, 2, 2),
                ),
                true,
            ),
        )
    }

    internal class GridSolvedInputParams : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(32, 64, 128, 256),
                    intArrayOf(512, 1024, 2, 2),
                    intArrayOf(0, 0, 0, 0),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 4, 8, 16),
                    intArrayOf(32, 64, 128, 256),
                    intArrayOf(512, 1024, 2048, 2),
                    intArrayOf(0, 0, 0, 0),
                ),
                true,
            ),
        )
    }

    @Test
    internal fun `display empty grid test`() {
        val grid = arrayOf(
            intArrayOf(),
        )
        display(grid)
    }

    @Test
    internal fun `display low grid test`() {
        val grid = arrayOf(
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
        )
        display(grid)
    }

    @Test
    internal fun `organize test`() {
        val row = intArrayOf()
        val idxToMatch = 0
        val idxToCompare = 1
        val actual = organize(row, idxToMatch, idxToCompare)
        val expected = intArrayOf()
        assertArrayEquals(expected, actual)
    }

    @Test
    internal fun `merge test`() {
        val row = intArrayOf()
        val idxToMatch = 0
        val idxToCompare = 1
        val actual = merge(row, idxToMatch, idxToCompare)
        val expected = intArrayOf()
        assertArrayEquals(expected, actual)
    }

    @Test
    internal fun `merge and organize cells test`() {
        val row = intArrayOf()
        val actual = mergeAndOrganizeCells(row)
        val expected = intArrayOf()
        assertArrayEquals(expected, actual)
    }

    @Test
    internal fun `shift cells down error test`() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            shiftCellsDown(grid)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ShiftCellsDownInputParams::class)
    internal fun `shift cells down test`(grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = shiftCellsDown(grid)
        assertThat(actual, equalTo(expected))
    }

    @Test
    internal fun `shift cells up error test`() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            shiftCellsUp(grid)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ShiftCellsUpInputParams::class)
    internal fun `shift cells up test`(grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = shiftCellsUp(grid)
        assertThat(actual, equalTo(expected))
    }

    @Test
    internal fun `shift cells right empty test`() {
        val grid = arrayOf<IntArray>()
        val actual = shiftCellsRight(grid)
        val expected = arrayOf<IntArray>()
        assertArrayEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(ShiftCellsRightInputParams::class)
    internal fun `shift cells right test`(grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = shiftCellsRight(grid)
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(ShiftCellsLeftInputParams::class)
    internal fun `shift cells left test`(grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = shiftCellsLeft(grid)
        assertThat(actual, equalTo(expected))
    }

    @Test
    internal fun `manipulate grid with unsupported input test`() {
        assertThrows(IllegalArgumentException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            val input = ""
            manipulateGrid(grid, input)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ManipulateGridInputParams::class)
    internal fun `manipulate grid test`(input: String, grid: Array<IntArray>, expected: Array<IntArray>) {
        val actual = manipulateGrid(grid, input)
        assertArrayEquals(expected, actual)
    }

    @Test
    internal fun `manipulate grid with S input test`() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            val input = "s"
            manipulateGrid(grid, input)
        }
    }

    @Test
    internal fun `manipulate grid with D input test`() {
        val grid = arrayOf(
            intArrayOf(),
        )
        val input = "d"
        val actual = manipulateGrid(grid, input)
        val expected = arrayOf(
            intArrayOf(),
        )
        assertArrayEquals(expected, actual)
    }

    @Test
    internal fun `manipulate grid with W input test`() {
        assertThrows(ArrayIndexOutOfBoundsException::class.java) {
            val grid = arrayOf(
                intArrayOf(),
            )
            val input = "w"
            manipulateGrid(grid, input)
        }
    }

    @Test
    internal fun `when invalid input test`() {
        assertFalse(isValidInput(""))
    }

    @DisplayName("Valid inputs")
    @ParameterizedTest
    @ValueSource(strings = ["a", "s", "d", "w"])
    internal fun `when valid input test`(inputString: String) {
        val actual = isValidInput(inputString)
        assertThat(actual, equalTo(true))
    }

    @ParameterizedTest
    @ArgumentsSource(UpdateGridInputParams::class)
    internal fun `update grid test`(
        grid: Array<IntArray>,
        coordinates: Pair<Int, Int>,
        value: Int,
        expected: Array<IntArray>,
    ) {
        val actual = updateGrid(grid, coordinates, value)
        assertArrayEquals(expected, actual)
    }

    @Test
    internal fun `generate number test`() {
        val actual = generateNumber()
        assertThat(actual, lessThan(5))
    }

    @Test
    internal fun `locate spawn coordinates error test`() {
        val grid = arrayOf(
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
        )
        assertThrows<IndexOutOfBoundsException> {
            locateSpawnCoordinates(grid)
        }
    }

    @Test
    internal fun `locate spawn coordinates test`() {
        val grid = arrayOf(
            intArrayOf(0, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
        )
        val actual = locateSpawnCoordinates(grid)
        assertThat(actual, equalTo(0 to 0))
    }

    @Test
    internal fun `spawn number error test`() {
        val grid = arrayOf(
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
        )
        assertThrows<IndexOutOfBoundsException> {
            spawnNumber(grid)
        }
    }

    @Test
    internal fun `spawn number test`() {
        val grid = arrayOf(
            intArrayOf(0, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
            intArrayOf(2, 2, 2, 2),
        )
        val actual = spawnNumber(grid).first().first()
        assertThat(actual, greaterThan(0))
    }

    @ParameterizedTest
    @ArgumentsSource(GridInputParams::class)
    internal fun `is grid full test`(grid: Array<IntArray>, expected: Boolean) {
        val actual = isGridFull(grid)
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(GridSolvedInputParams::class)
    internal fun `is grid solved test`(grid: Array<IntArray>, expected: Boolean) {
        val actual = isGridSolved(grid)
        assertThat(actual, equalTo(expected))
    }

    @Test
    internal fun `run solved grid test`() {
        val grid = arrayOf(
            intArrayOf(2, 4, 8, 16),
            intArrayOf(32, 64, 128, 256),
            intArrayOf(512, 1024, 2048, 2),
            intArrayOf(0, 0, 0, 0),
        )
        val actual = run2048(grid)
        assertThat(actual, equalTo("Congratulations! You won the game."))
    }

    @Test
    internal fun `run full grid test`() {
        val grid = arrayOf(
            intArrayOf(2, 4, 8, 16),
            intArrayOf(32, 64, 128, 256),
            intArrayOf(512, 1024, 8, 2),
            intArrayOf(16, 32, 512, 256),
        )
        val actual = run2048(grid)
        assertThat(actual, equalTo("So sorry, but you lost the game."))
    }
}
