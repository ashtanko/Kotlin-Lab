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

/**
 * 1366. Rank Teams by Votes
 * @see <a href="https://leetcode.com/problems/rank-teams-by-votes/">Source</a>
 */
fun interface RankTeams {
    operator fun invoke(votes: Array<String>): String
}

data class VoteRecord(
    val team: String,
    val teamCount: Int,
) {
    val ranks = IntArray(teamCount)
}

class RankTeamsImpl : RankTeams {
    override operator fun invoke(votes: Array<String>): String {
        if (votes.size == 1) {
            return votes.first()
        }

        val map = HashMap<String, VoteRecord>()
        for (vote in votes) {
            for (i in vote.indices) {
                val team = vote[i].toString()
                val record = map.getOrDefault(team, VoteRecord(team, vote.length))
                record.ranks[i]++
                map[team] = record
            }
        }

        val list = ArrayList(map.values)
        list.sortWith { o1, o2 ->
            var idx = 0
            while (idx < o1.ranks.size && idx < o2.ranks.size) {
                if (o1.ranks[idx] == o2.ranks[idx]) {
                    idx++
                } else {
                    return@sortWith o2.ranks[idx] - o1.ranks[idx]
                }
            }
            return@sortWith o1.team.compareTo(o2.team)
        }

        val sb = StringBuilder()
        for (voteRecord in list) {
            sb.append(voteRecord.team)
        }

        return sb.toString()
    }
}
