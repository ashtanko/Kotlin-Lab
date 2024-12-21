# Dependency Inversion Principle (DIP) in Kotlin

The Dependency Inversion Principle is one of the SOLID principles, emphasizing that high-level modules should not depend
on low-level modules. Both should depend on abstractions. This promotes decoupling and flexibility in software design.

## Example: Notification Service

Consider a scenario where you need to send notifications using different channels, such as email and SMS. The Dependency
Inversion Principle can guide you in designing a flexible and extensible notification system.

In this example, we have:

- **`MessageSender`**: An interface that defines the contract for sending messages.
- **`EmailSender`**: A class that implements `MessageSender` to send email messages.
- **`SmsSender`**: A class that implements `MessageSender` to send SMS messages.
- **`NotificationService`**: A class that uses the `MessageSender` interface to send notifications.

The Dependency Inversion Principle allows us to inject different implementations of `MessageSender`
into `NotificationService` without modifying its code.

## Unit Tests

To run the unit tests, use the following command: `./gradlew test`

## Conclusion

The Dependency Inversion Principle encourages designing modules that rely on abstractions rather than concrete
implementations. This promotes modularity, reusability, and testability in your software. By adhering to DIP, you can
create more flexible and maintainable systems that are easier to extend and adapt.

For more information on the Dependency Inversion Principle and other SOLID principles, refer to online resources such as
the [SOLID principles Wikipedia page](https://en.wikipedia.org/wiki/SOLID).
