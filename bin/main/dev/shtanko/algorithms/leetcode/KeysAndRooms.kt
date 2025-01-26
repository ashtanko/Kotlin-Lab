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

import java.util.Stack

/**
 * 841. Keys and Rooms
 * @see <a href="https://leetcode.com/problems/keys-and-rooms">Source</a>
 */
fun interface KeysAndRooms {
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean
}

class KeysAndRoomsStraightForward : KeysAndRooms {
    override fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        val dfs: Stack<Int> = Stack<Int>().apply {
            add(0)
        }
        val seen: HashSet<Int> = HashSet<Int>().apply {
            add(0)
        }
        while (dfs.isNotEmpty()) {
            val i: Int = dfs.pop()
            for (j in rooms[i]) {
                if (!seen.contains(j)) {
                    dfs.add(j)
                    seen.add(j)
                    if (rooms.size == seen.size) return true
                }
            }
        }
        return rooms.size == seen.size
    }
}

class KeysAndRoomsDFS : KeysAndRooms {
    override fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        val visited = BooleanArray(rooms.size)
        dfs(0, rooms, visited)
        for (visit in visited) if (!visit) return false
        return true
    }

    private fun dfs(u: Int, rooms: List<List<Int>>, visited: BooleanArray) {
        visited[u] = true
        for (v in rooms[u]) if (!visited[v]) dfs(v, rooms, visited)
    }
}

class KeysAndRoomsRecursive : KeysAndRooms {
    override fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        return helper(rooms, 0, HashSet())
    }

    private fun helper(rooms: List<List<Int>>, key: Int, seen: MutableSet<Int>): Boolean {
        seen.add(key)
        val keys = rooms[key]
        for (k in keys) {
            if (!seen.contains(k)) helper(rooms, k, seen)
        }
        return seen.size == rooms.size
    }
}
