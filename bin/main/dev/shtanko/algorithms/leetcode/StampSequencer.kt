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

import java.util.LinkedList
import java.util.Queue
import java.util.Stack
import kotlin.math.max
import kotlin.math.min

/**
 * 936. Stamping The Sequence
 * @see <a href="https://leetcode.com/problems/stamping-the-sequence/">Source</a>
 */
class StampSequencer {

    /**
     * This function calculates the sequence of moves to stamp a string onto another string.
     * @param stamp The string to be stamped.
     * @param target The string onto which the stamp string is to be stamped.
     * @return An array of integers representing the sequence of moves to stamp the string.
     */
    fun getStampingSequence(stamp: String, target: String): IntArray {
        val stampLength: Int = stamp.length
        val targetLength: Int = target.length
        val positionQueue: Queue<Int> = LinkedList()
        val isStamped = BooleanArray(targetLength)
        val sequence: Stack<Int> = Stack()
        val windows: MutableList<Window> = ArrayList()

        for (i in 0..targetLength - stampLength) {
            val stampedIndices: MutableSet<Int> = HashSet()
            val unstampedIndices: MutableSet<Int> = HashSet()
            for (j in 0 until stampLength) {
                if (target[i + j] == stamp[j]) {
                    stampedIndices.add(i + j)
                } else {
                    unstampedIndices.add(i + j)
                }
            }
            windows.add(Window(stampedIndices, unstampedIndices))

            if (unstampedIndices.isEmpty()) {
                sequence.push(i)
                for (j in i until i + stampLength) if (!isStamped[j]) {
                    positionQueue.add(j)
                    isStamped[j] = true
                }
            }
        }

        processEachPositionInQueue(positionQueue, windows, stampLength, targetLength, sequence, isStamped)

        for (stamped in isStamped) {
            if (!stamped) {
                return IntArray(0)
            }
        }

        val result = IntArray(sequence.size)
        var index = 0
        while (sequence.isNotEmpty()) {
            result[index++] = sequence.pop()
        }
        return result
    }

    /**
     * This function processes each position in the queue to calculate the sequence of moves.
     * @param positionQueue The queue of positions to be processed.
     * @param windows The list of windows in the target string.
     * @param stampLength The length of the stamp string.
     * @param targetLength The length of the target string.
     * @param sequence The stack to store the sequence of moves.
     * @param isStamped The array to track which positions in the target string have been stamped.
     */
    private fun processEachPositionInQueue(
        positionQueue: Queue<Int>,
        windows: List<Window>,
        stampLength: Int,
        targetLength: Int,
        sequence: Stack<Int>,
        isStamped: BooleanArray,
    ) {
        while (positionQueue.isNotEmpty()) {
            val position: Int = positionQueue.poll()
            for (j in max(0, position - stampLength + 1)..min(targetLength - stampLength, position)) {
                if (windows[j].unstampedIndices.contains(position)) {
                    windows[j].unstampedIndices.remove(position)
                    if (windows[j].unstampedIndices.isEmpty()) {
                        sequence.push(j)
                        for (o in windows[j].stampedIndices) {
                            if (!isStamped[o]) {
                                positionQueue.add(o)
                                isStamped[o] = true
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This data class represents a window in the target string.
     * @property stampedIndices The set of indices in the window that have been stamped.
     * @property unstampedIndices The set of indices in the window that have not been stamped.
     */
    private data class Window(var stampedIndices: Set<Int>, var unstampedIndices: MutableSet<Int>)
}
