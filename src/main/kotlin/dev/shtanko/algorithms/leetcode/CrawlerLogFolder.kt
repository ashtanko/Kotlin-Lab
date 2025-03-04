/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.level.Easy
import java.util.Stack

/**
 * 1598. Crawler Log Folder
 * @see <a href="https://leetcode.com/problems/crawler-log-folder">Source</a>
 */
@Easy("https://leetcode.com/problems/crawler-log-folder")
fun interface CrawlerLogFolder {
    operator fun invoke(logs: Array<String>): Int
}

class CrawlerLogFolderCounter : CrawlerLogFolder {
    override fun invoke(logs: Array<String>): Int {
        return logs.fold(0) { folderDepth, currentOperation ->
            when (currentOperation) {
                "../" -> maxOf(0, folderDepth - 1)
                "./" -> folderDepth
                else -> folderDepth + 1
            }
        }
    }
}

class CrawlerLogFolderFold : CrawlerLogFolder {
    override fun invoke(logs: Array<String>): Int {
        return logs.fold(emptyList<String>()) { folderStack, currentOperation ->
            when (currentOperation) {
                "../" -> if (folderStack.isNotEmpty()) folderStack.dropLast(1) else folderStack
                "./" -> folderStack
                else -> folderStack + currentOperation
            }
        }.size
    }
}

class CrawlerLogFolderStack : CrawlerLogFolder {
    override fun invoke(logs: Array<String>): Int {
        val folderStack = Stack<String>()

        for (currentOperation in logs) {
            when {
                currentOperation == "../" -> {
                    if (folderStack.isNotEmpty()) {
                        folderStack.pop()
                    }
                }

                currentOperation != "./" -> {
                    folderStack.push(currentOperation)
                }
            }
        }
        return folderStack.size
    }
}
