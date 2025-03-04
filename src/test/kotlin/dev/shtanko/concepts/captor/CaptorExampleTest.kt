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

package dev.shtanko.concepts.captor

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify

class CaptorExampleTest {

    @Test
    fun exampleWithCaptor() {
        val userService = mock<UserService>()
        val captor = argumentCaptor<String>()

        userService.addUser("John Doe")
        verify(userService).addUser(captor.capture())
        println(captor.firstValue) // Output: John Doe
    }

    private class UserService {
        fun addUser(name: String) {
            // Some logic
            println(name)
        }
    }
}
