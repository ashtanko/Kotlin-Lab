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

package dev.shtanko.concepts.fake

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FakeExampleTest {
    @Test
    fun `should return user when found`() {
        // Arrange
        val inMemoryUserRepository = object : UserRepository {
            private val users = mapOf(1L to User(1L, "John Doe"))

            override fun findById(id: Long): User? {
                return users[id]
            }
        }
        val userService = UserService(inMemoryUserRepository)

        // Act
        val user = userService.getUserById(1L)

        // Assert
        assertEquals(User(1L, "John Doe"), user)
    }

    @Test
    fun `should return null when user not found`() {
        // Arrange
        val inMemoryUserRepository = object : UserRepository {
            override fun findById(id: Long): User? {
                return null
            }
        }
        val userService = UserService(inMemoryUserRepository)

        // Act
        val user = userService.getUserById(2L)

        // Assert
        assertEquals(null, user)
    }
}

private interface UserRepository {
    fun findById(id: Long): User?
}

private data class User(val id: Long, val name: String)

private class UserService(private val userRepository: UserRepository) {
    fun getUserById(id: Long): User? {
        return userRepository.findById(id)
    }
}
