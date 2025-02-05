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

/**
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
 * Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
 * so "a" is considered a different type of stone from "A".
 */
fun interface NumJewelsInStonesStrategy {
    operator fun invoke(a: String, b: String): Int
}

class NumJewelsInStonesMap : NumJewelsInStonesStrategy {
    override operator fun invoke(a: String, b: String): Int {
        var res = 0
        val setJ = hashSetOf<Char>()
        for (jewel in a) {
            setJ.add(jewel)
        }
        for (stone in b) {
            if (setJ.contains(stone)) {
                res++
            }
        }
        return res
    }
}

class NumJewelsInStonesRegex : NumJewelsInStonesStrategy {
    override operator fun invoke(a: String, b: String): Int {
        return b.replace("[^$a]".toRegex(), "").length
    }
}
