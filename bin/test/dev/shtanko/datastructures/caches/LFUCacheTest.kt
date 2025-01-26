/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.datastructures.caches

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LFUCacheTest {

    @Test
    fun `LFU cache with int values`() {
        val lfuCache = LFUCache<Int, Int>(5)
        lfuCache[1] = 10
        lfuCache[2] = 20
        lfuCache[3] = 30
        lfuCache[4] = 40
        lfuCache[5] = 50
        assertThat(lfuCache[1]).isEqualTo(10)
        // this operation will remove value with key as 2
        lfuCache[6] = 60

        // will return null as value with key 2 is now evicted
        assertThat(lfuCache[2]).isNull()

        // should return 60
        assertThat(lfuCache[6]).isEqualTo(60)

        // this operation will remove value with key as 3
        lfuCache[7] = 70

        assertThat(lfuCache[2]).isNull()
        assertThat(lfuCache[7]).isEqualTo(70)
    }

    @Test
    fun `LFU cache with string values`() {
        val lfuCache = LFUCache<Int, String>(5)
        lfuCache[1] = "Alpha"
        lfuCache[2] = "Beta"
        lfuCache[3] = "Gamma"
        lfuCache[4] = "Delta"
        lfuCache[5] = "Eplison"

        // get method call will increase frequency of key 1 by 1
        assertThat(lfuCache[1]).isEqualTo("Alpha")

        // this operation will remove value with key as 2
        lfuCache[6] = "Digamma"

        // will return null as value with key 2 is now evicted
        assertThat(lfuCache[2]).isNull()

        // should return string Digamma
        assertThat(lfuCache[6]).isEqualTo("Digamma")

        // this operation will remove value with key as 3
        lfuCache[7] = "Zeta"

        assertThat(lfuCache[2]).isNull()
        assertThat(lfuCache[7]).isEqualTo("Zeta")
    }

    @Test
    fun `LFU cache with object values`() {
        val lfuCache = LFUCache<String, Any>(3)
        lfuCache["Cat"] = 10
        lfuCache["Dog"] = "10"
        lfuCache["Fish"] = 10.0

        assertThat(lfuCache["Cat"]).isEqualTo(10)
        assertThat(lfuCache["Dog"]).isEqualTo("10")
        assertThat(lfuCache["Fish"]).isEqualTo(10.0)

        assertThat(lfuCache["Cat"]).isInstanceOf(Integer::class.java)
        assertThat(lfuCache["Dog"]).isInstanceOf(String::class.java)
        assertThat(lfuCache["Fish"]).isInstanceOf(java.lang.Double::class.java)
    }

    @Test
    fun `null keys and values`() {
        val lfuCache = LFUCache<Int?, Any?>(3)
        lfuCache[null] = null
        assertThat(lfuCache[null]).isNull()
        lfuCache[1] = null
        assertThat(lfuCache[1]).isNull()
    }
}
