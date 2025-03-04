/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.bitwise

import dev.shtanko.algorithms.annotations.Bitwise

/**
 * Let us take unsigned integer (32 bit), which consist of 0-31 bits.
 * To print binary representation of unsigned integer, start from 31st bit, check whether 31st bit is ON or OFF,
 * if it is ON print “1” else print “0”. Now check whether 30th bit is ON or OFF, if it is ON print “1” else print “0”,
 * do this for all bits from 31 to 0, finally we will get binary representation of number.
 * Time Complexity: O(1)
 * Auxiliary Space: O(1)
 */
@Bitwise
fun Long.bin(): String {
    val sb = StringBuilder()
    sb.append("0")

    var i: Long = (1 shl 30).toLong()
    while (i > 0) {
        if (this and i != 0L) {
            sb.append("1")
        } else {
            sb.append("0")
        }
        i /= 2
    }
    return sb.toString()
}
