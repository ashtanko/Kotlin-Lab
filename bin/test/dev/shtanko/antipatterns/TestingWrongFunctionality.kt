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

package dev.shtanko.antipatterns

import io.mockk.mockk
import java.util.UUID
import kotlin.test.assertNotEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private class Counter {
    private var count = 0

    fun increment() {
        count++
    }

    fun decrement() {
        if (count > 0) count--
    }

    fun getCount(): Int = count
}

private interface UserService {
    fun getUser(id: Int): User?
}

private class UserController(val userService: UserService) {
    fun getUser(id: Int): User? {
        return userService.getUser(id) // Delegates to the service
    }
}

class TestingWrongFunctionality {
    @Test
    fun `should check if string is empty`() {
        val str = ""
        assertTrue(str.isEmpty()) // This tests Kotlin, not your code!
    }

    @Test
    fun `testing third-party libraries instead of business logic`() {
        val id1 = UUID.randomUUID().toString()
        val id2 = UUID.randomUUID().toString()

        assertNotEquals(id1, id2) // ❌ This tests the UUID library, not our own logic
    }

    @Test
    fun `testing internal state instead of output`() {
        val counter = Counter()
        counter.increment()

        val field = counter.javaClass.getDeclaredField("count")
        field.isAccessible = true
        val count = field.get(counter) as Int

        assertEquals(1, count) // ❌ Accesses private state instead of public behavior
    }

    @Test
    fun `testing configuration instead of business logic`() {
        val userService = mockk<UserService>()
        val controller = UserController(userService)

        assertNotNull(controller.userService) // ❌ This just tests that DI works, not business logic
    }
}
