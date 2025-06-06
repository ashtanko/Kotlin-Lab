package dev.shtanko.algorithms.slidingwindow

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

class FixedSizeExampleTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments?>? {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 3, 4, 5), 3, 12),      // Normal case
                Arguments.of(intArrayOf(-1, -2, -3, -4, -5), 2, -3),  // All negatives
                Arguments.of(intArrayOf(2, -1, 2, 3, -9, 4), 3, 4),   // Mix values
                Arguments.of(intArrayOf(5, 1, 2), 3, 8),              // k = size
                Arguments.of(intArrayOf(3, 9, 2, 4, 1), 1, 9),        // k = 1
                Arguments.of(intArrayOf(1, 2), 3, 0),                 // k > size
                Arguments.of(intArrayOf(), 2, 0),                     // Empty array
                Arguments.of(intArrayOf(1, 2, 3), 0, 0),              // k = 0
                Arguments.of(intArrayOf(7), 1, 7),                    // Single element
                Arguments.of(intArrayOf(0, 0, 0, 0), 2, 0),           // All zeros
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `maxSum should return expected result`(arr: IntArray, k: Int, expected: Int) {
        val result = maxSumFixedSizeSlidingWindow(arr, k)
        assertThat(result).isEqualTo(expected)
    }
}
