package dev.shtanko.algorithms.slidingwindow

/**
 * Finds the length of the longest substring without repeating characters
 * using the Sliding Window technique.
 */
fun lengthOfLongestSubstring(str: String): Int {
    // A set to keep track of characters in the current window
    val seen = mutableSetOf<Char>()
    var left = 0
    // The maximum length of a substring without repeating characters
    var maxLen = 0
    for (right in str.indices) {
        // If the character at 'right' is already in the set, shrink the window from the left
        while (str[right] in seen) {
            seen.remove(str[left]) // Remove the character at the 'left' from the set
            left++  // Move the left pointer forward to shrink the window
        }
        seen.add(str[right])

        // Update maxLen if the current window size is greater
        maxLen = maxOf(maxLen, right - left + 1)
    }

    return maxLen
}
