package dev.shtanko.algorithms.codingbat.string2

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class GetSandwichTest<out T : GetSandwich>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments?>? = Stream.of(
            Arguments.of(
                "",
                "",
            ),
            Arguments.of(
                "breadjambread",
                "jam",
            ),
            Arguments.of(
                "xxbreadjambreadyy",
                "jam",
            ),
            Arguments.of(
                "xxbreadjambreadyyy",
                "jam",
            ),
            Arguments.of(
                "xxbreadyy",
                "",
            ),
            Arguments.of(
                "xxbreadbreadjambreadyy",
                "breadjam",
            ),
            Arguments.of(
                "breadAbread",
                "A",
            ),
            Arguments.of(
                "breadbread",
                "",
            ),
            Arguments.of(
                "abcbreaz",
                "",
            ),
            Arguments.of(
                "xyz",
                "",
            ),
            Arguments.of(
                "breadbreaxbread",
                "breax",
            ),
            Arguments.of(
                "breaxbreadybread",
                "y",
            ),
            Arguments.of(
                "breadbreadbreadbread",
                "breadbread",
            ),
        )
    }

    @ArgumentsSource(InputArgumentsProvider::class)
    @ParameterizedTest
    fun `get sandwich test`(str: String, expected: String) {
        val actual = strategy(str)
        assertThat(actual).isEqualTo(expected)
    }
}

class GetSandwichTwoPointersTest : GetSandwichTest<GetSandwich>(GetSandwichTwoPointers())
class GetSandwichTwoSlidingWindowTest : GetSandwichTest<GetSandwich>(GetSandwichTwoSlidingWindow())
