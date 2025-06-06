package dev.shtanko.algorithms.codingbat.string2

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class SameStarCharTest<out T : SameStarChar>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments?>? = Stream.of(
            Arguments.of("", true),
            Arguments.of("*", true),
            Arguments.of("**", true),
            Arguments.of("abcDEF", true),
            Arguments.of("xy*yzz", true),
            Arguments.of("xy*zzz", false),
            Arguments.of("*xa*az", true),
            Arguments.of("*xa*bz", false),
            Arguments.of("*xa*a*", true),
            Arguments.of("*xa*a*a", true),
            Arguments.of("*xa*a*b", false),
            Arguments.of("*12*2*2", true),
            Arguments.of("12*2*3*", false),
            Arguments.of("XY*YYYY*Z*", false),
            Arguments.of("XY*YYYY*Y*", true),
            Arguments.of("12*2*3*", false),
        )
    }

    @ArgumentsSource(InputArgumentsProvider::class)
    @ParameterizedTest
    fun test(str: String, expected: Boolean) {
        val actual = strategy.invoke(str)
        assertThat(actual).isEqualTo(expected)
    }
}

class SameStarCharSlidingWindowTest : SameStarCharTest<SameStarChar>(SameStarChar.SlidingWindow)
class SameStarCharSlidingWindowSimpleTest : SameStarCharTest<SameStarChar>(SameStarChar.SlidingWindowSimple)
