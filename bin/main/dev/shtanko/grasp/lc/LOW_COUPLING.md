# Low Coupling Principle

## Overview

The Low Coupling principle is a key concept in software design that aims to reduce the dependencies between classes and
components, fostering a more modular, flexible, and maintainable system. By minimizing the connections between different
parts of a software system, the Low Coupling principle helps to achieve better separation of concerns and promotes a
more resilient architecture.

This README provides an explanation of the Low Coupling principle, its benefits, and strategies for achieving low
coupling in software design.

## Principle Explanation

The Low Coupling principle advocates for designing classes and components that have minimal dependencies on each other.
In other words, classes should interact through well-defined interfaces rather than directly depending on specific
implementations. This reduces the ripple effect of changes and enhances the system's adaptability to modifications.

## Example Scenario

Consider a messaging application with two classes:

1. `MessageSender`: Responsible for sending messages to different platforms (e.g., email, SMS).
2. `UserNotification`: Notifies users about messages using various communication channels.

The Low Coupling principle applies here:

- To achieve low coupling, `UserNotification` should rely on the `MessageSender` through a well-defined interface, such
  as an abstract class or an interface. This way, changes to the `MessageSender` implementation won't directly
  affect `UserNotification`.

## Usage

To apply the Low Coupling principle effectively in your software design:

1. Identify areas where classes or components have high dependencies on each other.
2. Introduce well-defined interfaces, abstract classes, or design patterns like dependency injection to decouple
   components.
3. Avoid tight interconnections by reducing direct calls and interactions between classes.

## Benefits

Applying the Low Coupling principle offers several benefits:

- Enhanced maintainability: Changes to one component are less likely to impact other components, reducing the risk of
  unintended consequences.

- Improved testability: Isolated components are easier to test in isolation, allowing for more effective unit testing.

- Flexibility and adaptability: A low-coupled system can accommodate changes and new features more easily, promoting
  system longevity.

## Strategies for Achieving Low Coupling

1. **Dependency Injection**: Pass dependencies explicitly to classes instead of creating them internally.

2. **Use Interfaces and Abstraction**: Rely on interfaces and abstract classes to interact with components, enabling
   interchangeable implementations.

3. **Observer Pattern**: Implement the Observer pattern to allow objects to subscribe and react to changes in other
   objects.

## Example Code

Here's a simplified Kotlin implementation demonstrating the Low Coupling principle:

```kotlin
interface MessageSender {
  fun sendMessage(message: String)
}

class EmailSender : MessageSender {
  override fun sendMessage(message: String) {
    println("Sending email: $message")
  }
}

class UserNotification(private val messageSender: MessageSender) {
  fun notifyUser(message: String) {
    messageSender.sendMessage(message)
  }
}

// Usage
fun main() {
  val emailSender = EmailSender()
  val userNotification = UserNotification(emailSender)

  userNotification.notifyUser("Your message has been delivered.")
}
```
