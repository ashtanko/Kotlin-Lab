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

class MajorityChecker(val arr: IntArray) {

    fun query(left: Int, right: Int, threshold: Int): Int {
        var vote = -1
        var cnt = 0
        for (i in left..right) {
            if (cnt == 0) {
                vote = arr[i]
            }
            if (vote == arr[i]) {
                cnt++
            } else {
                cnt--
            }
        }
        cnt = 0
        for (i in left..right) {
            if (arr[i] == vote) {
                cnt++
            }
        }
        return if (cnt >= threshold) vote else -1
    }
}
