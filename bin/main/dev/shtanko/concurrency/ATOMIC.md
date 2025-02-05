# Atomic

In the Java Virtual Machine (JVM), the term "atomic" refers to operations that are performed as a single, indivisible 
step. This means that the operation is completed without any interference or observation from other threads. 
Atomicity is a crucial concept in concurrent programming, ensuring that certain actions happen entirely or not at all, 
preventing race conditions and data corruption.

## Atomic Operations

1. **Primitive Types**:
   - **Read and write operations** on primitive types like `int`, `short`, `byte`, `char`, `float`, and `boolean` are
      atomic, except for `long` and `double` on some architectures. On 64-bit architectures, `long` and `double` are 
      typically atomic, but on 32-bit architectures, they might not be.

2. **Volatile Variables**:
   - Declaring a variable `volatile` ensures that reads and writes to that variable are atomic. Additionally, it ensures
      visibility, meaning changes to a volatile variable by one thread are immediately visible to other threads.

3. **Atomic Classes in `java.util.concurrent.atomic`**:
   - Java provides classes in the `java.util.concurrent.atomic` package for performing atomic operations on variables. 
      These classes include:
     - `AtomicInteger`
     - `AtomicLong`
     - `AtomicBoolean`
     - `AtomicReference`
   - These classes provide methods like `get()`, `set()`, `incrementAndGet()`, `compareAndSet()`, etc., which are 
      implemented using low-level atomic instructions provided by the hardware, ensuring atomicity.

### Why Atomicity is Important

Atomicity is essential in multi-threaded environments to ensure that operations are completed without interference.
Without atomic operations, multiple threads could read and write shared data simultaneously, leading to inconsistent and
unpredictable results.

### Example: Using `AtomicInteger`

Here's an example of using `AtomicInteger` to perform atomic operations:

```kotlin
import java.util.concurrent.atomic.AtomicInteger

object AtomicExample {
    private val counter = AtomicInteger(0)

    @JvmStatic
    fun main(args: Array<String>) {
        val task = Runnable {
            for (i in 0 until 1000) {
                counter.incrementAndGet()
            }
        }

        val t1 = Thread(task)
        val t2 = Thread(task)

        t1.start()
        t2.start()

        try {
            t1.join()
            t2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        println("Counter: ${counter.get()}")
    }
}
```

Atomic operations in the JVM are implemented using low-level mechanisms provided by the hardware and the operating 
system. These mechanisms ensure that atomic operations are performed without interference from other threads. Here is a 
detailed look at how atomic operations work under the hood:

Hardware Support
Modern processors provide specific instructions that ensure atomicity. These instructions are part of the CPU's 
instruction set and are used to perform atomic read-modify-write operations. Examples of such instructions include:

* Compare-and-Swap (CAS): This instruction compares the value at a memory location with an expected value and, only if 
they match, modifies the memory location to a new value. This is done atomically, ensuring no other thread can interfere 
during the operation.

* Load-Link/Store-Conditional (LL/SC): These instructions work together to provide atomicity. The load-link reads the
value, and the store-conditional writes the value only if no other write has occurred to the memory location since the
load-link.
