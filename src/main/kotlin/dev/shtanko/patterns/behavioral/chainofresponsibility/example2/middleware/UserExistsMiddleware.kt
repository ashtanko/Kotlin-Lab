/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.patterns.behavioral.chainofresponsibility.example2.middleware

import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.server.Server

/**
 * ConcreteHandler. Checks whether a user with the given credentials exists.
 */
class UserExistsMiddleware(server: Server) : Middleware() {
    private val server: Server

    init {
        this.server = server
    }

    override fun check(email: String, password: String): Boolean {
        if (!server.hasEmail(email)) {
            println("This email is not registered!")
            return false
        }
        if (!server.isValidPassword(email, password)) {
            println("Wrong password!")
            return false
        }
        return checkNext(email, password)
    }
}
