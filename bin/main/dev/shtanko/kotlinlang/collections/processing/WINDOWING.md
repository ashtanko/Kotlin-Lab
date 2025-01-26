## `windowed` Function in Kotlin

The `windowed` function in Kotlin allows you to create sliding windows over a list, which is useful for tasks where you
need to process elements in groups or analyze patterns within sequences of data.

### Explanation of `windowed` Function Parameters

- **Size (`size`):**
  Specifies the number of elements in each window.
  
- **Step (`step`):**
  Specifies the step or stride between the start of consecutive windows.
  
- **Partial Windows (`partialWindows`):**
  A boolean flag that determines whether to include partial windows that extend beyond the end of the list.

### Example Usage

```kotlin
fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    
    // Example 1: Sliding windows of size 3 with step 1
    val windows1 = numbers.windowed(size = 3, step = 1, partialWindows = false)
    println("Windows (size = 3, step = 1, partialWindows = false): $windows1")
    
    // Example 2: Sliding windows of size 4 with step 2
    val windows2 = numbers.windowed(size = 4, step = 2, partialWindows = true)
    println("Windows (size = 4, step = 2, partialWindows = true): $windows2")
}
```
## Output
-Windows (size = 3, step = 1, partialWindows = false): [[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5, 6], [5, 6, 7], [6, 7, 8
], [7, 8, 9], [8, 9, 10]]
-Windows (size = 4, step = 2, partialWindows = true): [[1, 2, 3, 4], [3, 4, 5, 6], [5, 6, 7, 8], [7, 8, 9, 10], [9, 10]]

## Explanation of Output
-Example 1: Slides over the list with windows of size 3 and a step of 1. Partial windows are not included 
(partialWindows = false), so the last window [8, 9, 10] is excluded because it's partial.

-Example 2: Slides over the list with windows of size 4 and a step of 2. Partial windows are included 
(partialWindows = true), so the last window [9, 10] is included even though it's partial.

## Use Cases
- Data Analysis: Analyzing sequences of data to detect patterns or trends.
- Signal Processing: Processing signals or time-series data.
- Text Processing: Extracting n-grams for natural language processing tasks.
- The windowed function is versatile and helps simplify operations that involve sliding over collections in a controlled
manner, making it a powerful tool in Kotlin programming.
