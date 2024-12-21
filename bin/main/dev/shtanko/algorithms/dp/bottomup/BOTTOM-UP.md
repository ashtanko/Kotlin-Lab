# Bottom-Up Dynamic Programming

The bottom-up dynamic programming approach is a technique used to solve problems by starting with the smallest
subproblems and iteratively building up to the main problem. It is often used to optimize algorithms and avoid redundant
calculations. This README provides an overview of the bottom-up approach and how to use it.

## Key Concepts

### 1. Iterative Solution

In the bottom-up approach, you start by solving the smallest subproblems first and use their solutions to calculate
solutions for larger subproblems. It involves using loops or iterations to build up to the main problem.

### 2. Tabulation

Tabulation is a common technique in bottom-up dynamic programming. It involves creating a table (usually an array or a
matrix) to store intermediate results for subproblems. Each cell in the table corresponds to a subproblem, and you fill
in the table from the smallest subproblems to the main problem.

### 3. No Recursion

Unlike the top-down approach (memoization) which uses recursion, the bottom-up approach does not involve function calls
or recursive calls. It is often more space-efficient and may have better performance for some problems.

## How to Use Bottom-Up Dynamic Programming

To use the bottom-up dynamic programming approach, follow these steps:

1. **Define Subproblems:** Break down the main problem into smaller, non-overlapping subproblems. Ensure that
   subproblems can be solved independently.

2. **Create a Table:** Create a data structure (usually an array or a matrix) to store intermediate results. The
   dimensions of the table depend on the nature of the problem.

3. **Initialize Base Cases:** Set the initial values in the table to represent the solutions to the smallest
   subproblems (base cases).

4. **Iterate:** Use loops or iterations to fill in the table from the smallest subproblems to the main problem.
   Calculate the solution for each subproblem based on previously computed solutions.

5. **Final Result:** The final result for the main problem can be found in the last cell of the table or in a specific
   location depending on the problem.

## Examples

Explore the [examples](examples/) directory of this repository for practical examples of bottom-up dynamic programming
in various programming languages. These examples cover classic problems such as:

- Fibonacci sequence calculation
- Coin change problem
- Longest common subsequence
- Knapsack problem

## Resources

For further learning about bottom-up dynamic programming, consider the following resources:

- [GeeksforGeeks Dynamic Programming](https://www.geeksforgeeks.org/dynamic-programming/)
- [Coursera - Algorithmic Toolbox](https://www.coursera.org/learn/algorithmic-toolbox)

## Contributing

If you have additional examples or insights related to bottom-up dynamic programming, feel free to contribute to this
repository by opening a pull request.

Happy coding!
