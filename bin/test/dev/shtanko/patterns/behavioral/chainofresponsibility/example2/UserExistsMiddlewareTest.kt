/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.patterns.behavioral.chainofresponsibility.example2

import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.middleware.UserExistsMiddleware
import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.server.Server
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

internal class UserExistsMiddlewareTest {

    private lateinit var server: Server
    private lateinit var middleware: UserExistsMiddleware

    @BeforeEach
    fun setup() {
        server = mock(Server::class.java)
        middleware = UserExistsMiddleware(server)
    }

    @Test
    fun `check returns false when email is not registered`() {
        `when`(server.hasEmail("email")).thenReturn(false)
        assertFalse(middleware.check("email", "password"))
    }

    @Test
    fun `check returns false when password is incorrect`() {
        `when`(server.hasEmail("email")).thenReturn(true)
        `when`(server.isValidPassword("email", "password")).thenReturn(false)
        assertFalse(middleware.check("email", "password"))
    }

    @Test
    fun `check returns true when email is registered and password is correct`() {
        `when`(server.hasEmail("email")).thenReturn(true)
        `when`(server.isValidPassword("email", "password")).thenReturn(true)
        assertTrue(middleware.check("email", "password"))
    }
}
