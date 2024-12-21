# Processes in JVM

## 1. Class Loading

The JVM loads compiled Java bytecode files (`.class` files) into memory:

- **Loading**: Reads the `.class` file and creates a `java.lang.Class` object.
- **Linking**: Verifies bytecode, prepares memory for static fields, resolves symbolic references.
  
## 2. Memory Management

JVM manages memory in different areas:

- **Heap**: Runtime object allocation (instances, arrays).
- **Stack**: Thread-specific method call and local variable memory.
- **Method Area**: Stores class structures, method bytecode, constants, static fields.

## 3. Execution Engine

Executes bytecode instructions:

- **Interpreter**: Interprets bytecode sequentially.
- **Just-In-Time (JIT) Compiler**: Compiles frequently executed bytecode into native code for performance.
- **Garbage Collector**: Manages memory by reclaiming unused objects.

## 4. Runtime Data Areas

Stores data during program execution:

- **Heap**: Object allocation.
- **Stack**: Thread-specific method call frames and local variables.
- **Method Area**: Shared class metadata.

## 5. Security

Enforces security:

- **Bytecode Verifier**: Checks bytecode validity against Java rules.
- **Security Manager**: Enforces access control policies.

## 6. Exception Handling

Manages runtime exceptions and errors for program stability.

## 7. Native Interface

Allows Java to interact with native libraries (e.g., C/C++).

Understanding these processes helps optimize Java applications and ensures platform-independent execution through JVM abstraction.
