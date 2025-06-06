package dev.shtanko.algorithms.slidingwindow

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

class VariableSizeExampleTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments?> {
            return Stream.of(
                Arguments.of("", 0),                    // Empty string
                Arguments.of("a", 1),                   // Single character
                Arguments.of("abcabcbb", 3),            // Normal case with repeats
                Arguments.of("bbbbb", 1),               // All same characters
                Arguments.of("pwwkew", 3),              // Repeats with longer unique in middle
                Arguments.of("abcdef", 6),              // All unique
                Arguments.of("abba", 2),                // Repeat after unique chars
                Arguments.of("aab", 2),                 // Slight repetition
                Arguments.of("dvdf", 3),                // Disjoint substrings
                Arguments.of("tmmzuxt", 5),             // Complex mixed pattern
            )
        }
    }


    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `lengthOfLongestSubstring should return expected result`(str: String, expected: Int) {
        val result = lengthOfLongestSubstring(str)
        assertThat(result).isEqualTo(expected)
    }
}
