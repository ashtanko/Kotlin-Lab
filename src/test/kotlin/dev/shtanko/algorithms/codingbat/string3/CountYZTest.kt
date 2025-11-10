package dev.shtanko.algorithms.codingbat.string3

import java.util.stream.Stream
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class CountYZTest<out T : CountYZ>(private val strategy: T) {
    private object InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of("fez day", 2),
            Arguments.of("day fez", 2),
            Arguments.of("day fyyyz", 2),
        )
    }
}
