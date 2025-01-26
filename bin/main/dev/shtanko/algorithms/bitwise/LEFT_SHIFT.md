# The Left Shift Operation

The **Left Shift** operation shifts the bits of a number to the left by a specified number of positions. Each left shift by one position is equivalent to multiplying the number by `2`. The operation also fills the empty bits on the right with `0`.

## How It Works

- Each number is represented in binary.
- The operation shifts all the bits of the number to the left by the specified number of positions.
- New bits on the right are filled with `0`.

## Example of Left Shift

Let’s take an integer:

- **A = 5** (binary: `0101`)

Performing a left shift by 1 position:

- **A** = (binary: `0101`)
- **A << 1** = (binary: `1010`)

So, shifting `5` to the left by 1 results in `10` (binary `1010`).

## Explanation

- Shift all bits of `5` to the left by 1 position:
  - The binary of `5` is `0101`.
  - After shifting left by 1 position, the new binary value is `1010`, which is `10` in decimal.

The result of `5 << 1` is `10`.
