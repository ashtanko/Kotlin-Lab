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

package dev.shtanko.kotlinlang.functions.inline

import org.junit.jupiter.api.Test

class InlineTest {
    @Test
    fun `doOnDebug test`() {
        println("START main()")
        doOnDebug(::println, 10) {
            if (!Env.UTF8_SUPPORT) {
                return@doOnDebug
            }
            println("I'm on debug ¯\\_(ツ)_/¯")
        }
        println("END main()")
    }
}
