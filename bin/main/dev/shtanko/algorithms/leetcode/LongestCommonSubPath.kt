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

import kotlin.math.min

/**
 * 1923. Longest Common Subpath
 * @see <a href="https://leetcode.com/problems/longest-common-subpath">Source</a>
 */
fun interface LongestCommonSubPath {
    operator fun invoke(n: Int, paths: Array<IntArray>): Int
}

class RollingHash : LongestCommonSubPath {

    override operator fun invoke(n: Int, paths: Array<IntArray>): Int {
        var s = 0
        var length = paths[0].size
        for (i in paths.indices) {
            s += paths[i].size
            length = min(length, paths[i].size)
        }
        val merged = IntArray(s) // all path merged into one to calculate a polynomial hash
        val sep = IntArray(paths.size) // positions of path's ends in the "merged" array
        var t = 0
        for (i in paths.indices) {
            sep[i] = t + paths[i].size
            System.arraycopy(paths[i], 0, merged, t, paths[i].size)
            t += paths[i].size
        }
        val hashes = PolyHash(merged)

        // basic binary search on l
        var begin = 0
        var end = length + 1
        while (begin + 1 < end) {
            val mid = (end + begin) / 2
            val c = check(mid, merged, sep, hashes)
            if (c) begin = mid else end = mid
        }
        return begin
    }

    private class PolyHash(x: IntArray) {
        // This class stores polynomial hashes for a given array
        private var b: Long = BASE
        private var q: Long = MODULE
        private var n: Int
        private var pow: LongArray // pow[i] = (b^i) % q
        private var cumulativeHash: LongArray

        init {
            n = x.size
            pow = LongArray(x.size)
            pow[0] = 1
            for (i in 1 until x.size) pow[i] = pow[i - 1] * b % q
            cumulativeHash = LongArray(x.size)
            cumulativeHash[0] = x[0] * pow[0] % q
            for (i in 1 until x.size) cumulativeHash[i] = (cumulativeHash[i - 1] + x[i] * pow[i]) % q
        }

        operator fun get(start: Int, end: Int): Long {
            // returns hash of subarray 'moved' to the end in O(1)
            var v: Long = if (start > 0) {
                Math.floorMod(cumulativeHash[end - 1] - cumulativeHash[start - 1], q)
            } else {
                Math.floorMod(cumulativeHash[end - 1], q)
            }
            // Yes, this may overflow but this is fine
            v = v * pow[n - end] % q
            return v
        }
    }

    private class MySubArray(
        private val array: IntArray,
        private val polyHash: PolyHash,
        private val begin: Int,
        private val end: Int,
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false
            val o = other as MySubArray
            if (end - begin != o.end - o.begin) return false
            for (i in 0 until end - begin) {
                if (array[i + begin] != o.array[i + o.begin]) return false
                if (i > 10) return true // This is a horrible optimization.
                // It means there is ~1/(n^10 * (collision chance)) probability to  make a wrong answer
                // But in return the equals() works in O(1)
            }
            return true
        }

        override fun hashCode(): Int {
            return polyHash[begin, end].toInt()
        }
    }

    private class MySubArrayFactory(
        // This makes check() more readable... in theory
        private val l: IntArray,
        private val polyHash: PolyHash,
    ) {
        operator fun get(begin: Int, end: Int): MySubArray {
            return MySubArray(l, polyHash, begin, end)
        }
    }

    private fun check(l: Int, merged: IntArray, sep: IntArray, hashes: PolyHash): Boolean {
        // Checks if there is a common subpath of a length "l"
        if (l == 0) return true
        val path = MySubArrayFactory(merged, hashes) // <-- generates hashable subpaths
        var found: MutableSet<MySubArray?> = HashSet()
        // add all subpaths of the 1st friend of length "l" in "found"
        for (j in 0..sep[0] - l) {
            found.add(path[j, j + l])
        }
        for (i in 0 until sep.size - 1) {
            // remove subpaths from "found" that are not subpaths of the ith friend
            val res: MutableSet<MySubArray?> = HashSet()
            for (j in sep[i]..sep[i + 1] - l) {
                if (found.contains(path[j, j + l])) res.add(path[j, j + l])
            }
            found = res
        }
        // if there is a subpath that we didn't remove => then this path is present in all the paths
        return found.isNotEmpty()
    }

    companion object {
        private const val BASE = 100001L
        private const val MODULE = 1000000019L
    }
}
