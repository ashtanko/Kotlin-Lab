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

import java.util.Stack

interface BrowserHistory {
    fun visit(url: String)

    fun back(steps: Int): String

    fun forward(steps: Int): String
}

class BrowserHistoryArray(homepage: String) : BrowserHistory {

    private var current = -1
    private var top = 0
    private val h = Array(ARRAY_SIZE) { "" }

    init {
        current++
        h[current] = homepage
        top++
    }

    override fun visit(url: String) {
        current++
        h[current] = url
        top = current + 1
    }

    override fun back(steps: Int): String {
        if (current <= steps) {
            current = 0
        } else {
            current -= steps
        }
        return h[current]
    }

    override fun forward(steps: Int): String {
        if (current + steps + 1 >= top) {
            current = top - 1
        } else {
            current += steps
        }
        return h[current]
    }

    companion object {
        private const val ARRAY_SIZE = 101
    }
}

class BrowserHistoryList(homepage: String) : BrowserHistory {

    private var currentIndex = 0
    private val history = mutableListOf<String>()

    init {
        history.add(homepage)
    }

    override fun visit(url: String) {
        history.subList(currentIndex.plus(1), history.size).clear()
        history.add(url)
        currentIndex++
    }

    override fun back(steps: Int): String {
        currentIndex = currentIndex.minus(steps).coerceAtLeast(0)
        return history[currentIndex]
    }

    override fun forward(steps: Int): String {
        currentIndex = currentIndex.plus(steps).coerceAtMost(history.size - 1)
        return history[currentIndex]
    }
}

class BrowserHistoryStack(homepage: String) : BrowserHistory {

    private val history = Stack<String>()
    private var future: Stack<String>

    init {
        history.push(homepage)
        future = Stack()
    }

    override fun visit(url: String) {
        history.push(url)
        future = Stack()
    }

    override fun back(steps: Int): String {
        var s = steps
        while (s > 0 && history.size > 1) {
            future.push(history.peek())
            history.pop()
            s--
        }
        return history.peek()
    }

    override fun forward(steps: Int): String {
        var s = steps
        while (s > 0 && future.isNotEmpty()) {
            history.push(future.peek())
            future.pop()
            s--
        }
        return history.peek()
    }
}
