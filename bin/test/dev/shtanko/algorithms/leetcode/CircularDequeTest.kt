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

package dev.shtanko.algorithms.leetcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

abstract class CircularDequeTest<out T : CircularDeque>(private val strategy: T) {

    @Test
    fun designCircularDequeTest() {
        assertThat(strategy.insertLast(1)).isEqualTo(true)
        assertThat(strategy.insertLast(2)).isEqualTo(true)
        assertThat(strategy.insertFront(3)).isEqualTo(true)
        assertThat(strategy.insertFront(4)).isEqualTo(false)
        assertThat(strategy.getRear()).isEqualTo(2)
        assertThat(strategy.isFull()).isEqualTo(true)
        assertThat(strategy.deleteLast()).isEqualTo(true)
        assertThat(strategy.insertFront(4)).isEqualTo(true)
        assertThat(strategy.getFront()).isEqualTo(4)
    }
}

class CircularDequeArrayTest : CircularDequeTest<CircularDequeArray>(CircularDequeArray(3))
class CircularDequeLinkedListTest : CircularDequeTest<CircularDequeLinkedList>(CircularDequeLinkedList(3))
