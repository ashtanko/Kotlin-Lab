🪟 **Sliding Window Algorithm**

The Sliding Window technique is used to reduce time complexity when working with subarrays or substrings of contiguous elements.

🚀 **When to Use**
- Finding max/min/sum in subarrays
- Working with substrings (e.g., longest without repeats)
- Anytime you deal with consecutive elements

🧠 **Core Concept**

Instead of checking all possible subarrays with nested loops:
1. Initialize a window (e.g., left and right pointers).
2. Move the window across the structure.
3. Update the result as the window moves.

📦 **Two Types of Sliding Windows**
- Fixed Size: Max sum of k consecutive elements
- Variable Size: Longest substring without repeating chars

🧪 **Example 1: Fixed Size Window**

**Problem:** Find the maximum sum of any subarray of size k.

```kotlin
fun maxSum(arr: IntArray, k: Int): Int {
    var windowSum = 0
    var maxSum = 0

    for (i in 0 until k) {
        windowSum += arr[i]
    }
    maxSum = windowSum

    for (i in k until arr.size) {
        windowSum += arr[i] - arr[i - k]
        maxSum = maxOf(maxSum, windowSum)
    }

    return maxSum
}
```
✅ Time Complexity: O(n)

🧪 **Example 2: Variable Size Window**

**Problem**: Find the length of the longest substring without repeating characters.

```kotlin
fun lengthOfLongestSubstring(s: String): Int {
    val seen = mutableSetOf<Char>()
    var left = 0
    var maxLen = 0

    for (right in s.indices) {
        while (s[right] in seen) {
            seen.remove(s[left])
            left++
        }
        seen.add(s[right])
        maxLen = maxOf(maxLen, right - left + 1)
    }

    return maxLen
}
```
✅ Time Complexity: O(n)

🧩 **Summary Table**

Here's the fixed markdown code with proper formatting:

```markdown
🪟 **Sliding Window Algorithm**

The Sliding Window technique is used to reduce time complexity when working with subarrays or substrings of contiguous elements.

🚀 **When to Use**
- Finding max/min/sum in subarrays
- Working with substrings (e.g., longest without repeats)
- Anytime you deal with consecutive elements

🧠 **Core Concept**

Instead of checking all possible subarrays with nested loops:
1. Initialize a window (e.g., left and right pointers).
2. Move the window across the structure.
3. Update the result as the window moves.

📦 **Two Types of Sliding Windows**
- Fixed Size: Max sum of k consecutive elements
- Variable Size: Longest substring without repeating chars

🧪 **Example 1: Fixed Size Window**

**Problem:** Find the maximum sum of any subarray of size k.

```kotlin
fun maxSum(arr: IntArray, k: Int): Int {
    var windowSum = 0
    var maxSum = 0

    for (i in 0 until k) {
        windowSum += arr[i]
    }
    maxSum = windowSum

    for (i in k until arr.size) {
        windowSum += arr[i] - arr[i - k]
        maxSum = maxOf(maxSum, windowSum)
    }

    return maxSum
}
```

✅ **Time Complexity:** O(n)

🧪 **Example 2: Variable Size Window**

**Problem:** Find the length of the longest substring without repeating characters.

```kotlin
fun lengthOfLongestSubstring(s: String): Int {
    val seen = mutableSetOf<Char>()
    var left = 0
    var maxLen = 0

    for (right in s.indices) {
        while (s[right] in seen) {
            seen.remove(s[left])
            left++
        }
        seen.add(s[right])
        maxLen = maxOf(maxLen, right - left + 1)
    }

    return maxLen
}
```

✅ **Time Complexity:** O(n)

🔄 **General Sliding Window Template**

```kotlin
var left = 0
for (right in data.indices) {
    // Expand window
    // While condition is invalid, shrink window
    while (!validCondition()) {
        left++
    }
    // Update result if needed
}
```

🧩 **Summary Table**

| Element          | Role                                    |
| ---------------- | --------------------------------------- |
| `left` / `right` | Define current window                   |
| Grow             | Move `right` →                          |
| Shrink           | Move `left` →                           |
| Condition        | Maintain valid state (e.g., no repeats) |
| Update           | Track best result so far                |

