/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.patterns.solid.isp.example1

import java.io.ByteArrayOutputStream

// Interface for workers who perform work-related tasks
interface Worker {
    fun work()
}

// Interface for entities that eat or consume food
interface Eater {
    fun eat()
}

// Human class that implements both Worker and Eater interfaces

class Human : Worker, Eater {
    private val outputStream = ByteArrayOutputStream()

    override fun work() {
        outputStream.write("Human is working\n".toByteArray())
    }

    override fun eat() {
        outputStream.write("Human is eating\n".toByteArray())
    }

    fun getOutput(): String {
        return outputStream.toString()
    }
}

// Robot class that implements only the Worker interface
class Robot : Worker {
    private val outputStream = ByteArrayOutputStream()

    override fun work() {
        outputStream.write("Robot is working\n".toByteArray())
    }

    fun getOutput(): String {
        return outputStream.toString()
    }
}