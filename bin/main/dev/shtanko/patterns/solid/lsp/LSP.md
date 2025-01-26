# Liskov Substitution Principle (LSP) in Kotlin

The Liskov Substitution Principle is one of the SOLID principles, stating that objects of a superclass should be
replaceable with objects of a subclass without affecting the correctness of the program. This principle ensures that
subtypes adhere to the contract defined by the base type.

## Example: Birds and Flying

Consider a scenario involving different types of birds and their flying capabilities. The Liskov Substitution Principle
guides us to ensure that subtypes can be used interchangeably with their base type.

In this example, we have:

- **`Bird`**: A base class that defines a method `fly()`.
- **`Sparrow`**: A subclass of `Bird` that overrides the `fly()` method to indicate flying.
- **`Ostrich`**: Another subclass of `Bird` that overrides the `fly()` method to indicate inability to fly.

The Liskov Substitution Principle ensures that both `Sparrow` and `Ostrich` can be used as replacements for `Bird`
without affecting the program's correctness.

## Unit Tests

To run the unit tests, use the following command: `./gradlew test`

## Conclusion

The Liskov Substitution Principle encourages designing inheritance hierarchies in a way that subtypes can be substituted
for their base types seamlessly. Adhering to this principle promotes polymorphism, code reusability, and maintainability
in object-oriented systems.

For more information on the Liskov Substitution Principle and other SOLID principles, refer to online resources such as
the [SOLID principles Wikipedia page](https://en.wikipedia.org/wiki/SOLID).
