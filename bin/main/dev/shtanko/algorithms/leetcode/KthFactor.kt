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

fun kthFactor(n: Int, k: Int): Int {
    var cnt = 0
    val list: MutableList<Int> = ArrayList()
    var i = 1
    while (i * i <= n) {
        if (n % i == 0) {
            if (i * i != n) list.add(n / i)
            if (++cnt == k) return i
        }
        i++
    }

    return if (list.size + cnt < k) -1 else list[list.size - (k - cnt)]
}
