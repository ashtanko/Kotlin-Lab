/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.collections.processing

@Suppress("MagicNumber")
fun main() {
    val nums = 1..4
    val chars = 'A'..'F'
    println(nums.zip(chars))

    val winner = listOf("Ashley", "Barbara", "Cyprian", "David")
    val prizes = listOf(5000, 3000, 1000)
    val zipped = winner.zip(prizes)
    println(zipped)
    // [(Ashley, 5000), (Barbara, 3000), (Cyprian, 1000)]
    zipped.forEach { (person, price) ->
        println("$person won $price")
    }
    // Ashley won 5000
    // Barbara won 3000
    // Cyprian won 1000
}

private infix fun <T, R> Iterable<T>.zip(other: Iterable<R>): List<Pair<T, R>> {
    return zip(other) { t1, t2 -> t1 to t2 }
}

private fun <T> Iterable<T>.collectionSizeOrDefault(default: Int): Int =
    if (this is Collection<*>) this.size else default

private inline fun <T, R, V> Iterable<T>.zip(other: Iterable<R>, transform: (a: T, b: R) -> V): List<V> {
    val first = iterator()
    val second = other.iterator()
    val list = ArrayList<V>(minOf(collectionSizeOrDefault(10), other.collectionSizeOrDefault(10)))
    while (first.hasNext() && second.hasNext()) {
        list.add(transform(first.next(), second.next()))
    }
    return list
}
