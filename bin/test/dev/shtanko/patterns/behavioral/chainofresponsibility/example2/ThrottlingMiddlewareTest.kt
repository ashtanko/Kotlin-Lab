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

import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.middleware.ThrottlingMiddleware
import kotlin.test.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ThrottlingMiddlewareTest {

    private lateinit var middleware: ThrottlingMiddleware

    @BeforeEach
    fun setup() {
        middleware = ThrottlingMiddleware(2)
    }

    @Test
    fun `check returns true when requests are within limit`() {
        assertTrue(middleware.check("email", "password"))
        assertTrue(middleware.check("email", "password"))
    }

    @Test
    fun `check resets request count after a minute`() {
        assertTrue(middleware.check("email", "password"))
        assertTrue(middleware.check("email", "password"))
        Thread.sleep(7)
        assertTrue(middleware.check("email", "password"))
    }
}
