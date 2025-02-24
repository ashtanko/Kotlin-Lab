# Test-Driven Development (TDD) Guide

Test-Driven Development (TDD) is a software development process that emphasizes writing tests before implementing the actual code. 
It follows a simple yet powerful cycle:

1. **Red**: Write a failing test for the feature you want to implement.
2. **Green**: Write the minimum amount of code needed to make the test pass.
3. **Refactor**: Refactor the code for readability and maintainability without changing its behavior.

# Why TDD?

- **Increases Confidence**: With tests in place, you can refactor code without fear of breaking existing functionality.  
- **Encourages Simplicity**: Writing only the code necessary to pass the tests keeps the implementation lean.  
- **Improves Design**: Forces you to think about the requirements and design upfront.  
- **Faster Debugging**: When a test fails, you know exactly what went wrong.

# When to Use TDD?

- **New Features**: TDD is ideal when adding new features to ensure they work as intended from the start.  
- **Bug Fixes**: Writing a failing test that reproduces the bug helps ensure it won't reoccur once fixed.  
- **Complex Logic**: Use TDD when implementing complex algorithms or business rules to verify correctness.  
- **Refactoring**: Ensure existing functionality remains intact while improving the code's structure.  
- **Learning a Codebase**: Writing tests while exploring unfamiliar code helps understand its behavior and constraints.  

# Example: Building a Calculator

Let's build a simple calculator using TDD.

## Step 1: Write the Test (RED)

Start by writing a test for the feature you want to implement. For example, let's write a test for an `add` method that sums two numbers:

```kotlin
import org.junit.Test
import kotlin.test.assertEquals

class CalculatorTest {

    @Test
    fun `add should return the sum of two numbers`() {
        val calculator = Calculator()
        val result = calculator.add(2, 3)
        assertEquals(5, result)
    }
}
```
At this stage, the test will fail because the add method and Calculator class do not exist yet.

## Step 2: Write the Code (GREEN)

Now, write the minimum amount of code required to make the test pass. For the `add` method, you can implement the `Calculator` class as follows:

```kotlin
class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }
}
```

## Step 3: Refactor (if necessary)

In this simple case, there's no immediate need to refactor. The code is already clean and straightforward. 
However, if the implementation were complex, you could now safely refactor it without fear of breaking the functionality 
because the test ensures everything works as expected.

For example, if you were to improve or optimize the `add` method in the future, you could do so while ensuring the test still passes.

Refactoring might include:
- Renaming methods or variables for clarity
- Removing duplicate code
- Improving performance or readability

But always remember to run the tests after refactoring to ensure that no functionality was broken.

# Key Points

- **Always start with a failing test**: This ensures you're testing the right behavior before writing any code.
- **Write the simplest code to pass the test**: Focus on implementing only what is necessary to make the test pass.
- **Refactor while ensuring all tests pass**: Improve the code's structure or readability without breaking functionality.
  
With TDD, you'll have a robust, maintainable, and bug-resistant codebase!
