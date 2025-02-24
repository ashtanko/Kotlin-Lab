/*
 * Copyright 2022 Oleksii Shtanko
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

import dev.shtanko.algorithms.MOD
import dev.shtanko.algorithms.annotations.DFS

/**
 * 1916. Count Ways to Build Rooms in an Ant Colony
 * @see <a href="https://leetcode.com/problems/count-ways-to-build-rooms-in-an-ant-colony/">Source</a>
 */
fun interface WaysToBuildRooms {
    operator fun invoke(prevRoom: IntArray): Int
}

@DFS
class WaysToBuildRoomsDFS : WaysToBuildRooms {
    override operator fun invoke(prevRoom: IntArray): Int {
        val n: Int = prevRoom.size
        val tree: Array<ArrayList<Int>> = Array(n) { ArrayList() }
        for (i in 0 until n) {
            tree[i] = ArrayList()
        }

        // Add the directed edges
        // Here each edge will be directed from room[i] to i. Denoting that we need to build room[i]
        // first before room 'i'.
        for (i in 1 until prevRoom.size) {
            tree[prevRoom[i]].add(i)
        }

        // To store the subtree size for each node.
        val size = IntArray(n)
        dfs(tree, size, 0)

        // Find n factorial
        var nFact: Long = 1
        for (i in 2..n) {
            nFact = nFact * i % MOD
        }

        // Product of all the sizes of subtrees.
        var den: Long = 1
        for (i in 0 until n) {
            den = den * size[i] % MOD
        }
        val d = den.toInt()

        // To divide two number using modulo we find modulo inverse of denominator with mod and then multiply it with
        // the numerator.
        val inverse = modInverse(d)

        return (nFact * inverse % MOD).toInt()
    }

    // To Calculate the size of subtree i.e total number of nodes below the given node in the tree.
    private fun dfs(tree: Array<ArrayList<Int>>, size: IntArray, root: Int): Int {
        var ans = 1
        for (e in tree[root]) {
            ans += dfs(tree, size, e)
        }
        size[root] = ans
        return ans
    }

    private fun modInverse(a: Int): Int {
        return power(a, MOD - 2, MOD)
    }

    private fun power(x: Int, y: Int, m: Int): Int {
        if (y == 0) return 1
        var p = power(x, y / 2, m) % m
        p = (p * p.toLong() % m).toInt()
        return if (y % 2 == 0) p else (x * p.toLong() % m).toInt()
    }
}
