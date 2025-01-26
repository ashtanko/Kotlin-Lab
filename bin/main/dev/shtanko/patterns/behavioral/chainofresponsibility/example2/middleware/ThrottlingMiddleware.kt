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

package dev.shtanko.patterns.behavioral.chainofresponsibility.example2.middleware

/**
 * ConcreteHandler. Checks whether there are too many failed login requests.
 */
class ThrottlingMiddleware(private val requestPerMinute: Int) : Middleware() {
    private var request = 0
    private var currentTime: Long

    init {
        currentTime = System.currentTimeMillis()
    }

    /**
     * Please, not that checkNext() call can be inserted both in the beginning
     * of this method and in the end.
     *
     * This gives much more flexibility than a simple loop over all middleware
     * objects. For instance, an element of a chain can change the order of
     * checks by running its check after all other checks.
     */
    override fun check(email: String, password: String): Boolean {
        if (System.currentTimeMillis() > currentTime + THROTTLE) {
            request = 0
            currentTime = System.currentTimeMillis()
        }
        request++
        if (request > requestPerMinute) {
            println("Request limit exceeded!")
            Thread.currentThread().interrupt()
        }
        return checkNext(email, password)
    }

    companion object {
        private const val THROTTLE = 6
    }
}
