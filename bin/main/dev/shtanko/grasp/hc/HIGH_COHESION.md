# High Cohesion Principle

## Overview

The High Cohesion principle is a fundamental concept in software design that advocates for grouping related
functionality together within a class or component. This principle promotes a design where each class has a well-defined
and focused responsibility, leading to better organization, readability, and maintainability of the codebase.

This README provides an explanation of the High Cohesion principle, its benefits, and strategies for achieving high
cohesion in software design.

## Principle Explanation

High Cohesion encourages designing classes and components in a way that keeps closely related functions and
responsibilities together. A class with high cohesion exhibits a strong, clear purpose and minimizes unrelated
functionalities.

## Example Scenario

Consider a messaging application with the following classes:

1. `Message`: Represents an individual message with attributes like sender, receiver, and content.
2. `MessageStorage`: Manages the storage and retrieval of messages.
3. `MessageStatistics`: Calculates and provides statistics about message usage.

The High Cohesion principle applies here:

- The `Message` class encapsulates data related to messages.
- The `MessageStorage` class is responsible for storing and retrieving messages.
- The `MessageStatistics` class focuses solely on calculating and providing statistics based on message data.

Each class in this example has a distinct and well-defined responsibility, demonstrating the High Cohesion principle.

## Usage

To apply the High Cohesion principle effectively in your software design:

1. Identify related functionalities and responsibilities within your system.
2. Group closely related functions and data together within the same class or module.
3. Keep unrelated functionalities separate to avoid mixing concerns and maintain a clear purpose for each component.

## Benefits

Applying the High Cohesion principle offers several benefits:

- Improved readability: Classes with high cohesion are easier to understand due to their focused responsibilities and
  reduced complexity.

- Enhanced maintainability: Changes to a specific functionality are localized to a single class or module, reducing the
  risk of unintended side effects.

- Better testability: Well-defined responsibilities enable more focused and effective unit testing.

## Strategies for Achieving High Cohesion

1. **Single Responsibility Principle (SRP)**: Design classes with a single, well-defined responsibility.

2. **Extract Methods**: Break down complex methods into smaller, focused sub-methods.

3. **Use Modules and Packages**: Organize related classes into modules or packages based on their cohesive
   functionality.

## Example Code

Here's a simplified Kotlin implementation demonstrating the High Cohesion principle:

```kotlin
class Message(val sender: String, val receiver: String, val content: String)

class MessageStorage {
  private val messages = mutableListOf<Message>()

  fun storeMessage(message: Message) {
    messages.add(message)
  }

  fun retrieveMessages(): List<Message> {
    return messages.toList()
  }
}

class MessageStatistics(private val messageStorage: MessageStorage) {
  fun getMessageCount(): Int {
    val messages = messageStorage.retrieveMessages()
    return messages.size
  }

  fun getAverageMessageLength(): Double {
    val messages = messageStorage.retrieveMessages()
    if (messages.isEmpty()) return 0.0

    val totalLength = messages.sumBy { it.content.length }
    return totalLength.toDouble() / messages.size
  }
}

// Usage
fun main() {
  val messageStorage = MessageStorage()
  val messageStatistics = MessageStatistics(messageStorage)

  val message1 = Message("Alice", "Bob", "Hello!")
  val message2 = Message("Bob", "Alice", "Hi there!")

  messageStorage.storeMessage(message1)
  messageStorage.storeMessage(message2)

  val messageCount = messageStatistics.getMessageCount()
  val averageLength = messageStatistics.getAverageMessageLength()

  println("Message Count: $messageCount")
  println("Average Message Length: $averageLength")
}
```
