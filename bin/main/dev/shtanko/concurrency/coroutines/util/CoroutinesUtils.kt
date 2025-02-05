/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.concurrency.coroutines.util

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

internal object CoroutinesUtils {

    private const val FORMAT = "%s %s %s"
    private const val PATTERN = "yyyy-MM-dd HH:mm:ss"
    private val currentThreadName = Thread.currentThread().name
    private val currentTime = DateTimeFormatter.ofPattern(PATTERN).withZone(ZoneOffset.UTC).format(Instant.now())

    fun log(time: String, msg: String): String =
        String.format(
            FORMAT,
            time,
            currentThreadName,
            msg,
        )

    fun printLog(time: String = currentTime, msg: String) = println(log(time, msg))
}
