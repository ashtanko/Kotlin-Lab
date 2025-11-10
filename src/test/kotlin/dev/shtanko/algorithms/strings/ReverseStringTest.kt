package dev.shtanko.algorithms.strings

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

internal class ReverseStringTest {
    private lateinit var stdApproach: ReverseString
    private lateinit var twoPointersApproach: ReverseString

    @BeforeEach
    fun setUp() {
        stdApproach = ReverseStringStd()
        twoPointersApproach = ReverseStringTwoPointers()
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of("", ""),
            Arguments.of("a", "a"),
            Arguments.of("ab", "ba"),
            Arguments.of("hello", "olleh"),
            Arguments.of("racecar", "racecar"),  // palindrome
            Arguments.of("OpenAI", "IAnepO"),
            Arguments.of("  space  ", "  ecaps  "),  // handling spaces
            Arguments.of("12345", "54321"),
            Arguments.of("!@#\$%", "%\$#@!"),  // special characters
            Arguments.of("AaBbCc", "cCbBaA"),  // mixed case
            Arguments.of("a".repeat(1_000_000), "a".repeat(1_000_000)), // large input test
        )
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class StdApproachTests {

        @ParameterizedTest
        @ArgumentsSource(InputArgumentsProvider::class)
        fun `should correctly reverse a string using standard approach`(input: String, expected: String) {
            assertThat(stdApproach(input)).isEqualTo(expected)
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class TwoPointersApproachTests {

        @ParameterizedTest
        @ArgumentsSource(InputArgumentsProvider::class)
        fun `should correctly reverse a string using two-pointer approach`(input: String, expected: String) {
            assertThat(twoPointersApproach(input)).isEqualTo(expected)
        }
    }
}
