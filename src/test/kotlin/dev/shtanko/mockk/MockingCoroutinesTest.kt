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

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class MockingCoroutinesTest {

    private val repository = mockk<UserRepository>()

    @Test
    fun `mocking coroutines example test`() = runTest {
        coEvery { repository.fetchUser() } returns User(1, "Mocked User")

        val user = repository.fetchUser()
        println(user) // Output: User(id=1, name=Mocked User)

        coVerify { repository.fetchUser() }
    }


    private data class User(val id: Int, val name: String)

    private class UserRepository {
        suspend fun fetchUser(): User {
            return User(1, "John Doe")
        }
    }
}
