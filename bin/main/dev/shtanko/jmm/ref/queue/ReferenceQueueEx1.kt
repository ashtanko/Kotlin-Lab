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

object ReferenceQueueEx1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val referent = Any()
        val referenceQueue = ReferenceQueue<Any>()
        val weakReference1 = WeakReference(referent)
        val weakReference2 = WeakReference(referent, referenceQueue)
        val referent2 = weakReference1.get()
        weakReference1.clear()
        val referent3 = weakReference2.get()
        val predicate = if (referent3 != null) {
            println("GC hasn't removed the instance yet")
            false
        } else {
            println("GC has cleared the instance")
            true
        }
        println(predicate)
        println(referent2)
    }
}
