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

import java.util.PriorityQueue

/**
 * 1054. Distant Barcodes
 * @see <a href="https://leetcode.com/problems/distant-barcodes/">Source</a>
 */
fun interface DistantBarcodes {
    fun rearrangeBarcodes(barcodes: IntArray): IntArray
}

class DistantBarcodesMap : DistantBarcodes {
    override fun rearrangeBarcodes(barcodes: IntArray): IntArray {
        val cnt: MutableMap<Int, Int> = HashMap()
        for (i in barcodes) {
            cnt[i] = cnt.getOrDefault(i, 0) + 1
        }
        val list: List<Map.Entry<Int, Int>> = ArrayList<Map.Entry<Int, Int>>(cnt.entries).sortedWith(
            java.util.Map.Entry.comparingByValue<Int, Int>().reversed(),
        )
        val l: Int = barcodes.size
        var i = 0
        val res = IntArray(l)
        for (e in list) {
            var time = e.value
            while (time-- > 0) {
                res[i] = e.key
                i += 2
                if (i >= barcodes.size) i = 1
            }
        }
        return res
    }
}

class DistantBarcodesQueue : DistantBarcodes {
    override fun rearrangeBarcodes(barcodes: IntArray): IntArray {
        val ct: MutableMap<Int, Int> = HashMap()
        val pq = PriorityQueue { a: Int, b: Int ->
            ct.getOrDefault(b, 0) - ct.getOrDefault(a, 0)
        }

        for (b in barcodes) {
            ct[b] = ct.getOrDefault(b, 0) + 1
        }

        for (k in ct.keys) {
            pq.add(k)
        }

        var pos = 0
        while (pq.isNotEmpty()) {
            val el = pq.poll()
            val count = ct.getOrDefault(el, 0)
            var i = 0
            while (i < count) {
                if (pos >= barcodes.size) pos = 1
                barcodes[pos] = el
                i++
                pos += 2
            }
        }

        return barcodes
    }
}
