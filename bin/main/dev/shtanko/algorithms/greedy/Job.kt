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

package dev.shtanko.algorithms.greedy

internal data class Job(
    var id: Char = ' ',
    var deadline: Int = 0,
    private var profit: Int = 0,
) : Comparator<Job> {
    override fun compare(j1: Job, j2: Job): Int {
        return if (j1.profit > j2.profit) -1 else 1
    }
}

internal fun Array<Job>.scheduleJobs(): MutableList<Char> {
    sortWith(Job())
    val n = size
    val result = IntArray(n) // To store result (Sequence of jobs)
    val slot = BooleanArray(n) // To keep track of free time slots

    // Initialize all slots to be free
    for (i in 0 until n)
        slot[i] = false

    // Iterate through all given jobs
    for (i in 0 until n) {
        // Find a free slot for this job
        for (j in kotlin.math.min(n, this[i].deadline) - 1 downTo 0) {
            // Free slot found
            if (!slot[j]) {
                result[j] = i // Add this job to result
                slot[j] = true // Make this slot occupied
                break
            }
        }
    }

    val res = mutableListOf<Char>()
    // Print the result
    for (i in 0 until n) {
        if (slot[i]) {
            val ch = this[result[i]].id
            res.add(ch)
        }
    }
    return res
}
