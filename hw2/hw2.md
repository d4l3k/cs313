# CS313 - HW2
Tristan Rice, q7w9a, 25886145

## Part 1
### 1. Find the bugs

See bugs.txt.

### 2. Efficiency

cCnt:  0x1e31      7729
iCnt:  0xbab       2987

7729 cycles / 2987 instructions = 2.59 cycles/instruction (CPI)

## Part 2

Changes work with fwdOrder, srcAHzd, sBHazard, aLoadUse, bLoadUse. Doesn't seem
to work with cmov. Maybe because it's not forwarding the condition codes.
