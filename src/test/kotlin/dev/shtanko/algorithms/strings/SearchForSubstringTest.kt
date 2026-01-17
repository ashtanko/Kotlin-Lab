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

internal class SearchForSubstringTest {
    private lateinit var stdApproach: SearchForSubstring
    private lateinit var bruteForceApproach: SearchForSubstring

    @BeforeEach
    fun setUp() {
        stdApproach = SearchForSubstringStd()
        bruteForceApproach = SearchForSubstringBruteForce()
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of("hello world", "hello", 0),
            Arguments.of("hello world", "world", 6),
            Arguments.of("hello world", "bye", -1),
            Arguments.of("hello", "", 0),
            Arguments.of("", "test", -1),
            Arguments.of("banana", "ana", 1),
            Arguments.of("aaaaaa", "aaa", 0),
            Arguments.of("short", "this is too long", -1),
        )
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class StdApproachTests {

        @ParameterizedTest
        @ArgumentsSource(InputArgumentsProvider::class)
        fun `should return correct substring index`(input: String, toFind: String, expected: Int) {
            assertThat(stdApproach(input, toFind)).isEqualTo(expected)
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class BruteForceApproachTests {

        @ParameterizedTest
        @ArgumentsSource(InputArgumentsProvider::class)
        fun `should return correct substring index`(input: String, toFind: String, expected: Int) {
            assertThat(bruteForceApproach(input, toFind)).isEqualTo(expected)
        }
    }
}
