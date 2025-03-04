/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import java.util.LinkedList
import java.util.Queue
import kotlin.math.min

/**
 * 269. Alien Dictionary
 * @see <a href="https://leetcode.com/problems/alien-dictionary">Source</a>
 */
fun interface AlienDictionary {
    operator fun invoke(words: Array<String>): String
}

/**
 * Approach 1: Breadth-First Search
 * Time complexity : O(C).
 */
@BFS
class AlienDictionaryBFS : AlienDictionary {
    override fun invoke(words: Array<String>): String {
        val adjList: MutableMap<Char, MutableList<Char>> = HashMap()
        val counts: MutableMap<Char, Int> = HashMap()

        initializeDataStructures(words, adjList, counts)
        if (!findEdges(words, adjList, counts)) {
            return ""
        }

        return breadthFirstSearch(adjList, counts)
    }

    private fun initializeDataStructures(
        words: Array<String>,
        adjList: MutableMap<Char, MutableList<Char>>,
        counts: MutableMap<Char, Int>,
    ) {
        for (word in words) {
            for (c in word.toCharArray()) {
                counts[c] = 0
                adjList[c] = ArrayList()
            }
        }
    }

    private fun findEdges(
        words: Array<String>,
        adjList: MutableMap<Char, MutableList<Char>>,
        counts: MutableMap<Char, Int>,
    ): Boolean {
        for (i in 0 until words.size - 1) {
            val word1 = words[i]
            val word2 = words[i + 1]

            if (word1.length > word2.length && word1.startsWith(word2)) {
                return false
            }

            for (j in 0 until min(word1.length, word2.length)) {
                if (word1[j] != word2[j]) {
                    adjList[word1[j]]?.add(word2[j])
                    counts[word2[j]] = counts.getOrDefault(word2[j], 0).plus(1)
                    break
                }
            }
        }
        return true
    }

    private fun breadthFirstSearch(
        adjList: Map<Char, MutableList<Char>>,
        counts: MutableMap<Char, Int>,
    ): String {
        val sb = StringBuilder()
        val queue: Queue<Char> = LinkedList()

        for (c in counts.keys) {
            if (counts[c] == 0) {
                queue.add(c)
            }
        }

        while (queue.isNotEmpty()) {
            val c: Char = queue.remove()
            sb.append(c)
            for (next in adjList.getOrDefault(c, emptyList())) {
                counts[next] = counts.getOrDefault(next, 0) - 1
                if (counts[next] == 0) {
                    queue.add(next)
                }
            }
        }

        return if (sb.length < counts.size) {
            ""
        } else {
            sb.toString()
        }
    }
}

/**
 * Approach 2: Depth-First Search
 * Time complexity : O(C).
 */
@DFS
class AlienDictionaryDFS : AlienDictionary {

    override fun invoke(words: Array<String>): String {
        val adj = Array(ALPHABET_LETTERS_COUNT) { BooleanArray(ALPHABET_LETTERS_COUNT) }
        val visited = IntArray(ALPHABET_LETTERS_COUNT) { -1 }
        buildGraph(words, adj, visited)

        val sb = StringBuilder()
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            // unvisited
            if (visited[i] == 0 && !dfs(adj, visited, sb, i)) {
                return ""
            }
        }
        return sb.reverse().toString()
    }

    /**
     * Depth-first search (DFS) algorithm for traversing a graph.
     *
     * This method performs a depth-first search traversal starting from the given node `i`
     * in the graph represented by the adjacent matrix `adj`.
     *
     * @param adj The adjacency matrix representing the connections between graph nodes.
     * @param visited The array representing the visited status of graph nodes.
     * @param sb The `StringBuilder` used to store the visited nodes in the DFS traversal order.
     * @param i The index of the current node being visited.
     * @return `true` if the DFS traversal is successful, `false` if a cycle is detected.
     */
    @DFS
    private fun dfs(adj: Array<BooleanArray>, visited: IntArray, sb: StringBuilder, i: Int): Boolean {
        visited[i] = 1 // 1 = visiting
        for (j in 0 until ALPHABET_LETTERS_COUNT) {
            if (adj[i][j]) {
                if (visited[j] == 1) return false
                if (visited[j] == 0 && !dfs(adj, visited, sb, j)) {
                    return false
                }
            }
        }
        visited[i] = 2 // 2 = visited
        sb.append((i + 'a'.code).toChar())
        return true
    }

    /**
     * Builds a graph based on the given array of words, assigning adjacency values and visited status to the
     * graph nodes.
     *
     * @param words The array of words representing the graph nodes.
     * @param adj The adjacency matrix representing the connections between graph nodes.
     * @param visited The array representing the visited status of graph nodes.
     */
    private fun buildGraph(words: Array<String>, adj: Array<BooleanArray>, visited: IntArray) {
        if (words.isEmpty()) return
        var pre = words[0].toCharArray()
        for (k in pre.indices) visited[pre[k] - 'a'] = 0
        for (i in 1 until words.size) {
            val cur = words[i].toCharArray()
            for (k in cur.indices) visited[cur[k] - 'a'] = 0
            val length = min(pre.size, cur.size)
            for (j in 0 until length) {
                if (cur[j] != pre[j]) {
                    adj[pre[j] - 'a'][cur[j] - 'a'] = true
                    break
                }
            }
            pre = cur
        }
    }
}
