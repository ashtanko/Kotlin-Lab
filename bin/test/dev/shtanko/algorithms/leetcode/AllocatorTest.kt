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

class AllocatorTest {

    @Test
    fun `allocator test`() {
        val allocator = Allocator(10)
        mallocTest(allocator)
    }

    @Test
    fun `tree allocator test`() {
        val allocator = TAllocator(10)
        mallocTest(allocator)
    }

    private fun mallocTest(malloc: Malloc) {
        assertThat(malloc.allocate(1, 1)).isEqualTo(0)
        assertThat(malloc.allocate(1, 2)).isEqualTo(1)
        assertThat(malloc.allocate(1, 3)).isEqualTo(2)
        assertThat(malloc.free(2)).isEqualTo(1)
        assertThat(malloc.allocate(3, 4)).isEqualTo(3)
        assertThat(malloc.allocate(1, 1)).isEqualTo(1)
        assertThat(malloc.allocate(1, 1)).isEqualTo(6)
        assertThat(malloc.free(1)).isEqualTo(3)
        assertThat(malloc.allocate(10, 2)).isEqualTo(-1)
        assertThat(malloc.free(7)).isEqualTo(0)
    }
}
