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

fun interface AbstractWordLadder2Strategy {
    operator fun invoke(beginWord: String, endWord: String, wordList: List<String>): List<List<String>>
}

class WordLadder2 : AbstractWordLadder2Strategy {

    override operator fun invoke(beginWord: String, endWord: String, wordList: List<String>): List<List<String>> {
        val dict = wordList.toHashSet()
        val res: MutableList<List<String>> = ArrayList()

        if (!dict.contains(endWord)) {
            return res
        }

        val map: Map<String, List<String>> = getChildren(beginWord, endWord, dict)
        val path: MutableList<String> = ArrayList()
        path.add(beginWord)
        findLadders(beginWord, endWord, map, res, path)
        return res
    }

    private fun findLadders(
        beginWord: String,
        endWord: String,
        map: Map<String, List<String>>,
        res: MutableList<List<String>>,
        path: MutableList<String>,
    ) {
        if (beginWord == endWord) {
            res.add(ArrayList(path))
        }
        if (!map.containsKey(beginWord)) {
            return
        }
        for (next in map[beginWord] ?: error("svw")) {
            path.add(next)
            findLadders(next, endWord, map, res, path)
            path.removeAt(path.size - 1)
        }
    }

    private fun getChildren(beginWord: String, endWord: String, dict: Set<String>): Map<String, List<String>> {
        val map: MutableMap<String, MutableList<String>> = HashMap()
        var start: MutableSet<String> = HashSet()
        start.add(beginWord)
        var end: MutableSet<String> = HashSet()
        val visited: MutableSet<String> = HashSet()
        end.add(endWord)
        var isFound = false
        var isBackward = false
        while (start.isNotEmpty() && !isFound) {
            if (start.size > end.size) {
                val temp: MutableSet<String> = start
                start = end
                end = temp
                isBackward = !isBackward
            }
            val set: MutableSet<String> = HashSet()
            for (cur in start) {
                visited.add(cur)

                for (next in getNext(cur, dict)) {
                    if (visited.contains(next) || start.contains(next)) {
                        continue
                    }
                    if (end.contains(next)) {
                        isFound = true
                    }
                    set.add(next)
                    val parent = if (isBackward) next else cur
                    val child = if (isBackward) cur else next
                    if (!map.containsKey(parent)) {
                        map[parent] = ArrayList()
                    }
                    map[parent]?.add(child)
                }
            }
            start = set
        }
        return map
    }

    private fun getNext(cur: String, dict: Set<String>): List<String> {
        val res: MutableList<String> = ArrayList()
        val chars = cur.toCharArray()
        for (i in chars.indices) {
            val old = chars[i]
            for (c in 'a'..'z') {
                if (c == old) {
                    continue
                }
                chars[i] = c
                val next = String(chars)
                if (dict.contains(next)) {
                    res.add(next)
                }
            }
            chars[i] = old
        }
        return res
    }
}
