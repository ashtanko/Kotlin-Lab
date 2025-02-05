# Two Pointers Approach

The **Two Pointers Approach** is a common algorithmic technique used to solve a variety of array or sequence-based
problems efficiently. It involves using two pointers (indices) to traverse the array or sequence simultaneously,
manipulating their positions based on certain conditions. This approach is particularly useful for problems that involve
searching for pairs, triplets, or subsequences with specific properties.

## How it works

1. **Sorting (if necessary):** In some cases, it might be helpful to sort the input array before applying the two
   pointers approach. Sorting can help simplify the problem and provide a systematic way to search for the desired
   elements.

2. **Initializing Pointers:** Generally, you'll initialize two pointers, often named `left` and `right`, which are
   initially positioned at different ends of the array or sequence.

3. **Iterating:** As the algorithm progresses, the pointers are moved toward each other while following a set of
   conditions. This movement helps narrow down the search space.

4. **Conditions:** The key to the two pointers approach is to define conditions based on the problem's requirements.
   These conditions determine how the pointers move and when they stop.

5. **Updating Pointers:** Depending on the conditions and the comparison of elements at the pointer positions, you'll
   update the pointers to converge toward the solution.

## Examples

Here are a few examples of problems that can be solved using the two pointers approach:

- [Remove Duplicates from Sorted Array](RemoveDuplicates.kt): Remove duplicate elements from a sorted array
  in-place.
- [Container With Most Water](ContainerWithMostWater.kt): Find the maximum area that can be formed between
  vertical lines.

## Usage

1. Choose a problem that involves searching or comparing elements in an array or sequence.

2. Apply the two pointers approach by initializing pointers and defining conditions for their movement.

3. Implement the solution using Kotlin's features.

4. Test your solution with sample inputs to ensure correctness.
