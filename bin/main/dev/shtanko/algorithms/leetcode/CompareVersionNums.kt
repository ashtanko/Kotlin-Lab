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

import dev.shtanko.algorithms.annotations.Iterative

/**
 * 165. Compare Version Numbers
 * @see <a href="https://leetcode.com/problems/compare-version-numbers/">Source</a>
 */
fun interface CompareVersionNums {
    operator fun invoke(version1: String, version2: String): Int
}

@Iterative
class CompareVersionNumsStream : CompareVersionNums {
    override fun invoke(version1: String, version2: String): Int {
        val versionStream1 = VersionStream(version1)
        val versionStream2 = VersionStream(version2)

        var comparisonResult = 0
        while (versionStream1.isAvailable() || versionStream2.isAvailable()) {
            val difference = versionStream1.readVersionPart() - versionStream2.readVersionPart()
            if (difference > 0) {
                comparisonResult = 1; break
            }
            if (difference < 0) {
                comparisonResult = -1; break
            }
        }
        return comparisonResult
    }

    class VersionStream(private val version: String) {
        var index = 0
        var versionPart = 0

        fun isAvailable(): Boolean {
            return index < version.length
        }

        fun readVersionPart(): Int {
            versionPart = 0
            while (index < version.length) {
                val character = version[index++]
                if (character == '.') break
                versionPart = versionPart * 10 + (character - '0')
            }
            return versionPart
        }
    }
}
