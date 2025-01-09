| Concept               | Purpose                                                                 | Example                                                                                     |
|-----------------------|-------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| **Dummy**             | To fill required dependencies without usage                            | Passing a `null` or unused mock                                                            |
| **Stub**              | Provide predefined behavior                                            | Returning a fixed value from a method                                                     |
| **Mock**              | Verify interactions                                                   | `verify(mock).methodCalled()`                                                              |
| **Fake**              | Simplified implementation for testing                                  | In-memory database                                                                         |
| **Spy**               | Wrap a real object for partial mocking                                 | Mockito's `spy()`                                                                          |
| **Captor**            | Capture arguments for validation                                       | `ArgumentCaptor`                                                                           |
| **Shunt**             | Replace a dependency with a simplified but equivalent version          | Replace file system calls with in-memory equivalents                                       |
| **Test Harness**      | Support the execution of tests                                         | Custom framework or setup                                                                  |
| **Golden Master**     | Compare output to a known good baseline                                | Regression testing using saved results                                                    |
| **Snapshot Test**     | Verify UI or object consistency                                        | UI snapshot comparisons in Flutter                                                        |
| **Contract Test**     | Ensure services conform to API agreements                             | Validating microservices                                                                   |
| **Data Generator**    | Create realistic data for testing                                      | Faker library                                                                              |
