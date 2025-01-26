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

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

abstract class LFUCacheTest<out T : LFUCache>(private val cache: T) {

    @Test
    fun `LFU cache test`() {
        cache.put(1, 1)
        cache.put(2, 2)
        assertEquals(1, cache.get(1))
        cache.put(3, 3)
        assertEquals(-1, cache.get(2))
        assertEquals(3, cache.get(3))
        cache.put(4, 4)
        assertEquals(-1, cache.get(1))
        assertEquals(3, cache.get(3))
        assertEquals(4, cache.get(4))
    }

    @Test
    fun `LFU cache put test`() {
        cache.put(1, 1)
        cache.put(2, 2)
        cache.put(1, 2)
        assertThat(cache.get(1), equalTo(2))
    }
}

class LFUCacheImplTest : LFUCacheTest<LFUCacheImpl>(LFUCacheImpl(2))
