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

package dev.shtanko.mockito

import java.util.UUID
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class KotlinMockitoStaticExampleTest {

    private lateinit var repository: Repository
    private val service: Service = mock()

    @BeforeEach
    fun setUp() {
        repository = Repository(service)
    }

    @Test
    fun `WHEN changeNickname THEN send correct params to backend`() = runTest {
        Mockito.mockStatic(UUID::class.java).use { mockedUUID ->
            val expectedNickname = "JohnDoe"
            val uuid = mock<UUID>()
            whenever(uuid.toString()).thenReturn("uuid")
            mockedUUID.`when`<Any> { UUID.randomUUID() }.thenReturn(uuid)

            repository.changeNickname(expectedNickname)

            verify(service).changeNickname(
                nickname = expectedNickname,
                uuid = "uuid",
            )
        }
    }

    private class Repository(private val service: Service) {
        suspend fun changeNickname(newNickname: String) {
            service.changeNickname(newNickname, UUID.randomUUID().toString())
        }
    }

    private class Service {
        fun changeNickname(nickname: String, uuid: String) {
            println("$nickname $uuid")
        }
    }
}
