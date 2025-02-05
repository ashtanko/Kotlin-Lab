/*
 * Copyright 2022 Oleksii Shtanko
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
import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.middleware.RoleCheckMiddleware
import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.middleware.ThrottlingMiddleware
import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.middleware.UserExistsMiddleware
import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.server.Server
import java.io.BufferedReader
import java.io.InputStreamReader

object Demo {
    private val reader = BufferedReader(InputStreamReader(System.`in`))
    private var server: Server = Server().apply {
        register("admin@example.com", "admin_pass")
        register("user@example.com", "user_pass")
    }

    init {
        // All checks are linked. Client can build various chains using the same
        // components.
        val middleware = Middleware.link(
            ThrottlingMiddleware(2),
            UserExistsMiddleware(server),
            RoleCheckMiddleware(),
        )

        // Server gets a chain from client code.
        server.setMiddleware(middleware)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var success: Boolean
        do {
            print("Enter email: ")
            val email = reader.readLine()
            print("Input password: ")
            val password = reader.readLine()
            success = server.logIn(email, password)
        } while (!success)
    }
}
