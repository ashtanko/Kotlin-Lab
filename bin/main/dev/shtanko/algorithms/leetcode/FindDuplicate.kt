/*
 * Copyright 2021 Oleksii Shtanko
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

/**
 * Find Duplicate File in System
 * @see <a href="https://leetcode.com/problems/find-duplicate-file-in-system/">Source</a>
 */
fun interface FindDuplicate {
    operator fun invoke(paths: Array<String>): List<List<String>>
}

/**
 * Approach #1 Brute Force
 * Time complexity : O(n*x + f^2*s).
 * Space complexity : O(n*x).
 */
class FindDuplicateBruteForce : FindDuplicate {
    override operator fun invoke(paths: Array<String>): List<List<String>> {
        val list: MutableList<Array<String>> = ArrayList()
        for (path in paths) {
            val values = path.split(" ".toRegex()).toTypedArray()
            for (i in 1 until values.size) {
                val nameCont = values[i].split("\\(".toRegex()).toTypedArray()
                nameCont[1] = nameCont[1].replace(")", "")
                list.add(
                    arrayOf(
                        values[0] + "/" + nameCont[0],
                        nameCont[1],
                    ),
                )
            }
        }
        val visited = BooleanArray(list.size)
        val res: MutableList<List<String>> = ArrayList()
        for (i in 0 until list.size - 1) {
            if (visited[i]) continue
            val l: MutableList<String> = ArrayList()
            for (j in i + 1 until list.size) {
                if (list[i][1] == list[j][1]) {
                    l.add(list[j][0])
                    visited[j] = true
                }
            }
            if (l.isNotEmpty()) {
                l.add(list[i][0])
                res.add(l)
            }
        }
        return res
    }
}

/**
 * Approach #2 Using HashMap
 */
class FindDuplicateHashMap : FindDuplicate {
    override operator fun invoke(paths: Array<String>): List<List<String>> {
        val map = HashMap<String, MutableList<String>>()
        for (path in paths) {
            val values = path.split(" ".toRegex()).toTypedArray()
            for (i in 1 until values.size) {
                val nameCont = values[i].split("\\(".toRegex()).toTypedArray()
                nameCont[1] = nameCont[1].replace(")", "")
                val list = map.getOrDefault(nameCont[1], ArrayList())
                list.add(values[0] + "/" + nameCont[0])
                map[nameCont[1]] = list
            }
        }
        val res: MutableList<List<String>> = ArrayList()
        for (key in map.keys) {
            if (map[key]!!.size > 1) res.add(map[key]!!)
        }
        return res
    }
}
