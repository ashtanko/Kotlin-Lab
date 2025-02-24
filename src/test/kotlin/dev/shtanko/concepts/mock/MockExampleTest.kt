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

package dev.shtanko.concepts.mock

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MockExampleTest {
    @Test
    fun `getUser returns user when found`() {
        // Arrange
        val userRepository = mock(UserRepository::class.java)
        val userService = UserService(userRepository)

        val expectedUser = User(1, "John Doe", "john@example.com")

        // Act
        whenever(userRepository.findById(any())).thenReturn(expectedUser)

        val result = userService.getUser(1)

        // Assert
        assertEquals(expectedUser, result)
        verify(userRepository).findById(eq(1))
    }
}

private interface UserRepository {
    fun findById(id: Long): User?
}

private class UserService(private val userRepository: UserRepository) {
    fun getUser(id: Long): User? {
        return userRepository.findById(id)
    }
}

private data class User(val id: Long, val name: String, val email: String)
