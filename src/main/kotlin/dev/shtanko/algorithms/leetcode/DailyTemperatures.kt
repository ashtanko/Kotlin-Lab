/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.Deque
import java.util.LinkedList
import java.util.Stack

/**
 * 739. Daily Temperatures
 * @see <a href="https://leetcode.com/problems/daily-temperatures">Source</a>
 */
@Medium("https://leetcode.com/problems/daily-temperatures")
fun interface DailyTemperatures {
    operator fun invoke(temperatures: IntArray): IntArray
}

class DailyTemperaturesStack : DailyTemperatures {
    override operator fun invoke(temperatures: IntArray): IntArray {
        val stack: Stack<Int> = Stack()
        val ret = IntArray(temperatures.size)
        for (i in temperatures.indices) {
            while (stack.isNotEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                val idx: Int = stack.pop()
                ret[idx] = i - idx
            }
            stack.push(i)
        }
        return ret
    }
}

class DailyTemperaturesArr : DailyTemperatures {
    override operator fun invoke(temperatures: IntArray): IntArray {
        val stack = IntArray(temperatures.size)
        var top = -1
        val ret = IntArray(temperatures.size)
        for (i in temperatures.indices) {
            while (top > -1 && temperatures[i] > temperatures[stack[top]]) {
                val idx = stack[top--]
                ret[idx] = i - idx
            }
            stack[++top] = i
        }
        return ret
    }
}

class DailyTemperaturesDeque : DailyTemperatures {
    override operator fun invoke(temperatures: IntArray): IntArray {
        if (temperatures.isEmpty()) {
            return intArrayOf()
        }
        val stack: Deque<Int> = LinkedList()
        val res = IntArray(temperatures.size)
        // the idea is to keep all element in ascending order!
        for (i in temperatures.indices) {
            if (stack.isEmpty()) {
                // case1 : stack is empty, feel free to push
                stack.offerFirst(i)
            } else if (stack.isNotEmpty() && temperatures[i] > temperatures[stack.peekFirst()]) {
                // case2 : descending order happens:
                // pop all the smaller element out, then push the current element
                while (stack.isNotEmpty() && temperatures[i] > temperatures[stack.peekFirst()]) {
                    res[stack.peekFirst()] = i - stack.peekFirst()
                    stack.pollFirst()
                }
                stack.offerFirst(i) // push current element to keep ascending order
            } else {
                // case3 : push current element to keep ascending order
                stack.offerFirst(i)
            }
        }
        return res
    }
}
