# Strict guarantees

## Program Order Rule
The Program Order Rule in the Java Virtual Machine (JVM) is a fundamental concept within the Java Memory Model (JMM). 
It establishes a basic ordering principle for actions within a single thread:
**Definition:**
  * Each action in a thread happens-before every action in that thread that comes later in the program order.
**Explanation:**
  * This rule ensures that within a thread, the order of operations as written in the source code is preserved.
  * It doesn't necessarily mean that the operations will be executed strictly in that order at the hardware level. 
    The JVM and the underlying hardware may reorder instructions for performance optimization as long as the final 
    result is consistent with the program order.
  * This is known as within-thread as-if-serial semantics.
  
**Significance:**
  * The Program Order Rule provides a foundation for understanding how the JVM handles concurrency.
  * It helps to reason about the order of operations within a thread and how those operations interact with other threads and shared memory.
  * It's essential for ensuring the correctness of multithreaded programs, especially when dealing with shared resources and synchronization.

**Example:**
```java
int x = 1;
int y = 2;
```
In this simple example, the Program Order Rule guarantees that the assignment to x happens-before the assignment to y. 
However, the actual order of execution at the hardware level might be different, as long as the final values of x and y are as expected.

**Note:**
  * The Program Order Rule is just one of several happens-before rules defined in the JMM. 
    Other rules, such as the Monitor Lock Rule and the Volatile Variable Rule, help to establish order between actions in different threads.
  * Understanding these rules is crucial for writing correct and efficient concurrent programs in Java.

## Monitor Lock Rule
The Monitor Lock Rule in the Java Virtual Machine (JVM) is a crucial part of the Java Memory Model (JMM). 
It establishes a clear ordering principle for actions involving monitor locks, ensuring data consistency and preventing race conditions in multithreaded programs.

**Definition:**
  * Unlocking a monitor lock happens-before any subsequent lock on the same monitor.
  
**Explanation:**
  * When a thread unlocks a monitor, it essentially releases exclusive access to the shared resource protected by that monitor.
  * The Monitor Lock Rule guarantees that any thread that subsequently acquires the same lock can "see" all the memory writes performed by the thread that previously held the lock.
  * This ensures that modifications made to shared data while holding the lock are visible to other threads that acquire the lock later.
  
**Significance:**
  * Data Consistency: The Monitor Lock Rule is fundamental for maintaining data consistency in multithreaded environments. It prevents situations where one thread modifies data while another thread is unaware of those changes.
  * Race Condition Prevention: By enforcing an order for accessing shared resources, the Monitor Lock Rule helps prevent race conditions, which occur when multiple threads access and modify shared data concurrently, leading to unpredictable results.
  * Synchronization Primitive: Monitor locks, often implemented using the synchronized keyword in Java, are a primary synchronization mechanism. The Monitor Lock Rule ensures that these synchronization constructs behave as expected, providing a foundation for building robust and thread-safe applications.

**Example:**
```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```
In this example, the synchronized keyword ensures that only one thread at a time can execute the increment() method. 
The Monitor Lock Rule guarantees that any modifications made to the count variable by one thread will be visible to other threads when they acquire the lock.

**Key Points:**
  * The Monitor Lock Rule is a crucial component of the JMM for ensuring data consistency and preventing race conditions in multithreaded Java programs.
  * It establishes a clear order for accessing shared resources protected by monitor locks.
  * Understanding the Monitor Lock Rule is essential for writing correct and efficient concurrent code in Java.

## Volatile variable rule

## Thread start rule

## Thread termination rule

## Interruption rule

## Finalizer rule

## Transitivity

