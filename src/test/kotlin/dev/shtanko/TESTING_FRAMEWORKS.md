# Kotlin Testing Frameworks

## ✅ Unit Testing & JVM Testing
### 1. JUnit 5 (Jupiter) - Standard JVM Testing
- Best for unit and integration tests.
- Supports annotations like `@Test`, `@BeforeEach`, `@AfterEach`.
- Works well with Kotest, Mockito, and Spring.

### 2. Kotest (formerly KotlinTest) - Kotlin-native Testing
- Supports property-based testing, matchers, and coroutines.
- DSL-style syntax for expressive tests.

### 3. Spek - Specification-based Testing
Inspired by RSpec (from Ruby).

## ✅ Mocking Libraries
### 4. Mockito & MockK - Mocking Frameworks
- Mockito: Java-based, integrates with JUnit 5.
- MockK: Kotlin-native, better for coroutines and DSL-friendly.

## ✅ Android UI & Instrumentation Testing
### 5. Espresso - UI Testing for Android

### 6. UIAutomator - System UI Testing
Used for interactions beyond the app (e.g., system UI, notifications).
### 7. Compose Testing - Jetpack Compose UI Testing

## ✅ Benchmarking & Performance Testing
### 8. JMH (Java Microbenchmark Harness) - Benchmark Kotlin Code
Best for measuring performance of functions and loops.
Requires annotation processing (kapt).

### 9. Android Benchmark (Jetpack)
Measures Android performance in native/kotlin code.
Supports BenchmarkRule and Perfetto.

## ✅Turbine
Works with kotlinx.coroutines.Flow for testing emissions.
Eliminates the need for complex collect() calls in tests.
Supports structured assertions like awaitItem(), expectNoEvents(), and awaitComplete().

### ✅ Turbine vs Standard Flow Testing

| Feature                          | Standard Flow (`collect`) | Turbine |
|----------------------------------|-----------------|---------|
| Requires manual `collect()`      | ✅ Yes          | ❌ No  |
| Assertions are simple            | ❌ No           | ✅ Yes  |
| Supports awaiting specific items | ❌ No           | ✅ Yes (`awaitItem()`) |
| Checks for no events             | ❌ No           | ✅ Yes (`expectNoEvents()`) |
| Supports Flow cancellation       | ❌ No           | ✅ Yes (`cancelAndIgnoreRemainingEvents()`) |
| Handles delays in Flows          | ❌ No           | ✅ Yes |
