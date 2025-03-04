/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.extensions

fun <T> Array<T>.swap(i: Int, j: Int) {
    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}

fun IntArray.swap(i: Int, j: Int) {
    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}

fun <T> Array<T>.reverse() {
    val n = this.size
    for (i in 0 until n / 2)
        this.swap(i, n - 1 - i)
}

fun <T> Array<T>.reverse2() {
    var i = 0
    var j = size - 1
    while (i < j) {
        swap(i, j)
        i++
        j--
    }
}

fun <T> Array<T>.flip(left: Int, right: Int) {
    var l = left
    var r = right
    while (l <= r) {
        swap(l++, r--)
    }
}

fun CharArray.swap(i: Int, j: Int) {
    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}

fun IntArray.second(): Int {
    if (isEmpty()) {
        throw NoSuchElementException("Array is empty.")
    }
    return this[1]
}
