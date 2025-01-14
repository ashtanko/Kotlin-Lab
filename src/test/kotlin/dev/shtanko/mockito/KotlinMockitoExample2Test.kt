/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.mockito

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class KotlinMockitoExample2Test {
    private val api: Service = mock()
    private val cache: Storage = mock()
    private val repository: UserRepository = UserRepositoryImpl(api, cache)

    @Test
    fun `GIVEN nickname present in storage WHEN getUsername THEN return value from storage AND don't call backend`() =
        runTest {
            val expectedUsername = "oleksii"
            whenever(cache.getUsername()).thenReturn(expectedUsername)
            val username = repository.getUsername()
            assertEquals(expectedUsername, username)
            verify(api, never()).fetchUsername()
        }

    @Test
    fun `GIVEN nickname not present in storage WHEN getNickname THEN return value from backend AND put to storage`() =
        runTest {
            val expectedUsername = "oleksii"
            whenever(api.fetchUsername()).thenReturn(expectedUsername)
            whenever(cache.getUsername()).thenReturn(null)
            val username = repository.getUsername()
            assertEquals(expectedUsername, username)
            verify(api, times(1)).fetchUsername()
            verify(cache, times(1)).saveUsername(expectedUsername)
        }
}

private interface Service {
    suspend fun fetchUsername(): String
}

private interface Storage {
    suspend fun saveUsername(username: String)
    suspend fun getUsername(): String?
}

private interface UserRepository {
    suspend fun getUsername(): String
}

private class UserRepositoryImpl(val api: Service, val cache: Storage) : UserRepository {
    override suspend fun getUsername(): String {
        val usernameFromCache = cache.getUsername()
        return if (usernameFromCache == null) {
            val username = api.fetchUsername()
            cache.saveUsername(username)
            username
        } else {
            usernameFromCache
        }
    }
}
