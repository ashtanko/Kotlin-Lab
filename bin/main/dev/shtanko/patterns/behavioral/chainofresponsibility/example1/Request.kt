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

package dev.shtanko.patterns.behavioral.chainofresponsibility.example1

class Request(private val requestType: RequestType, private val requestDescription: String) {

    /**
     * Indicates if the request is handled or not. A request can only switch state from unhandled to
     * handled, there's no way to 'unhandle' a request.
     */
    private var handled = false

    /**
     * Mark the request as handled.
     */
    fun markHandled() {
        handled = true
    }

    /**
     * Get the type of this request, used by each person in the chain of command to see if they should
     * or can handle this particular request.
     *
     * @return The request type
     */
    fun getRequestType(): RequestType {
        return requestType
    }

    /**
     * Indicates if this request is handled or not.
     *
     * @return <tt>true</tt> when the request is handled, <tt>false</tt> if not
     */
    fun isHandled(): Boolean {
        return handled
    }

    override fun toString() = requestDescription
}
