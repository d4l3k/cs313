


sum_function:
  ld $0x0, r3
	ld $0x6, r4
	ld $start, r5

  geq r4, end
  while:

	j while
	end:
	j r0

start:
	.long 4
	.long 7
	.long 8
	.long 9
	.long 12
	.long 11
