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

abstract class IncrementalStackTest<out T : IncrementalStack>(private val strategy: T) {

    @Test
    fun test() {
        strategy.push(1)
        strategy.push(2)
        assertThat(strategy.pop()).isEqualTo(2)
        strategy.push(2)
        strategy.push(3)
        strategy.push(4)
        strategy.increment(5, 100)
        strategy.increment(2, 100)
        assertThat(strategy.pop()).isEqualTo(103)
        assertThat(strategy.pop()).isEqualTo(202)
        assertThat(strategy.pop()).isEqualTo(201)
        assertThat(strategy.pop()).isEqualTo(-1)
    }
}

class IncrementalStackArrayTest : IncrementalStackTest<IncrementalStackArray>(IncrementalStackArray(3))
class IncrementalStackLinkedListTest : IncrementalStackTest<IncrementalStackLinkedList>(IncrementalStackLinkedList(3))
class IncrementalStackLazyTest : IncrementalStackTest<IncrementalStackLazy>(IncrementalStackLazy(3))
