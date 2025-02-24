# The Bitwise AND Operation

The **Bitwise AND** operation compares each bit of two numbers and returns a new number. Each bit of the result is set to `1` if and only if the corresponding bits of both numbers are `1`. If either or both bits are `0`, the result at that position is `0`.

## How It Works

- Each number is represented in binary.
- The operation is performed bit by bit, starting from the least significant bit (rightmost) to the most significant bit (leftmost).

## Truth Table for Bitwise AND

| A   | B   | A AND B |
|-----|-----|---------|
| 0   | 0   | 0       |
| 0   | 1   | 0       |
| 1   | 0   | 0       |
| 1   | 1   | 1       |

## Bitwise AND Example

Let’s take two integers:

- **A = 5** (binary: `0101`)
- **B = 3** (binary: `0011`)

Performing a bitwise AND:

- **A** = (binary: `0101`)  
- **B** = (binary: `0011`)  
- **Result** = (binary: `0001`)

## Explanation

- Compare each bit from left to right:
  - First bit: `0 AND 0 = 0`
  - Second bit: `1 AND 0 = 0`
  - Third bit: `0 AND 1 = 0`
  - Fourth bit: `1 AND 1 = 1`

So, the result of `5 AND 3` is `1` (binary `0001`).
