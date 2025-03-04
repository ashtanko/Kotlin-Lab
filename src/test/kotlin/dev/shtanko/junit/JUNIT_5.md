**Key Points**

* JUnit 5 is the latest version of the popular Java testing framework.
* It provides a flexible and powerful set of features for writing and running tests.
* Assertions are used to verify the expected behavior of code.
* Annotations control the execution order and behavior of tests.
* Parameterized tests allow you to easily test with different input values.

**1. Assertions**

* `assertEquals(expected, actual)`: Asserts that two objects are equal.
* `assertNotEquals(unexpected, actual)`: Asserts that two objects are not equal.
* `assertTrue(condition)`: Asserts that a condition is true.
* `assertFalse(condition)`: Asserts that a condition is false.
* `assertNull(object)`: Asserts that an object is null.
* `assertNotNull(object)`: Asserts that an object is not null.
* `assertSame(expected, actual)`: Asserts that two object references point to the same object in memory.
* `assertNotSame(unexpected, actual)`: Asserts that two object references do not point to the same object in memory.
* `assertArrayEquals(expected, actual)`: Asserts that two arrays are equal.
* `assertThrows(exceptionClass, executable)`: Asserts that a specific exception is thrown when the given executable is run.

**2. Test Annotations**

* `@Test`: Marks a method as a test method.
* `@BeforeEach`: Executes before each test method in the class.
* `@AfterEach`: Executes after each test method in the class.
* `@BeforeAll`: Executes once before all test methods in the class.
* `@AfterAll`: Executes once after all test methods in the class.
* `@Disabled`: Disables a test method or class.

**3. Test Suites**

* `@RunWith(JUnitPlatform.class)`: Used in JUnit 4 to run JUnit 5 tests.
* `@SelectPackages`: Selects all tests within a given package.
* `@SelectClasses`: Selects specific test classes.

**4. Parameterized Tests**

* `@ParameterizedTest`: Marks a method as a parameterized test.
* `@ValueSource`: Provides a source of values for a parameter.
* `@MethodSource`: Provides a source of values from a method.
* `@CsvSource`: Provides a source of values from a CSV string.
* `@ArgumentsSource`: Provides a source of values from a custom argument provider.

**5. Assumptions**

* `assumeTrue(condition)`: Skips the test if the condition is false.
* `assumeFalse(condition)`: Skips the test if the condition is true.

**6. Extensions**

* JUnit 5 supports extensions that can modify test execution behavior.
* Examples:
    * `@ExtendWith`: Registers extensions to be used in a test class.
    * `@RegisterExtension`: Registers a single extension.

**7. Assertions with Custom Messages**

* You can provide custom error messages to assertion methods:
    * `assertEquals(expected, actual, "Custom error message")`

**Example**

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyTest {

    @Test
    void testAddition() {
        int result = 2 + 2;
        assertEquals(4, result, "Addition should be 4"); 
    }
}
```

## The main modules in JUnit 5 are:

- **junit-jupiter-api**: This module defines the API that you need to
write your tests.

- **junit-platform-launcher**: This module defines the launcher API
that external tool use. Launchers can be used to discover, filter,
and execute tests.

- **junit-platform-engine**: This provides the API that you can use
to write your own TestEngine. TestEngine is responsible for the
discovery and execution of tests.

- **junit-jupiter-engine**: It is the implementation of junit-platform-
engine API for JUnit 5.

- **junit-vintage-engine**: It is the implementation of junit-platform-
engine API for JUnit 3 and JUnit 4.

- **junit-platform-commons**: It contains all the utilities which are
used across different modules.

- **junit-platform-console**: This provides an implementation of a
launcher called ConsoleLauncher. ConsoleLauncher is a stand-
alone application used to launch JUnit platform from the console.

- **junit-platform-gradle-plugin**: This is a Gradle plug-in that can
be used to run JUnit 5 tests.

- **junit-platform-surefire-provider**: This module provides Maven
integration for JUnit 5.
