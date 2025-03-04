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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.HALF_OF_BYTE
import dev.shtanko.algorithms.annotations.DFS

/**
 * 93. Restore IP Addresses
 * @see <a href="https://leetcode.com/problems/restore-ip-addresses/">Source</a>
 */
fun interface RestoreIPAddresses {
    operator fun invoke(s: String): List<String>
}

@DFS
class RestoreIPAddressesDFS : RestoreIPAddresses {
    override operator fun invoke(s: String): List<String> {
        val solutions: MutableList<String> = ArrayList()
        restoreIp(s, solutions, 0, "", 0)
        return solutions
    }

    private fun restoreIp(ip: String, solutions: MutableList<String>, idx: Int, restored: String, count: Int) {
        if (count > 4) return
        if (count == 4 && idx == ip.length) solutions.add(restored)
        for (i in 1..3) {
            if (idx + i > ip.length) break
            val s = ip.substring(idx, idx + i)
            if (s.startsWith("0") && s.length > 1 || i == 3 && s.toInt() >= HALF_OF_BYTE) continue
            restoreIp(ip, solutions, idx + i, restored + s + if (count == 3) "" else ".", count + 1)
        }
    }
}

class RestoreIPAddressesFast : RestoreIPAddresses {
    override operator fun invoke(s: String): List<String> {
        val ret: MutableList<String> = ArrayList()

        val ip = StringBuffer()
        for (a in 1..3) for (b in 1..3) for (c in 1..3) for (d in 1..3) {
            if (a + b + c + d == s.length) {
                val n1: Int = s.substring(0, a).toInt()
                val n2: Int = s.substring(a, a + b).toInt()
                val n3: Int = s.substring(a + b, a + b + c).toInt()
                val n4: Int = s.substring(a + b + c).toInt()
                if (n1 <= 255 && n2 <= 255 && n3 <= 255 && n4 <= 255) {
                    ip.append(n1).append('.').append(n2)
                        .append('.').append(n3).append('.').append(n4)
                    if (ip.length == s.length + 3) ret.add(ip.toString())
                    ip.delete(0, ip.length)
                }
            }
        }
        return ret
    }
}
