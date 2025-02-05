/*
 * Copyright 2023 Oleksii Shtanko
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
 * 1282. Group the People Given the Group Size They Belong To
 * @see <a href="https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to">Source</a>
 */
fun interface GroupThePeople {
    operator fun invoke(groupSizes: IntArray): List<List<Int>>
}

class GroupThePeopleGreedy : GroupThePeople {
    override fun invoke(groupSizes: IntArray): List<List<Int>> {
        val ans: MutableList<List<Int>> = ArrayList()

        // A map from group size to the list of indices that are there in the group.
        val szToGroup: MutableMap<Int, MutableList<Int>> = HashMap()
        for (i in groupSizes.indices) {
            if (!szToGroup.containsKey(groupSizes[i])) {
                szToGroup[groupSizes[i]] = ArrayList()
            }
            val group = szToGroup[groupSizes[i]]
            group?.add(i)

            // When the list size equals the group size, empty it and store it in the answer.
            if (group?.size == groupSizes[i]) {
                ans.add(group)
                szToGroup.remove(groupSizes[i])
            }
        }

        return ans
    }
}
