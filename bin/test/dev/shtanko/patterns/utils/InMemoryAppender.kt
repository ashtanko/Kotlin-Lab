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

package dev.shtanko.patterns.utils

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.AppenderBase
import java.util.LinkedList
import org.slf4j.LoggerFactory

class InMemoryAppender(clazz: Class<*>? = null) : AppenderBase<ILoggingEvent>() {

    private val log: MutableList<ILoggingEvent> = LinkedList()

    val lastMessage: String
        get() = log[log.size - 1].message

    val logSize: Int
        get() = log.size

    fun logContains(message: String): Boolean {
        return log.map {
            it.formattedMessage
        }.contains(message)
    }

    init {
        if (clazz == null) {
            (LoggerFactory.getLogger("root") as Logger).addAppender(this)
        } else {
            (LoggerFactory.getLogger(clazz) as Logger).addAppender(this)
        }

        start()
    }

    override fun append(eventObject: ILoggingEvent) {
        log.add(eventObject)
    }
}
