/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.patterns.solid.srp.example1

class Logger(private val logLevel: LogLevel) {
    fun log(message: String) {
        when (logLevel) {
            LogLevel.INFO -> println("[INFO] $message")
            LogLevel.WARNING -> println("[WARNING] $message")
            LogLevel.ERROR -> println("[ERROR] $message")
        }
    }
}

enum class LogLevel {
    INFO,
    WARNING,
    ERROR,
}

data class User(val username: String, val password: String)

class AuthenticationManager(private val userRepository: UserRepository, private val logger: Logger) {
    fun authenticate(username: String, password: String): Boolean {
        val user = userRepository.findUserByUsername(username)

        if (user != null && user.password == password) {
            logger.log("User '$username' successfully authenticated.")
            return true
        }

        logger.log("Failed authentication attempt for user '$username'.")
        return false
    }
}

class UserRepository(private val users: List<User>) {
    fun findUserByUsername(username: String): User? {
        return users.find { it.username == username }
    }
}
