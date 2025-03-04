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

package dev.shtanko.algorithms.utils

import dev.shtanko.algorithms.leetcode.TreeNode
import org.junit.jupiter.api.Assertions.assertEquals

fun assertTreeNodeEquals(expected: TreeNode?, actual: TreeNode?) {
    if (expected == null && actual == null) return

    assertEquals(expected?.value, actual?.value, "Node values are not equal")
    assertTreeNodeEquals(expected?.left, actual?.left)
    assertTreeNodeEquals(expected?.right, actual?.right)
}
