package dev.shtanko.algorithms.slidingwindow

/**
 * Given an integer array arr and an integer k,
 * return the maximum sum of any contiguous subarray of size k.
 */
fun maxSumFixedSizeSlidingWindow(arr: IntArray, k: Int): Int {
    // Edge case: if k is larger than the array size or not positive, return 0
    if (k > arr.size || k <= 0) return 0

    var windowSum = 0
    var maxSum: Int

    // Compute the sum of the first window of size k
    for (i in 0 until k) {
        windowSum += arr[i]
    }

    // Initialize maxSum with the sum of the first window
    maxSum = windowSum

    for (i in k until arr.size) {
        // Add the next element to the window and remove the leftmost one
        windowSum += arr[i] - arr[i - k]

        maxSum = maxOf(maxSum, windowSum)
    }
    return maxSum
}
