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

package dev.shtanko.algorithms.leetcode

/**
 * 05. Isomorphic Strings
 * @see <a href="https://leetcode.com/problems/isomorphic-strings">Source</a>
 */
fun interface IsomorphicStrings {
    operator fun invoke(str: String, target: String): Boolean
}

class IsomorphicStringsOneLine : IsomorphicStrings {
    override fun invoke(str: String, target: String): Boolean {
        return str.zip(target).toSet().size.run { equals(str.toSet().size) && equals(target.toSet().size) }
    }
}
