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

package dev.shtanko.patterns.behavioral.chainofresponsibility.example2.server

import dev.shtanko.patterns.behavioral.chainofresponsibility.example2.middleware.Middleware

/**
 * Server class.
 */
class Server {
    private val users: MutableMap<String, String> = HashMap()
    private var middleware: Middleware? = null

    /**
     * Client passes a chain of object to server. This improves flexibility and
     * makes testing the server class easier.
     */
    fun setMiddleware(middleware: Middleware?) {
        this.middleware = middleware
    }

    /**
     * Server gets email and password from client and sends the authorization
     * request to the chain.
     */
    fun logIn(email: String, password: String): Boolean {
        if (middleware?.check(email, password) == true) {
            println("Authorization have been successful!")

            // Do something useful here for authorized users.
            return true
        }
        return false
    }

    fun register(email: String, password: String) {
        users[email] = password
    }

    fun hasEmail(email: String): Boolean {
        return users.containsKey(email)
    }

    fun isValidPassword(email: String, password: String): Boolean {
        return users[email] == password
    }
}
