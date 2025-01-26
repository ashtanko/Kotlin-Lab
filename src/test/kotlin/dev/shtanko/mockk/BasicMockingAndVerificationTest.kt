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

package dev.shtanko.mockk

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class BasicMockingAndVerificationTest {

    private val mockRepository = mockk<UserRepository>()
    private val service = UserService(mockRepository)

    @Test
    fun `basic mocking and verification example test`() {
        every { mockRepository.findUserById(1) } returns User(1, "John Doe")
        val user = service.getUser(1)
        println(user) // Output: User(id=1, name=John Doe)
        verify { mockRepository.findUserById(1) }
        confirmVerified(mockRepository)
    }

    private interface UserRepository {
        fun findUserById(id: Int): User?
    }

    private data class User(val id: Int, val name: String)

    private class UserService(val repository: UserRepository) {
        fun getUser(id: Int): User {
            return repository.findUserById(id) ?: throw IllegalArgumentException("User not found")
        }
    }
}
