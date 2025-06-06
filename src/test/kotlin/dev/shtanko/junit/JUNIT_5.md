# ✅ JUnit 5 - The Latest Java Testing Framework

## 🔹 Key Points

- **JUnit 5** is the latest version of the popular Java testing framework.
- Provides a **flexible and powerful** set of features for writing and running tests.
- **Assertions** verify expected behavior.
- **Annotations** control test execution order and behavior.
- **Parameterized tests** allow testing with different input values.

---

## 🛠 1. Assertions

| Assertion                                  | Description                                               |
|--------------------------------------------|-----------------------------------------------------------|
| `assertEquals(expected, actual)`           | Checks if two values are equal.                           |
| `assertNotEquals(unexpected, actual)`      | Checks if two values are not equal.                       |
| `assertTrue(condition)`                    | Ensures a condition is `true`.                            |
| `assertFalse(condition)`                   | Ensures a condition is `false`.                           |
| `assertNull(object)`                       | Ensures an object is `null`.                              |
| `assertNotNull(object)`                    | Ensures an object is not `null`.                          |
| `assertSame(expected, actual)`             | Checks if two references point to the same object.        |
| `assertNotSame(unexpected, actual)`        | Checks if two references do not point to the same object. |
| `assertArrayEquals(expected, actual)`      | Compares two arrays for equality.                         |
| `assertThrows(exceptionClass, executable)` | Ensures an exception is thrown.                           |

---

## 🏷 2. Test Annotations

| Annotation    | Purpose                            |
|---------------|------------------------------------|
| `@Test`       | Marks a method as a test.          |
| `@BeforeEach` | Runs before each test method.      |
| `@AfterEach`  | Runs after each test method.       |
| `@BeforeAll`  | Runs once before all test methods. |
| `@AfterAll`   | Runs once after all test methods.  |
| `@Disabled`   | Skips a test method/class.         |

---

## 📌 3. Test Suites

| Annotation                                             | Purpose                               |
|--------------------------------------------------------|---------------------------------------|
| `@RunWith(JUnitPlatform.class)`                        | Used in JUnit 4 to run JUnit 5 tests. |
| `@SelectPackages("com.example")`                       | Runs all tests from a package.        |
| `@SelectClasses({TestClass1.class, TestClass2.class})` | Runs specific test classes.           |

---

## 🔄 4. Parameterized Tests

| Annotation           | Purpose                                          |
|----------------------|--------------------------------------------------|
| `@ParameterizedTest` | Marks a test as parameterized.                   |
| `@ValueSource`       | Supplies simple values (e.g., numbers, strings). |
| `@MethodSource`      | Supplies values from a static method.            |
| `@CsvSource`         | Supplies values from a CSV-formatted string.     |
| `@ArgumentsSource`   | Uses a custom argument provider.                 |

---

## 🎭 5. Assumptions

| Method                   | Purpose                             |
|--------------------------|-------------------------------------|
| `assumeTrue(condition)`  | Skips test if condition is `false`. |
| `assumeFalse(condition)` | Skips test if condition is `true`.  |

---

## 🔌 6. Extensions

JUnit 5 supports **extensions** that modify test execution behavior.

| Annotation                          | Purpose                                |
|-------------------------------------|----------------------------------------|
| `@ExtendWith(ExtensionClass.class)` | Registers extensions.                  |
| `@RegisterExtension`                | Registers a single extension instance. |

---

## ✨ 7. Custom Messages in Assertions

You can provide **custom error messages** to assertions for better debugging:

```java
assertEquals(4, 2 + 2, "Addition should be 4");

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

## 🔗 JUnit 5 Modules

| Module                             | Description                                            |
|------------------------------------|--------------------------------------------------------|
| `junit-jupiter-api`                | Defines the API for writing tests.                     |
| `junit-platform-launcher`          | API for discovering, filtering, and executing tests.   |
| `junit-platform-engine`            | API for writing custom `TestEngine` implementations.   |
| `junit-jupiter-engine`             | Implementation of `junit-platform-engine` for JUnit 5. |
| `junit-vintage-engine`             | Supports running JUnit 3 and JUnit 4 tests.            |
| `junit-platform-commons`           | Contains shared utilities across modules.              |
| `junit-platform-console`           | Provides a console-based test runner.                  |
| `junit-platform-gradle-plugin`     | Gradle plugin for running JUnit 5 tests.               |
| `junit-platform-surefire-provider` | Enables **Maven integration** for JUnit 5.             |

🚀 **JUnit 5 offers modern testing features with flexibility and power.**  
Use these tools to **write better, more maintainable tests!** ✅  
