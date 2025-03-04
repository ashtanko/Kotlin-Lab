# Characteristics of Good Unit Tests

- **Readable**: Tests should clearly explain what the unit does. A meaningful test name helps understand behavior without checking the implementation.

- **Fast**: Tests should run in a few seconds to provide quick feedback. Slow tests discourage developers from running them. Use mocks to simulate dependencies and keep tests fast.

- **Independent & Isolated**: Tests should not depend on each other or execution order. Each test should run in its own isolated environment.

- **Correct**: A test should do exactly what its name suggests. Misleading test names create confusion and reduce trust in test results.

- **Environment Agnostic**: Tests should work on any developer machine without setup issues. Avoid dependencies on external files, environment variables, or system configurations.

- **Repeatable**: Running the same test multiple times should always yield the same result. Automate test execution in the build process. Fix failing tests quickly to maintain reliability.
