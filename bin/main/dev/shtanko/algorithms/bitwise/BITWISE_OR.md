# The Bitwise OR Operation

The **Bitwise OR** operation compares each bit of two numbers and returns a new number. Each bit of the result is set to `1` if at least one of the corresponding bits of the two numbers is `1`. If both bits are `0`, the result at that position is `0`.

## How It Works

- Each number is represented in binary.
- The operation is performed bit by bit, starting from the least significant bit (rightmost) to the most significant bit (leftmost).

## Truth Table for Bitwise OR

| A   | B   | A OR B |
|-----|-----|--------|
| 0   | 0   | 0      |
| 0   | 1   | 1      |
| 1   | 0   | 1      |
| 1   | 1   | 1      |

## Bitwise OR Example

Let’s take two integers:

- **A = 5** (binary: `0101`)
- **B = 3** (binary: `0011`)

Performing a bitwise OR:

- **A** = (binary: `0101`)  
- **B** = (binary: `0011`)  
- **Result** = (binary: `0111`)

## Explanation

- Compare each bit from left to right:
  - First bit: `0 OR 0 = 0`
  - Second bit: `1 OR 0 = 1`
  - Third bit: `0 OR 1 = 1`
  - Fourth bit: `1 OR 1 = 1`

So, the result of `5 OR 3` is `7` (binary `0111`).
