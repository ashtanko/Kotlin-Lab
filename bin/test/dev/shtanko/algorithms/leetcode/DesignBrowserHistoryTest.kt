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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

abstract class DesignBrowserHistoryTest<out T : BrowserHistory>(private val history: T) {

    @Test
    fun `history test`() {
        history.visit("google.com")
        history.visit("facebook.com")
        history.visit("youtube.com")
        assertEquals("facebook.com", history.back(1))
        assertEquals("google.com", history.back(1))
        assertEquals("facebook.com", history.forward(1))
        history.visit("linkedin.com")
        assertEquals("linkedin.com", history.forward(2))
        assertEquals("google.com", history.back(2))
        assertEquals("leetcode.com", history.back(7))
    }
}

class BrowserHistoryListTest :
    DesignBrowserHistoryTest<BrowserHistoryList>(BrowserHistoryList("leetcode.com"))

class BrowserHistoryStackTest :
    DesignBrowserHistoryTest<BrowserHistoryStack>(BrowserHistoryStack("leetcode.com"))

class BrowserHistoryArrayTest :
    DesignBrowserHistoryTest<BrowserHistoryArray>(BrowserHistoryArray("leetcode.com"))
