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

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test

class CapturingArgumentsTest {

    private val repository = mockk<UserRepository>()

    @Test
    fun `capturing arguments example test`() {
        val slot = slot<Int>()
        every { repository.updateUser(capture(slot), any()) } just Runs
        updateUser(User(1, "Alice"), repository)

        println("Captured id: ${slot.captured}") // Output: Captured id: 1
        verify { repository.updateUser(1, "Alice") }
    }

    private data class User(val id: Int, val name: String)

    private fun updateUser(user: User, repository: UserRepository) {
        repository.updateUser(user.id, user.name)
    }

    private interface UserRepository {
        fun updateUser(id: Int, name: String)
    }
}
