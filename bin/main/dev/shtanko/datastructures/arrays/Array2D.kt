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

package dev.shtanko.datastructures.arrays

/**
 * 2D Array implementation
 * @param T The type of elements in the array
 * @property rows The number of rows in the array
 * @property cols The number of columns in the array
 * @property defaultValue The default value to initialize the array with
 * @constructor Creates a 2D array with the specified number of rows and columns
 */
class Array2D<T>(private val rows: Int, private val cols: Int, defaultValue: T) : Iterable<T> {

    private var data: MutableList<T> = MutableList(rows * cols) { defaultValue }

    /**
     * Function to get an element at (row, col)
     *
     * @param row The row index
     * @param col The column index
     * @return The element at (row, col)
     */
    operator fun get(row: Int, col: Int): T {
        return data[row * cols + col]
    }

    /**
     * Function to set an element at (row, col)
     *
     * @param row The row index
     * @param col The column index
     * @param value The value to set
     */
    operator fun set(row: Int, col: Int, value: T) {
        data[row * cols + col] = value
    }

    /**
     * Function to get an element at (row, col)
     *
     * @return An iterator over the elements of the array
     */
    override fun iterator(): Iterator<T> {
        return data.iterator()
    }

    /**
     * Function to get elements by row
     *
     * @param row The row index
     * @return The elements in the row
     */
    fun getRow(row: Int): List<T> {
        return (0 until cols).map { get(row, it) }
    }

    /**
     * Function to get elements by column
     *
     * @param col The column index
     * @return The elements in the column
     */
    fun getColumn(col: Int): List<T> {
        return (0 until rows).map { get(it, col) }
    }

    /**
     * Function to convert the 2D array to a list
     *
     * @return The list representation of the 2D array
     */
    fun toList(): List<T> {
        return data
    }

    /**
     * Function to print the 2D array
     */
    fun printArray() {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                print("${get(i, j)} ")
            }
            println()
        }
    }
}
