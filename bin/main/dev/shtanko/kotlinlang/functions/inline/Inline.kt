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

import kotlin.random.Random
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.jvm.isAccessible

object Env {
    const val DEBUG = true
    val UTF8_SUPPORT: Boolean get() = Random.nextInt(5) != 0
}

inline fun <reified T> T.callPrivateFunc(name: String, vararg args: Any?): Any? =
    T::class
        .declaredMemberFunctions
        .firstOrNull { it.name == name }
        ?.apply { isAccessible = true }
        ?.call(this, *args)

inline fun doOnDebug(
    noinline logger: (String) -> Unit,
    times: Int = 1,
    crossinline block: () -> Unit,
) {
    if (Env.DEBUG) {
        logger("[LOG] Running doOnDebug...")
        repeat(times) {
            logger("[LOG] Iteration #$it...")
            block()
        }
        logger("[LOG] Flushing the log...")
        flush(logger)
    }
}

fun flush(logger: (String) -> Unit) {
    logger("[LOG] Flushing the log..")
}

fun main() {
    println("START main()")
    doOnDebug(::println) {
        if (!Env.UTF8_SUPPORT) {
            return@doOnDebug
        }
        println("I'm on debug ¯\\_(ツ)_/¯")
    }
    println("END main()")
}
