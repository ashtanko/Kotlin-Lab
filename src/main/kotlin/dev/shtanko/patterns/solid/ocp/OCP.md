# Open-Closed Principle (OCP) in Kotlin

The Open-Closed Principle is one of the SOLID principles, stating that software entities (classes, modules, functions,
etc.) should be open for extension but closed for modification. This principle encourages designing systems in a way
that allows new functionality to be added without altering existing code.

## Example: Shapes and Area Calculation

Consider a scenario involving different shapes and their area calculations. The Open-Closed Principle guides us to
design classes that can be extended to add new shapes without modifying the existing `AreaCalculator` class.

In this example, we have:

- **`Shape`**: An interface that defines a method `calculateArea()`.
- **`Circle`**: A class that implements `Shape` to calculate the area of a circle.
- **`Rectangle`**: Another class that implements `Shape` to calculate the area of a rectangle.
- **`AreaCalculator`**: A class that calculates the total area of a list of shapes.

The Open-Closed Principle ensures that new shapes can be added by extending the `Shape` interface and implementing
the `calculateArea()` method, without altering the existing `AreaCalculator` class.

## Unit Tests

To run the unit tests, use the following command: `./gradlew test`

## Conclusion

The Open-Closed Principle promotes extensibility and maintainability by allowing new features to be added without
changing existing code. By adhering to OCP, you can create software systems that are less prone to bugs and easier to
adapt to changing requirements.

For more information on the Open-Closed Principle and other SOLID principles, refer to online resources such as
the [SOLID principles Wikipedia page](https://en.wikipedia.org/wiki/SOLID).
