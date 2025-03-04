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

package dev.shtanko.mockk

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class MockingExceptionsTest {

    private val repository = mockk<UserRepository>()

    @Test
    fun `mocking exceptions example test`() {
        every { repository.deleteUser(1) } returns false

        try {
            deleteUser(1, repository)
        } catch (e: IllegalArgumentException) {
            println(e.message) // Output: Delete failed
        }

        verify { repository.deleteUser(1) }
    }

    private fun deleteUser(id: Int, repository: UserRepository) {
        if (!repository.deleteUser(id)) {
            throw IllegalArgumentException("Delete failed")
        }
    }

    private interface UserRepository {
        fun deleteUser(id: Int): Boolean
    }
}
