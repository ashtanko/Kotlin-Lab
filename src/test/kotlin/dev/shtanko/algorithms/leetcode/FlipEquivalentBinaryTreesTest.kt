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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FlipEquivalentBinaryTreesTest<out T : FlipEquivalentBinaryTrees>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf<Int?>(1, 2, 3, 4, 5, 6, null, null, null, 7, 8).buildTree(),
                arrayOf<Int?>(1, 3, 2, null, 6, 4, 5, null, null, null, null, 8, 7).buildTree(),
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun flipEquivTest(root1: TreeNode?, root2: TreeNode?, expected: Boolean) {
        val actual = strategy(root1, root2)
        println(root1?.prettyPrint())
        println(root2?.prettyPrint())
        assertThat(actual).isEqualTo(expected)
    }
}

class FlipEquivalentBinaryTreesFormTest :
    FlipEquivalentBinaryTreesTest<FlipEquivalentBinaryTreesForm>(FlipEquivalentBinaryTreesForm())
