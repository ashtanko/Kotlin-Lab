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

class LRUCacheTest {

    @Test
    fun `LRU cache test`() {
        val lRUCache = LRUCache(2)
        lRUCache.put(1, 1) // cache is {1=1}
        lRUCache.put(2, 2) // cache is {1=1, 2=2}
        assertEquals(1, lRUCache.get(1))
        lRUCache.put(3, 3) // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        assertEquals(-1, lRUCache.get(2))
        lRUCache.put(4, 4) // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        assertEquals(-1, lRUCache.get(1))
        assertEquals(3, lRUCache.get(3))
        assertEquals(4, lRUCache.get(4))
    }
}
