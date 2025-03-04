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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1233. Remove Sub-Folders from the Filesystem
 * @see <a href="https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/">Source</a>
 */
@Medium("https://leetcode.com/problems/remove-sub-folders-from-the-filesystem")
fun interface RemoveSubfolders {
    operator fun invoke(folder: Array<String>): List<String>
}

class RemoveSubfoldersTrie : RemoveSubfolders {

    private val root = TrieNode()

    override fun invoke(folder: Array<String>): List<String> {
        for (path in folder) {
            var currentNode = root
            val folderNames = path.split("/")

            for (folderName in folderNames) {
                // Skip empty folder names
                if (folderName.isEmpty()) continue
                // Create new node if it doesn't exist
                currentNode = currentNode.children.getOrPut(folderName) { TrieNode() }
            }
            // Mark the end of the folder path
            currentNode.isEndOfFolder = true
        }

        // Check each path for subfolders
        val result = mutableListOf<String>()
        for (path in folder) {
            var currentNode = root
            val folderNames = path.split("/")
            var isSubfolder = false

            for (i in folderNames.indices) {
                // Skip empty folder names
                if (folderNames[i].isEmpty()) continue

                val nextNode = currentNode.children[folderNames[i]]
                // Check if the current folder path is a subfolder of an
                // existing folder
                if (nextNode?.isEndOfFolder == true && i != folderNames.size - 1) {
                    isSubfolder = true
                    break // Found a sub-folder
                }

                currentNode = nextNode ?: break
            }
            // If not a sub-folder, add to the result
            if (!isSubfolder) result.add(path)
        }

        return result
    }

    private data class TrieNode(
        var isEndOfFolder: Boolean = false,
        val children: MutableMap<String, TrieNode> = mutableMapOf(),
    )
}
