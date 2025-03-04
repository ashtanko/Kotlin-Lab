/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.github.data.cache

import dev.shtanko.github.data.model.SearchResponseModel
import io.kotest.common.runBlocking
import kotlin.test.assertNotNull
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NetworkResponseCacheTest {
    private lateinit var networkResponseCache: NetworkResponseCache<SearchResponseModel>

    @BeforeEach
    fun setUp() {
        networkResponseCache = InMemoryNetworkResponseCache()
    }

    @Test
    fun `GIVEN - no items in cache WHEN get THEN return null`() = runTest {
        val actual = networkResponseCache["non_existing_key"]
        assertNull(actual)
    }

    @Test
    fun `GIVEN - item in cache WHEN get THEN return not null and items are queal`() = runTest {
        val item = SearchResponseModel()
        networkResponseCache.put("existing_key", item)
        val actual = networkResponseCache["existing_key"]
        assertNotNull(actual)
        assertEquals(item, actual)
    }

    @Test
    fun `handle many operations safety`() = runTest {
        val item = SearchResponseModel()
        coroutineScope {
            repeat(1000) {
                launch(Dispatchers.Default) {
                    networkResponseCache.put("key", item)
                }
            }
        }
        val actual = networkResponseCache["key"]
        assertEquals(item, actual)
        assertEquals(1, networkResponseCache.size())
    }

    @Test
    fun `expire cache after half second`() = runTest(timeout = 3.seconds) {
        networkResponseCache = InMemoryNetworkResponseCache(expiration = 100.milliseconds)
        val item = SearchResponseModel()
        networkResponseCache.put("key", item)
        runBlocking {
            val actualBeforeExpire = networkResponseCache["key"]
            assertEquals(item, actualBeforeExpire)
            delay(50L)
            val actualAfterExpire = networkResponseCache["key"]
            assertEquals(item, actualAfterExpire)
        }
    }

    @Test
    fun `expire cache after 1 second`() = runTest(timeout = 3.seconds) {
        networkResponseCache = InMemoryNetworkResponseCache(expiration = 100.milliseconds)
        val item = SearchResponseModel()
        networkResponseCache.put("key", item)
        runBlocking {
            val actualBeforeExpire = networkResponseCache["key"]
            assertEquals(item, actualBeforeExpire)
            delay(101L)
            val actualAfterExpire = networkResponseCache["key"]
            assertNull(actualAfterExpire)
        }
    }
}
