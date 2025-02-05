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

package dev.shtanko.concurrency

import java.util.concurrent.ForkJoinPool
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class MergeSortTaskTest {

    @Test
    fun `sort small array`() {
        val array = intArrayOf(5, 3, 8, 4, 2)
        val task = MergeSortTask(array, 0, array.size)
        ForkJoinPool.commonPool().invoke(task)
        assertArrayEquals(intArrayOf(2, 3, 4, 5, 8), array)
    }

    @Test
    fun `sort large array`() {
        val array = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val task = MergeSortTask(array, 0, array.size)
        ForkJoinPool.commonPool().invoke(task)
        assertArrayEquals(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), array)
    }

    @Test
    fun `sort array with duplicates`() {
        val array = intArrayOf(5, 3, 8, 3, 2, 8, 2)
        val task = MergeSortTask(array, 0, array.size)
        ForkJoinPool.commonPool().invoke(task)
        assertArrayEquals(intArrayOf(2, 2, 3, 3, 5, 8, 8), array)
    }

    @Test
    fun `sort already sorted array`() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        val task = MergeSortTask(array, 0, array.size)
        ForkJoinPool.commonPool().invoke(task)
        assertArrayEquals(intArrayOf(1, 2, 3, 4, 5), array)
    }

    @Test
    fun `sort empty array`() {
        val array = intArrayOf()
        val task = MergeSortTask(array, 0, array.size)
        ForkJoinPool.commonPool().invoke(task)
        assertArrayEquals(intArrayOf(), array)
    }

    @Test
    fun `sort single element array`() {
        val array = intArrayOf(1)
        val task = MergeSortTask(array, 0, array.size)
        ForkJoinPool.commonPool().invoke(task)
        assertArrayEquals(intArrayOf(1), array)
    }
}
