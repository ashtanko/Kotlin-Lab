# Single Responsibility Principle (SRP) in Kotlin

The Single Responsibility Principle is one of the SOLID principles, stating that a class should have only one reason to
change. In other words, a class should have a single responsibility or job. This principle promotes maintainability,
reusability, and separation of concerns in software design.

## Example: Order Processing

Consider a scenario involving order processing, where orders need to be validated, payments processed, and items
shipped. The Single Responsibility Principle guides us to design classes that have a single responsibility.

In this example, we have:

- **`Order`**: A class representing an order with order items and total amount.
- **`OrderProcessor`**: A class that processes orders, including validation, payment processing, and shipping.

The Single Responsibility Principle ensures that each class has a clear and distinct responsibility, making the codebase
more modular and maintainable.

## Unit Tests

To run the unit tests, use the following command: `./gradlew test`

## Conclusion

The Single Responsibility Principle encourages designing classes that focus on a single responsibility, leading to
better organization, testability, and ease of maintenance. By adhering to SRP, you can create software systems that are
less prone to bugs and easier to evolve.

For more information on the Single Responsibility Principle and other SOLID principles, refer to online resources such
as the [SOLID principles Wikipedia page](https://en.wikipedia.org/wiki/SOLID).
