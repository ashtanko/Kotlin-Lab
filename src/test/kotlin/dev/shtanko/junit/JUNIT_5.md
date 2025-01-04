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
