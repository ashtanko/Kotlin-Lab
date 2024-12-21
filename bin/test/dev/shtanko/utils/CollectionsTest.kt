/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CollectionsTest {

    @Test
    fun `second elem on empty collection should throw the NoSuchElementException`() {
        val list = emptyList<Int>()
        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            list.second()
        }

        Assertions.assertEquals("List is empty.", exception.message)
    }

    @Test
    fun `second elem on one elem collection should throw the NoSuchElementException`() {
        val list = listOf(1)
        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            list.second()
        }

        Assertions.assertEquals("List has only one element.", exception.message)
    }

    @Test
    fun `second on collection should return second element`() {
        val list = listOf(1, 2, 3)
        Assertions.assertEquals(2, list.second())
    }

    @Test
    fun `third elem on empty collection should throw the NoSuchElementException`() {
        val list = emptyList<Int>()
        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            list.third()
        }

        Assertions.assertEquals("List is empty.", exception.message)
    }

    @Test
    fun `third elem on one elem collection should throw the NoSuchElementException`() {
        val list = listOf(1)
        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            list.third()
        }

        Assertions.assertEquals("List has not enough elements.", exception.message)
    }

    @Test
    fun `third on collection should return third element`() {
        val list = listOf(1, 2, 3)
        Assertions.assertEquals(3, list.third())
    }

    @Test
    fun `fourth elem on empty collection should throw the NoSuchElementException`() {
        val list = emptyList<Int>()
        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            list.fourth()
        }

        Assertions.assertEquals("List is empty.", exception.message)
    }

    @Test
    fun `fourth elem on one elem collection should throw the NoSuchElementException`() {
        val list = listOf(1)
        val exception = Assertions.assertThrows(NoSuchElementException::class.java) {
            list.fourth()
        }

        Assertions.assertEquals("List has not enough elements.", exception.message)
    }

    @Test
    fun `fourth on collection should return fourth element`() {
        val list = listOf(1, 2, 3, 4)
        Assertions.assertEquals(4, list.fourth())
    }
}
