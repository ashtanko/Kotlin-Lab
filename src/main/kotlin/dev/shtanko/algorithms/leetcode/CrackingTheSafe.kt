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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Hard
import kotlin.math.pow

/**
 * 753. Cracking the Safe
 * @see <a href="https://leetcode.com/problems/cracking-the-safe/">Cracking the Safe</a>
 */
@Hard("https://leetcode.com/problems/cracking-the-safe")
fun interface CrackingSafe {
    operator fun invoke(n: Int, k: Int): String
}

class CrackingSafeHierholzersAlgorithm : CrackingSafe {

    private val visitedNodes: MutableSet<String> = HashSet()
    private val result: StringBuilder = StringBuilder()

    override operator fun invoke(n: Int, k: Int): String {
        if (n == 1 && k == 1) {
            return "0"
        }
        val initialString = StringBuilder()
        for (i in 0 until n - 1) {
            initialString.append("0")
        }
        val startNode = initialString.toString()

        depthFirstSearch(startNode, k)
        result.append(startNode)
        return result.toString()
    }

    private fun depthFirstSearch(node: String, k: Int) {
        for (x in 0 until k) {
            val neighbor = node + x
            if (!visitedNodes.contains(neighbor)) {
                visitedNodes.add(neighbor)
                depthFirstSearch(neighbor.substring(1), k)
                result.append(x)
            }
        }
    }
}

class CrackingSafeInverseBurrowsWheelerTransform : CrackingSafe {
    override operator fun invoke(n: Int, k: Int): String {
        val m = k.toDouble().pow((n - 1).toDouble()).toInt()
        val permutations = IntArray(m * k)
        for (i in 0 until k) {
            for (q in 0 until m) {
                permutations[i * m + q] = q * k + i
            }
        }

        val result = StringBuilder()
        for (i in 0 until m * k) {
            var j = i
            while (permutations[j] >= 0) {
                result.append((j / m).toString())
                val value = permutations[j]
                permutations[j] = -1
                j = value
            }
        }

        for (i in 0 until n - 1) {
            result.append("0")
        }
        return result.toString()
    }
}
