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

import java.io.BufferedReader
import java.io.InputStreamReader

private const val POSITIVE_GAME_OVER_MESSAGE = "Congratulations! You won the game."
private const val NEGATIVE_GAME_OVER_MESSAGE = "So sorry, but you lost the game."
private const val SOLVED = 2048
private const val RANDOM = 0.10

fun run2048(grid: Array<IntArray>): String {
    if (isGridSolved(grid)) {
        return POSITIVE_GAME_OVER_MESSAGE
    } else if (isGridFull(grid)) {
        return NEGATIVE_GAME_OVER_MESSAGE
    }

    val populatedGrid = spawnNumber(grid)
    display(populatedGrid)

    return run2048(manipulateGrid(populatedGrid, waitForValidInput()))
}

fun isGridSolved(grid: Array<IntArray>): Boolean = grid.any { row -> row.contains(SOLVED) }
fun isGridFull(grid: Array<IntArray>): Boolean = grid.all { row -> !row.contains(0) }

fun spawnNumber(grid: Array<IntArray>): Array<IntArray> {
    val coordinates = locateSpawnCoordinates(grid)
    val number = generateNumber()

    return updateGrid(grid, coordinates, number)
}

fun locateSpawnCoordinates(grid: Array<IntArray>): Pair<Int, Int> {
    val emptyCells = arrayListOf<Pair<Int, Int>>()
    grid.forEachIndexed { x, row ->
        row.forEachIndexed { y, cell ->
            if (cell == 0) emptyCells.add(Pair(x, y))
        }
    }

    return emptyCells[(Math.random() * (emptyCells.size - 1)).toInt()]
}

fun generateNumber(): Int = if (Math.random() > RANDOM) 2 else 4

fun updateGrid(grid: Array<IntArray>, at: Pair<Int, Int>, value: Int): Array<IntArray> {
    val updatedGrid = grid.copyOf()
    updatedGrid[at.first][at.second] = value
    return updatedGrid
}

fun waitForValidInput(): String {
    val input = waitForInput()
    return if (isValidInput(input)) input else waitForValidInput()
}

fun isValidInput(input: String): Boolean = arrayOf("a", "s", "d", "w").contains(input)

fun waitForInput(): String {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    println("Direction?  ")
    return reader.readLine()
}

fun manipulateGrid(grid: Array<IntArray>, input: String): Array<IntArray> = when (input) {
    "a" -> shiftCellsLeft(grid)
    "s" -> shiftCellsDown(grid)
    "d" -> shiftCellsRight(grid)
    "w" -> shiftCellsUp(grid)
    else -> throw IllegalArgumentException("Expected one of [a, s, d, w]")
}

fun shiftCellsLeft(grid: Array<IntArray>): Array<IntArray> =
    grid.map(::mergeAndOrganizeCells).toTypedArray()

fun shiftCellsRight(grid: Array<IntArray>): Array<IntArray> =
    grid.map { row -> mergeAndOrganizeCells(row.reversed().toIntArray()).reversed().toIntArray() }.toTypedArray()

fun shiftCellsUp(grid: Array<IntArray>): Array<IntArray> {
    val rows: Array<IntArray> = arrayOf(
        intArrayOf(grid[0][0], grid[1][0], grid[2][0], grid[3][0]),
        intArrayOf(grid[0][1], grid[1][1], grid[2][1], grid[3][1]),
        intArrayOf(grid[0][2], grid[1][2], grid[2][2], grid[3][2]),
        intArrayOf(grid[0][3], grid[1][3], grid[2][3], grid[3][3]),
    )

    val updatedGrid = grid.copyOf()

    rows.map(::mergeAndOrganizeCells).forEachIndexed { rowIdx, row ->
        updatedGrid[0][rowIdx] = row[0]
        updatedGrid[1][rowIdx] = row[1]
        updatedGrid[2][rowIdx] = row[2]
        updatedGrid[3][rowIdx] = row[3]
    }

    return updatedGrid
}

fun shiftCellsDown(grid: Array<IntArray>): Array<IntArray> {
    val rows: Array<IntArray> = arrayOf(
        intArrayOf(grid[3][0], grid[2][0], grid[1][0], grid[0][0]),
        intArrayOf(grid[3][1], grid[2][1], grid[1][1], grid[0][1]),
        intArrayOf(grid[3][2], grid[2][2], grid[1][2], grid[0][2]),
        intArrayOf(grid[3][3], grid[2][3], grid[1][3], grid[0][3]),
    )

    val updatedGrid = grid.copyOf()

    rows.map(::mergeAndOrganizeCells).forEachIndexed { rowIdx, row ->
        updatedGrid[3][rowIdx] = row[0]
        updatedGrid[2][rowIdx] = row[1]
        updatedGrid[1][rowIdx] = row[2]
        updatedGrid[0][rowIdx] = row[3]
    }

    return updatedGrid
}

fun mergeAndOrganizeCells(row: IntArray): IntArray = organize(merge(row.copyOf()))

fun merge(row: IntArray, idxToMatch: Int = 0, idxToCompare: Int = 1): IntArray {
    if (idxToMatch >= row.size) return row
    if (idxToCompare >= row.size) return merge(row, idxToMatch + 1, idxToMatch + 2)
    if (row[idxToMatch] == 0) return merge(row, idxToMatch + 1, idxToMatch + 2)

    return if (row[idxToMatch] == row[idxToCompare]) {
        row[idxToMatch] *= 2
        row[idxToCompare] = 0
        merge(row, idxToMatch + 1, idxToMatch + 2)
    } else {
        if (row[idxToCompare] != 0) {
            merge(row, idxToMatch + 1, idxToMatch + 2)
        } else {
            merge(row, idxToMatch, idxToCompare + 1)
        }
    }
}

fun organize(row: IntArray, idxToMatch: Int = 0, idxToCompare: Int = 1): IntArray {
    if (idxToMatch >= row.size) return row
    if (idxToCompare >= row.size) return organize(row, idxToMatch + 1, idxToMatch + 2)
    if (row[idxToMatch] != 0) return organize(row, idxToMatch + 1, idxToMatch + 2)

    return if (row[idxToCompare] != 0) {
        row[idxToMatch] = row[idxToCompare]
        row[idxToCompare] = 0
        organize(row, idxToMatch + 1, idxToMatch + 2)
    } else {
        organize(row, idxToMatch, idxToCompare + 1)
    }
}

fun display(grid: Array<IntArray>) {
    val prettyPrintableGrid = grid.map { row ->
        row.map { cell ->
            if (cell == 0) "    " else java.lang.String.format("%4d", cell)
        }
    }

    println("New Grid:")
    prettyPrintableGrid.forEach { row ->
        println("+----+----+----+----+")
        row.forEach { print("|$it") }
        println("|")
    }
    println("+----+----+----+----+")
}

fun main() {
    val grid = arrayOf(
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
    )

    val gameOverMessage = run2048(grid)
    println(gameOverMessage)
}
