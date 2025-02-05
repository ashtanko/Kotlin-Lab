/*
 * Copyright 2021 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.patterns.behavioral.interpreter.example1

import java.util.function.BiFunction
import java.util.function.IntBinaryOperator
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class ExpressionTest<T : Expression>(
    private val factory: BiFunction<NumberExpression, NumberExpression, T>,
    private val expectedToString: String? = null,
) {
    /**
     * Generate inputs ranging from -10 to 10 for both input params and calculate the expected result
     *
     * @param resultCalc The function used to calculate the expected result
     * @return A stream with test entries
     */
    open fun prepareParameters(resultCalc: IntBinaryOperator): Stream<Arguments> {
        val testData = ArrayList<Arguments>()
        for (i in -10..9) {
            for (j in -10..9) {
                testData.add(
                    Arguments.of(
                        NumberExpression(i),
                        NumberExpression(j),
                        resultCalc.applyAsInt(i, j),
                    ),
                )
            }
        }
        return testData.stream()
    }

    /**
     * Create a new set of test entries with the expected result
     *
     * @return The list of parameters used during this test
     */
    abstract fun expressionProvider(): Stream<Arguments>

    /**
     * Verify if the expression calculates the correct result when calling [T#interpret]
     */
    @ParameterizedTest
    @MethodSource("expressionProvider")
    open fun testInterpret(first: NumberExpression, second: NumberExpression, result: Int) {
        val expression: T = factory.apply(first, second)
        Assertions.assertNotNull(expression)
        Assertions.assertEquals(result, expression.interpret())
    }

    /**
     * Verify if the expression has the expected [T#toString] value
     */
    @ParameterizedTest
    @MethodSource("expressionProvider")
    open fun testToString(first: NumberExpression, second: NumberExpression) {
        val expression: T = factory.apply(first, second)
        Assertions.assertNotNull(expression)
        Assertions.assertEquals(expectedToString, expression.toString())
    }
}
