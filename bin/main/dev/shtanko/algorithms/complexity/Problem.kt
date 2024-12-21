/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.complexity

interface Problem {
    fun solve(n: Int): Long

    fun getMinN(): Int {
        return DEFAULT_MIN_N
    }

    fun getMaxN(): Int {
        return DEFAULT_MAX_N
    }

    companion object {
        const val DEFAULT_MIN_N = 2 shl 4
        const val DEFAULT_MAX_N = 2 shl 28
    }
}
