/*
 * Copyright 2024 Oleksii Shtanko
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

/**
 * 2096. Step-By-Step Directions From a Binary Tree Node to Another
 * @see <a href="https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another">Source</a>
 */
fun interface GetDirections {
    operator fun invoke(root: TreeNode?, startValue: Int, destValue: Int): String
}

class GetDirectionsBFS : GetDirections {
    private val pathBuilder = StringBuilder()
    private var searchState = STATE_IDLE
    private var startNodeValue = 0
    private var destinationNodeValue = 0

    override fun invoke(
        root: TreeNode?,
        startValue: Int,
        destValue: Int,
    ): String {
        this.startNodeValue = startValue
        this.destinationNodeValue = destValue
        root?.let {
            searchLeftToRight(it)
        }
        if (searchState == STATE_ERROR) {
            root?.let {
                searchState = STATE_IDLE
                pathBuilder.setLength(0)
                searchRightToLeft(it)
            }
        }
        return pathBuilder.toString()
    }

    private fun searchLeftToRight(node: TreeNode) {
        if (searchState == STATE_FINISHED || searchState == STATE_ERROR) return

        if (node.value == startNodeValue) {
            searchState = STATE_RECORDING
        }
        if (node.value == destinationNodeValue) {
            searchState = when (searchState) {
                STATE_RECORDING -> STATE_FINISHED
                else -> STATE_ERROR // Switch to right-to-left search
            }
        }

        node.left?.let {
            val previousLength = if (searchState == STATE_RECORDING) {
                pathBuilder.append("L")
                pathBuilder.length
            } else {
                0
            }
            searchLeftToRight(it)
            if (searchState == STATE_RECORDING && previousLength > 0) pathBuilder.setLength(previousLength - 1)
        }

        node.right?.let {
            val previousLength = if (searchState == STATE_RECORDING) {
                pathBuilder.append("R")
                pathBuilder.length
            } else {
                0
            }
            searchLeftToRight(it)
            if (searchState == STATE_RECORDING && previousLength > 0) pathBuilder.setLength(previousLength - 1)
        }

        if (node.value == destinationNodeValue && searchState == STATE_RECORDING) {
            searchState = STATE_FINISHED
        }

        if (searchState == STATE_RECORDING) {
            pathBuilder.append("U")
        }
    }

    private fun searchRightToLeft(node: TreeNode) {
        if (searchState == STATE_FINISHED || searchState == STATE_ERROR) return

        if (node.value == startNodeValue) {
            searchState = STATE_RECORDING
        }
        if (node.value == destinationNodeValue && searchState == STATE_RECORDING) {
            searchState = STATE_FINISHED
        }

        node.right?.let {
            val previousLength = if (searchState == STATE_RECORDING) {
                pathBuilder.append("R")
                pathBuilder.length
            } else {
                0
            }
            searchRightToLeft(it)
            if (searchState == STATE_RECORDING && previousLength > 0) {
                pathBuilder.setLength(previousLength - 1)
            }
        }

        node.left?.let {
            val previousLength = if (searchState == STATE_RECORDING) {
                pathBuilder.append("L")
                pathBuilder.length
            } else {
                0
            }
            searchRightToLeft(it)
            if (searchState == STATE_RECORDING && previousLength > 0) pathBuilder.setLength(previousLength - 1)
        }

        if (node.value == destinationNodeValue && searchState == STATE_RECORDING) {
            searchState = STATE_FINISHED
        }

        if (searchState == STATE_RECORDING) {
            pathBuilder.append("U")
        }
    }

    companion object {
        const val STATE_IDLE = 0
        const val STATE_RECORDING = 1
        const val STATE_FINISHED = 2
        const val STATE_ERROR = 3
    }
}
