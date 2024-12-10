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

import dev.shtanko.extensions.swap
import java.util.LinkedList
import java.util.Queue

/**
 * 839. Similar String Groups
 * @see <a href="https://leetcode.com/problems/similar-string-groups">Source</a>
 */
fun interface SimilarStringGroups {
    operator fun invoke(strings: Array<String>): Int
}

private fun numSimilarGroupsCount(
    strings: Array<String>,
    strategy: (n: Int, adj: Map<Int, MutableList<Int>>, visit: BooleanArray) -> Unit,
): Int = run {
    fun isSimilar(a: String, b: String): Boolean {
        var diff = 0
        for (i in a.indices) {
            if (a[i] != b[i]) {
                diff++
            }
        }
        return diff == 0 || diff == 2
    }

    val n = strings.size
    val adj: MutableMap<Int, MutableList<Int>> = HashMap()
    // Form the required graph from the given strings array.
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if (isSimilar(strings[i], strings[j])) {
                adj.computeIfAbsent(i) { ArrayList() }.add(j)
                adj.computeIfAbsent(j) { ArrayList() }.add(i)
            }
        }
    }
    val visit = BooleanArray(n)
    var count = 0
    // Count the number of connected components.
    for (i in 0 until n) {
        if (!visit[i]) {
            strategy(i, adj, visit)
            count++
        }
    }
    return count
}

/**
 * Approach 1: Depth First Search
 */
class SimilarStringGroupsBFS : SimilarStringGroups {

    override fun invoke(strings: Array<String>): Int {
        return numSimilarGroupsCount(strings, ::bfs)
    }

    private fun bfs(n: Int, adj: Map<Int, MutableList<Int>>, visit: BooleanArray) {
        var node = n
        val queue: Queue<Int> = LinkedList()
        queue.offer(node)
        visit[node] = true
        while (queue.isNotEmpty()) {
            node = queue.poll()
            if (!adj.containsKey(node)) {
                continue
            }
            for (neighbor in adj.getOrDefault(node, emptyList())) {
                if (!visit[neighbor]) {
                    visit[neighbor] = true
                    queue.offer(neighbor)
                }
            }
        }
    }
}

/**
 * Approach 2: Breadth First Search
 */
class SimilarStringGroupsDFS : SimilarStringGroups {
    override fun invoke(strings: Array<String>): Int {
        return numSimilarGroupsCount(strings, ::dfs)
    }

    private fun dfs(node: Int, adj: Map<Int, MutableList<Int>>, visit: BooleanArray) {
        visit[node] = true
        if (!adj.containsKey(node)) {
            return
        }
        for (neighbor in adj.getOrDefault(node, emptyList())) {
            if (!visit[neighbor]) {
                visit[neighbor] = true
                dfs(neighbor, adj, visit)
            }
        }
    }
}

/**
 * Approach 3: Union-find
 */
class SimilarStringGroupsUnionFind : SimilarStringGroups {
    override fun invoke(strings: Array<String>): Int {
        val numStrings: Int = strings.size
        val disjointSet = UnionFind(numStrings)
        var count = numStrings
        // Form the required graph from the given strings array.
        for (currentIndex in 0 until numStrings) {
            for (comparisonIndex in currentIndex + 1 until numStrings) {
                if (isSimilar(
                        strings[currentIndex],
                        strings[comparisonIndex],
                    ) && disjointSet.find(currentIndex) != disjointSet.find(comparisonIndex)
                ) {
                    count--
                    disjointSet.unionSet(currentIndex, comparisonIndex)
                }
            }
        }

        return count
    }

    private fun isSimilar(stringA: String, stringB: String): Boolean {
        var differenceCount = 0
        for (i in stringA.indices) {
            if (stringA[i] != stringB[i]) {
                differenceCount++
            }
        }
        return differenceCount == 0 || differenceCount == 2
    }
}

/**
 * Approach 4: Union-find 2
 */
class SimilarStringGroupsDSU : SimilarStringGroups {
    override fun invoke(strings: Array<String>): Int {
        if (strings.isEmpty()) {
            return 0
        }
        val numStrings: Int = strings.size
        val stringLength: Int = strings[0].length
        val disjointSet = DSU(numStrings)

        if (numStrings < stringLength * stringLength) { // If few words, then check for pairwise similarity: O(N^2 W)
            for (i in 0 until numStrings) for (j in i + 1 until numStrings) if (similar(
                    strings[i],
                    strings[j],
                )
            ) {
                disjointSet.union(i, j)
            }
        } else { // If short words, check all neighbors: O(N W^3)
            val buckets: MutableMap<String, MutableList<Int>> = HashMap()
            for (i in 0 until numStrings) {
                val charArray: CharArray = strings[i].toCharArray()
                for (j0 in charArray.indices) {
                    for (j1 in j0 + 1 until charArray.size) {
                        charArray.swap(j0, j1)
                        val stringBuilder = StringBuilder()
                        for (c in charArray) stringBuilder.append(c)
                        buckets.computeIfAbsent(stringBuilder.toString()) { ArrayList() }.add(i)
                        charArray.swap(j0, j1)
                    }
                }
            }
            for (currentIndex in strings.indices) {
                if (buckets.containsKey(strings[currentIndex])) {
                    for (bucketIndex in buckets.getOrDefault(strings[currentIndex], emptyList())) {
                        disjointSet.union(currentIndex, bucketIndex)
                    }
                }
            }
        }

        var numGroups = 0
        for (i in 0 until numStrings) if (disjointSet.parent[i] == i) numGroups++

        return numGroups
    }

    private fun similar(word1: String, word2: String): Boolean {
        var diff = 0
        for (i in word1.indices) if (word1[i] != word2[i]) diff++
        return diff <= 2
    }

    class DSU(numStrings: Int) {

        var parent: IntArray = IntArray(numStrings)

        init {
            for (i in 0 until numStrings) parent[i] = i
        }

        fun find(x: Int): Int {
            if (parent[x] != x) parent[x] = find(parent[x])
            return parent[x]
        }

        fun union(x: Int, y: Int) {
            parent[find(x)] = find(y)
        }
    }
}
