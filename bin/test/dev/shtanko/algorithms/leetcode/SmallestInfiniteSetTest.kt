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

package dev.shtanko.algorithms.leetcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

abstract class SmallestInfiniteSetTest<out T : SmallestInfiniteSet>(private val strategy: T) {

    @Test
    fun `smallest infinite set test`() {
        strategy.addBack(2)
        assertThat(strategy.popSmallest()).isEqualTo(1)
        assertThat(strategy.popSmallest()).isEqualTo(2)
        assertThat(strategy.popSmallest()).isEqualTo(3)
        strategy.addBack(1)
        assertThat(strategy.popSmallest()).isEqualTo(1)
        assertThat(strategy.popSmallest()).isEqualTo(4)
        assertThat(strategy.popSmallest()).isEqualTo(5)
    }
}

class SmallestInfiniteSetHashTest : SmallestInfiniteSetTest<SmallestInfiniteSet>(SmallestInfiniteSetHash())
class SmallestInfiniteSetSortedSetTest : SmallestInfiniteSetTest<SmallestInfiniteSet>(SmallestInfiniteSetSortedSet())
