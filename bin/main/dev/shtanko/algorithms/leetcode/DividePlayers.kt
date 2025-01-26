/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2491. Divide Players Into Teams of Equal Skill
 * @see <a href="https://leetcode.com/problems/divide-players-into-teams-of-equal-skill">Source</a>
 */
@Medium("https://leetcode.com/problems/divide-players-into-teams-of-equal-skill")
fun interface DividePlayers {
    operator fun invoke(skill: IntArray): Long
}

class DividePlayersMap : DividePlayers {
    override fun invoke(skill: IntArray): Long {
        val n = skill.size
        var totalSkill = 0
        val skillMap = mutableMapOf<Int, Int>()

        // Calculate total skill and build frequency map
        for (s in skill) {
            totalSkill += s
            skillMap[s] = skillMap.getOrDefault(s, 0) + 1
        }

        // Check if total skill can be evenly distributed among teams
        if (totalSkill % (n / 2) != 0) {
            return -1
        }

        val targetSkill = totalSkill / (n / 2)
        var totalChemistry: Long = 0

        // Iterate through unique skill values
        for ((currSkill, currFreq) in skillMap) {
            val partnerSkill = targetSkill - currSkill

            // Check if valid partner skill exists with matching frequency
            if (skillMap[partnerSkill] != currFreq) {
                return -1
            }

            // Calculate chemistry for all pairs with this skill
            totalChemistry += currSkill.toLong() * partnerSkill.toLong() * currFreq
        }

        // Return half of total chemistry (as each pair is counted twice)
        return totalChemistry / 2
    }
}
