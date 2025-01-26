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

package dev.shtanko.algorithms.exercises

import java.util.Collections
import java.util.stream.Collectors

/**
 * Implement an algorithm to determine if a string has all unique characters
 * What if you can not use additional data structures?
 */
fun interface UniqueCharacters {
    operator fun invoke(str: String): Boolean
}

class UniqueCharactersSet : UniqueCharacters {
    override fun invoke(str: String): Boolean {
        if (str.isBlank()) return false
        return str.length == str.toSet().size
    }
}

/**
 * Not use additional data structures
 */
class UniqueCharactersSort : UniqueCharacters {
    override fun invoke(str: String): Boolean {
        if (str.isBlank()) return false
        val chars = str.toCharArray().sorted()
        for (i in 0 until chars.size - 1) {
            if (chars[i] != chars[i + 1]) {
                continue
            } else {
                return false
            }
        }
        return true
    }
}

class UniqueCharactersStream : UniqueCharacters {

    override fun invoke(str: String): Boolean {
        if (str.isBlank()) return false

        return str.chars().filter { e ->
            val c = str.chars()
                .boxed()
                .collect(Collectors.toList())
            Collections.frequency(c, e) > 1
        }.count() <= 1
    }
}

class UniqueCharactersBruteForce : UniqueCharacters {
    override fun invoke(str: String): Boolean {
        if (str.isBlank()) return false
        for (i in str.indices) {
            for (j in i + 1 until str.length) {
                if (str[i] == str[j]) {
                    return false
                }
            }
        }
        return true
    }
}
