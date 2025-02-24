# The Bitwise XOR Operation

The **Bitwise XOR** (Exclusive OR) operation compares each bit of two numbers and returns a new number. Each bit of the result is set to `1` if and only if the corresponding bits of the two numbers are different. If the bits are the same (both `0` or both `1`), the result at that position is `0`.

## How It Works

- Each number is represented in binary.
- The operation is performed bit by bit, starting from the least significant bit (rightmost) to the most significant bit (leftmost).

## Truth Table for Bitwise XOR

| A   | B   | A XOR B |
|-----|-----|---------|
| 0   | 0   | 0       |
| 0   | 1   | 1       |
| 1   | 0   | 1       |
| 1   | 1   | 0       |

## Bitwise XOR Example

Let’s take two integers:

- **A = 5** (binary: `0101`)
- **B = 3** (binary: `0011`)

Performing a bitwise XOR:

- **A** = (binary: `0101`)  
- **B** = (binary: `0011`)  
- **Result** = (binary: `0110`)

## Explanation

- Compare each bit from left to right:
  - First bit: `0 XOR 0 = 0`
  - Second bit: `1 XOR 0 = 1`
  - Third bit: `0 XOR 1 = 1`
  - Fourth bit: `1 XOR 1 = 0`

So, the result of `5 XOR 3` is `6` (binary `0110`).
