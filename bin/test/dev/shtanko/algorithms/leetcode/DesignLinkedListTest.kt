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

package dev.shtanko.algorithms.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DesignLinkedListTest {

    @Test
    fun `linked add at index test`() {
        val list = DesignLinkedList()
        list.addAtIndex(-1, 2)
        list.addAtIndex(1, 1)
        list.addAtIndex(2, 4)
        assertEquals(0, list.get(0))
        assertEquals(1, list.get(1))
        assertEquals(4, list.get(2))
    }

    @Test
    fun `linked list test`() {
        val list = DesignLinkedList()
        list.addAtHead(1)
        list.addAtTail(3)
        list.addAtIndex(1, 2)
        assertEquals(2, list.get(1))
        list.deleteAtIndex(1)
        assertEquals(3, list.get(1))
    }

    @Test
    fun `linked list test 2`() {
        val list = DesignLinkedList()
        list.addAtHead(2)
        list.deleteAtIndex(1)
        list.addAtHead(2)
        list.addAtHead(7)
        list.addAtHead(3)
        list.addAtHead(2)
        list.addAtHead(5)
        list.addAtTail(5)
        println(list.get(5))
        list.deleteAtIndex(6)
        list.deleteAtIndex(4)
    }
}
