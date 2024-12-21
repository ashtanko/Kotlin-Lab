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

package dev.shtanko.jmm.ref.queue

import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference

object GFG {
    @JvmStatic
    fun main(args: Array<String>) {
        val obj = HelperClass()
        val rq = ReferenceQueue<HelperClass>()
        val wr = WeakReference(obj)
        println("-> Reference Queue Object :")
        println(rq)
        println("-> Reference Queue Poll :")
        // Checking if phantom object is lined up
        // or cleared in the queue
        // using the poll() method
        println(rq.poll())
        wr.get()?.display()
    }
}
