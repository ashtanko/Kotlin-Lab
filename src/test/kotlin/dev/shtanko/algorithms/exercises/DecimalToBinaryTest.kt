package dev.shtanko.algorithms.exercises

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal class DecimalToBinaryTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(1, "1"),
            Arguments.of(2, "10"),
            Arguments.of(3, "11"),
            Arguments.of(4, "100"),
            Arguments.of(5, "101"),
            Arguments.of(6, "110"),
            Arguments.of(7, "111"),
            Arguments.of(8, "1000"),
            Arguments.of(9, "1001"),
            Arguments.of(10, "1010"),
            Arguments.of(100, "1100100"),
            Arguments.of(100, "1100100"),
            Arguments.of(101, "1100101"),
            Arguments.of(102, "1100110"),
            Arguments.of(103, "1100111"),
            Arguments.of(104, "1101000"),
            Arguments.of(105, "1101001"),
            Arguments.of(106, "1101010"),
            Arguments.of(107, "1101011"),
            Arguments.of(108, "1101100"),
            Arguments.of(109, "1101101"),
            Arguments.of(110, "1101110"),
            Arguments.of(500, "111110100"),
            Arguments.of(900, "1110000100"),
            Arguments.of(1000, "1111101000"),
            Arguments.of(9999, "10011100001111"),
            Arguments.of(10_000, "10011100010000")
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `decimal to binary string test`(n: Int, expected: String) {
        val actual = DecimalToBinary().perform(n)
        MatcherAssert.assertThat(actual, CoreMatchers.equalTo(expected))
    }
}
