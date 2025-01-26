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

import java.util.PriorityQueue
import kotlin.math.min

fun interface SuperUglyNumber {
    operator fun invoke(n: Int, primes: IntArray): Int
}

class SuperUglyNumberCommon : SuperUglyNumber {
    override operator fun invoke(n: Int, primes: IntArray): Int {
        val ugly = IntArray(n)
        val idx = IntArray(primes.size)

        ugly[0] = 1
        for (i in 1 until n) {
            // find next
            ugly[i] = Int.MAX_VALUE
            for (j in primes.indices) ugly[i] = min(ugly[i], primes[j] * ugly[idx[j]])

            // slip duplicate
            for (j in primes.indices) {
                while (primes[j] * ugly[idx[j]] <= ugly[i]) idx[j]++
            }
        }

        return ugly[n - 1]
    }
}

class SuperUglyNumberRedundantMultiplication : SuperUglyNumber {
    override operator fun invoke(n: Int, primes: IntArray): Int {
        val ugly = IntArray(n)
        val idx = IntArray(primes.size)
        val values = IntArray(primes.size) { 1 }

        var next = 1
        for (i in 0 until n) {
            ugly[i] = next
            next = Int.MAX_VALUE
            for (j in primes.indices) {
                // skip duplicate and avoid extra multiplication
                if (values[j] == ugly[i]) values[j] = ugly[idx[j]++] * primes[j]
                // find next ugly number
                next = min(next, values[j])
            }
        }

        return ugly[n - 1]
    }
}

class SuperUglyNumberHeap : SuperUglyNumber {
    override operator fun invoke(n: Int, primes: IntArray): Int {
        val ugly = IntArray(n)

        val pq: PriorityQueue<Num> = PriorityQueue()
        for (i in primes.indices) pq.add(Num(primes[i], 1, primes[i]))
        ugly[0] = 1

        for (i in 1 until n) {
            ugly[i] = pq.peek().value
            while (pq.peek().value == ugly[i]) {
                val nxt: Num = pq.poll()
                pq.add(Num(nxt.p * ugly[nxt.idx], nxt.idx + 1, nxt.p))
            }
        }

        return ugly[n - 1]
    }

    class Num(var value: Int, var idx: Int, var p: Int) : Comparable<Num?> {
        override fun compareTo(other: Num?): Int {
            return value - other?.value!!
        }
    }
}
