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

import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.middleware.Middleware
import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.server.Server
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

internal class ServerTest {

    private lateinit var server: Server
    private lateinit var middleware: Middleware

    @BeforeEach
    fun setup() {
        server = Server()
        middleware = mock(Middleware::class.java)
        server.setMiddleware(middleware)
    }

    @Test
    fun `logIn returns true when middleware check is successful`() {
        `when`(middleware.check("email", "password")).thenReturn(true)
        assertTrue(server.logIn("email", "password"))
    }

    @Test
    fun `logIn returns false when middleware check is unsuccessful`() {
        `when`(middleware.check("email", "password")).thenReturn(false)
        assertFalse(server.logIn("email", "password"))
    }

    @Test
    fun `hasEmail returns true when email is registered`() {
        server.register("email", "password")
        assertTrue(server.hasEmail("email"))
    }

    @Test
    fun `hasEmail returns false when email is not registered`() {
        assertFalse(server.hasEmail("email"))
    }

    @Test
    fun `isValidPassword returns true when password matches registered email`() {
        server.register("email", "password")
        assertTrue(server.isValidPassword("email", "password"))
    }

    @Test
    fun `isValidPassword returns false when password does not match registered email`() {
        server.register("email", "password")
        assertFalse(server.isValidPassword("email", "wrong_password"))
    }
}
