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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import dev.shtanko.algorithms.ASCII_A
import java.util.PriorityQueue
import kotlin.math.max

fun interface TaskScheduler {
    operator fun invoke(tasks: CharArray, cooldownTime: Int): Int
}

class TaskSchedulerSimple : TaskScheduler {

    override operator fun invoke(tasks: CharArray, cooldownTime: Int): Int {
        val taskFrequencyCounter = IntArray(ALPHABET_LETTERS_COUNT)
        var maxFrequency = 0
        var maxFrequencyCount = 0
        for (task in tasks) {
            taskFrequencyCounter[task - ASCII_A]++
            if (maxFrequency == taskFrequencyCounter[task - ASCII_A]) {
                maxFrequencyCount++
            } else if (maxFrequency < taskFrequencyCounter[task - ASCII_A]) {
                maxFrequency = taskFrequencyCounter[task - ASCII_A]
                maxFrequencyCount = 1
            }
        }

        val numberOfParts = maxFrequency - 1
        val partLength = cooldownTime - maxFrequencyCount + 1
        val emptySlots = numberOfParts * partLength
        val availableTasks: Int = tasks.size - maxFrequency * maxFrequencyCount
        val idleSlots = max(0, emptySlots - availableTasks)

        return tasks.size + idleSlots
    }
}

class TaskSchedulerPriorityQueue : TaskScheduler {
    override operator fun invoke(tasks: CharArray, cooldownTime: Int): Int {
        val taskFrequencyMap = calculateTaskFrequency(tasks)
        val taskPriorityQueue = createPriorityQueue(taskFrequencyMap)

        return executeTasksWithCooldown(taskPriorityQueue, cooldownTime)
    }

    private fun calculateTaskFrequency(tasks: CharArray): MutableMap<Char, Int> {
        val taskFrequencyMap: MutableMap<Char, Int> = HashMap()
        for (task in tasks) {
            taskFrequencyMap[task] = taskFrequencyMap.getOrDefault(task, 0) + 1
        }
        return taskFrequencyMap
    }

    private fun createPriorityQueue(taskFrequencyMap: MutableMap<Char, Int>): PriorityQueue<Map.Entry<Char, Int>> {
        val taskPriorityQueue: PriorityQueue<Map.Entry<Char, Int>> =
            PriorityQueue { a, b -> if (a.value != b.value) b.value - a.value else a.key - b.key }
        taskPriorityQueue.addAll(taskFrequencyMap.entries)
        return taskPriorityQueue
    }

    private fun executeTasksWithCooldown(
        taskPriorityQueue: PriorityQueue<Map.Entry<Char, Int>>,
        cooldownTime: Int,
    ): Int {
        var taskExecutions = 0
        while (taskPriorityQueue.isNotEmpty()) {
            var cooldown = cooldownTime + 1
            val temporaryTaskList: MutableList<Map.Entry<Char, Int>> = ArrayList()
            while (cooldown > 0 && taskPriorityQueue.isNotEmpty()) {
                val mostFrequentTask: MutableMap.MutableEntry<Char, Int> =
                    taskPriorityQueue.poll() as MutableMap.MutableEntry<Char, Int> // most frequency task
                mostFrequentTask.setValue(mostFrequentTask.value - 1) // decrease frequency, meaning it got executed
                temporaryTaskList.add(mostFrequentTask) // collect task to add back to queue
                cooldown--
                taskExecutions++ // successfully executed task
            }
            for (entry in temporaryTaskList) {
                if (entry.value > 0) taskPriorityQueue.add(entry) // add valid tasks
            }
            if (taskPriorityQueue.isEmpty()) break
            taskExecutions += cooldown // if cooldown > 0, then it means we need to be idle
        }
        return taskExecutions
    }
}
