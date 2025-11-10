package dev.shtanko.algorithms.codingbat.string2

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class StarOutTest<out T : StarOut>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of("ab*cd", "ad"),
            Arguments.of("ab**cd", "ad"),
            Arguments.of("sm*eilly", "silly"),
            Arguments.of("sm*eil*ly", "siy"),
            Arguments.of("sm**eil*ly", "siy"),
            Arguments.of("sm***eil*ly", "siy"),
            Arguments.of("stringy*", "string"),
            Arguments.of("*str*in*gy", "ty"),
            Arguments.of("abc", "abc"),
            Arguments.of("*stringy", "tringy"),
            Arguments.of("a*bc", "c"),
            Arguments.of("ab", "ab"),
            Arguments.of("a*b", ""),
            Arguments.of("a", "a"),
            Arguments.of("a*", ""),
            Arguments.of("*a", ""),
            Arguments.of("*a*", ""),
            Arguments.of("", ""),
            Arguments.of("*", ""),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `star out test`(str: String, expected: String) {
        val actual = strategy.invoke(str)
        assertThat(actual).isEqualTo(expected)
    }
}

class StarOutIterativeTest : StarOutTest<StarOut>(StarOut.Iterative)
class StarOutStackTest : StarOutTest<StarOut>(StarOut.Stack)
