/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.algorithms.dp

import dev.shtanko.algorithms.utils.Dp

@Dp
class FibonacciOptimized {

    private val cache: MutableMap<Int, Long> = HashMap()

    fun perform(n: Int): Long {
        if (n == 0) return 0
        if (n == 1) return 1
        return when {
            cache.containsKey(n) -> cache[n] ?: -1
            else -> {
                cache[n] = perform(n - 1) + perform(n - 2)
                cache[n] ?: -1
            }
        }
    }
}
