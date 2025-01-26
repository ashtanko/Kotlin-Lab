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

object FloodFill {

    operator fun invoke(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
        val color = image[sr][sc]
        if (color != newColor) dfs(image, sr, sc, color, newColor)
        return image
    }

    private fun dfs(image: Array<IntArray>, r: Int, c: Int, color: Int, newColor: Int) {
        if (image[r][c] == color) {
            image[r][c] = newColor
            if (r >= 1) dfs(image, r - 1, c, color, newColor)
            if (c >= 1) dfs(image, r, c - 1, color, newColor)
            if (r + 1 < image.size) dfs(image, r + 1, c, color, newColor)
            if (c + 1 < image.first().size) dfs(image, r, c + 1, color, newColor)
        }
    }
}
