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
 * Base middleware class.
 */
abstract class Middleware {
    private var next: Middleware? = null

    /**
     * Subclasses will implement this method with concrete checks.
     */
    abstract fun check(email: String, password: String): Boolean

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected fun checkNext(email: String, password: String): Boolean {
        return if (next == null) {
            true
        } else {
            next?.check(email, password) ?: false
        }
    }

    companion object {
        /**
         * Builds chains of middleware objects.
         */
        fun link(first: Middleware, vararg chain: Middleware): Middleware {
            var head = first
            for (nextInChain in chain) {
                head.next = nextInChain
                head = nextInChain
            }
            return first
        }
    }
}
