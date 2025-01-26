/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.nonlocalbreak

object NonLocalBreak {
    @JvmStatic
    fun main(args: Array<String>) {
        processList(listOf(Element(0), Element(1)))
    }

    /**
     * Kotlin 2.1.0 adds a preview of another long-awaited feature, the ability to use non-local break and continue.
     * This feature expands the toolset you can use in the scope of inline functions and reduces boilerplate
     * code in your project.
     */
    fun processList(elements: List<Element>): Boolean {
        for (element in elements) {
            val variable = element.nullableMethod() ?: run {
                println("Element is null or invalid, continuing...")
                continue
            }
            if (variable == 0) return true // If variable is zero, return true
        }
        return false
    }

    data class Element(val id: Int) {
        fun nullableMethod(): Int? {
            return if (2 + 2 == 5) {
                return null
            } else {
                return 1
            }
        }
    }
}
