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

internal class RemoveMiddleSubstringTest {
    private lateinit var stdApproach: RemoveMiddleSubstring
    private lateinit var loopApproach: RemoveMiddleSubstring

    @BeforeEach
    fun setUp() {
        stdApproach = RemoveMiddleSubstringStd()
        loopApproach = RemoveMiddleSubstringLoop()
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("", "", ""), // empty input and remove
            Arguments.of("abc", "", "abc"), // empty remove string
            Arguments.of("", "abc", ""), // empty input
            Arguments.of("HelloWorld", "World", "Hello"), // substring in middle
            Arguments.of("WorldHello", "World", "Hello"), // substring at start
            Arguments.of("HelloWorldHello", "World", "HelloHello"), // substring in middle, repeated
            Arguments.of("aaaaa", "aa", "aaa"), // multiple occurrences
            Arguments.of("abcabcabc", "abc", "abcabc"), // first occurrence only
            Arguments.of("OpenAI AI Open", "AI", "Open AI Open"), // single match
            Arguments.of("spaces in between", " ", "spacesin between"), // space removal
            Arguments.of("!@#\$%^&*()", "!@#", "\$%^&*()"), // special characters
            Arguments.of("123456789", "456", "123789"), // numbers
            Arguments.of("caseCASEcase", "CASE", "casecase"), // case-sensitive
            Arguments.of(
                "a".repeat(1_000_000) + "Hello" + "b".repeat(1_000_000),
                "Hello",
                "a".repeat(1_000_000) + "b".repeat(1_000_000),
            ), // large input test
        )
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class StdApproachTests {

        @ParameterizedTest
        @ArgumentsSource(InputArgumentsProvider::class)
        fun `should correctly remove substring using standard approach`(
            input: String,
            toRemove: String,
            expected: String,
        ) {
            assertThat(stdApproach(input, toRemove)).isEqualTo(expected)
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class LoopApproachTests {

        @ParameterizedTest
        @ArgumentsSource(InputArgumentsProvider::class)
        fun `should correctly remove substring using loop approach`(input: String, toRemove: String, expected: String) {
            assertThat(loopApproach(input, toRemove)).isEqualTo(expected)
        }
    }
}
