/*
 * Copyright 2021 Oleksii Shtanko
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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class DesignTicTacToeTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(TTTOptimizedBruteForce(3)),
            Arguments.of(TTTOptimised(3)),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `move test`(ticTacToe: DesignTicTacToe) {
        // |X| | |
        // | | | |    // Player 1 makes a move at (0, 0).
        // | | | |
        assertThat(ticTacToe.move(0, 0, 1)).isEqualTo(0)

        // |X| |O|
        // | | | |    // Player 2 makes a move at (0, 2).
        // | | | |
        assertThat(ticTacToe.move(0, 2, 2)).isEqualTo(0)

        // |X| |O|
        // | | | |    // Player 1 makes a move at (2, 2).
        // | | |X|
        assertThat(ticTacToe.move(2, 2, 1)).isEqualTo(0)

        // |X| |O|
        // | |O| |    // Player 2 makes a move at (1, 1).
        // | | |X|
        assertThat(ticTacToe.move(1, 1, 2)).isEqualTo(0)

        // |X| |O|
        // | |O| |    // Player 1 makes a move at (2, 0).
        // |X| |X|
        assertThat(ticTacToe.move(2, 0, 1)).isEqualTo(0)

        // |X| |O|
        // |O|O| |    // Player 2 makes a move at (1, 0).
        // |X| |X|
        assertThat(ticTacToe.move(1, 0, 2)).isEqualTo(0)

        // |X| |O|
        // |O|O| |    // Player 1 makes a move at (2, 1).
        // |X|X|X|
        assertThat(ticTacToe.move(2, 1, 1)).isEqualTo(1)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `dead heat test`(ticTacToe: DesignTicTacToe) {
        // |X| | |
        // | | | |    // Player 1 makes a move at (0, 0).
        // | | | |
        assertThat(ticTacToe.move(0, 0, 1)).isEqualTo(0)

        // |X| | |
        // |0| | |    // Player 2 makes a move at (1, 0).
        // | | | |
        assertThat(ticTacToe.move(1, 0, 2)).isEqualTo(0)

        // |X| | |
        // |0| | |    // Player 1 makes a move at (2, 0).
        // |X| | |
        assertThat(ticTacToe.move(2, 0, 1)).isEqualTo(0)

        // |X| | |
        // |0|0| |    // Player 2 makes a move at (1, 1).
        // |X| | |
        assertThat(ticTacToe.move(1, 1, 2)).isEqualTo(0)

        // |X| | |
        // |0|0| |    // Player 1 makes a move at (1, 2).
        // |X| | |
        assertThat(ticTacToe.move(1, 2, 1)).isEqualTo(0)

        // |X| |0|
        // |0|0|X|    // Player 2 makes a move at (0, 2).
        // |X| | |
        assertThat(ticTacToe.move(0, 2, 2)).isEqualTo(0)

        // |X| |0|
        // |0|0|X|    // Player 1 makes a move at (2, 1).
        // |X|X| |
        assertThat(ticTacToe.move(2, 1, 1)).isEqualTo(0)

        // |X| |0|
        // |0|0|X|    // Player 2 makes a move at (2, 2).
        // |X|X|0|
        assertThat(ticTacToe.move(2, 2, 2)).isEqualTo(0)

        // |X|X|0|
        // |0|0|X|    // Player 1 makes a move at (0, 1).
        // |X|X|0|
        assertThat(ticTacToe.move(0, 1, 1)).isEqualTo(0)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `win X row 0 test`(ticTacToe: DesignTicTacToe) {
        // |X| | |
        // | | | |    // Player 1 makes a move at (0, 0).
        // | | | |
        assertThat(ticTacToe.move(0, 0, 1)).isEqualTo(0)

        // |X| | |
        // |0| | |    // Player 2 makes a move at (1, 0).
        // | | | |
        assertThat(ticTacToe.move(1, 0, 2)).isEqualTo(0)

        // |X|X| |
        // |0| | |    // Player 1 makes a move at (0, 1).
        // | | | |
        assertThat(ticTacToe.move(0, 1, 1)).isEqualTo(0)

        // |X|X| |
        // |0|0| |    // Player 2 makes a move at (0, 1).
        // | | | |
        assertThat(ticTacToe.move(1, 1, 2)).isEqualTo(0)

        // |X|X|X|
        // |0|0| |    // Player 1 makes a move at (0, 2).
        // | | | |
        assertThat(ticTacToe.move(0, 2, 1)).isEqualTo(1)
    }
}
