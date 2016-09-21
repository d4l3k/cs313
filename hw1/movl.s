# Setup.
irmovl 9, %eax
irmovl 1, %ebx
irmovl 2, %ecx
irmovl 0, %edx
irmovl 4, %edi
irmovl 8, %esi

# Write 1 and 2 into esp, ebp, edi, esi using new and old instrs.
mrmovl data(%ecx, 4), %esp
mrmovl data(%ebx, 4), %ebp
mrmovl data(%edi), %edi
mrmovl data(%esi), %esi

# Write 9 into data[1] and data[2] using new instr.
rmmovl %eax, data(%ebx, 4)
rmmovl %eax, data(%ecx, 4)

# Write 9 into data[0] and data[3] using old instr.
irmovl 0, %ebx
irmovl 12, %ecx
rmmovl %eax, data(%ebx)
rmmovl %eax, data(%ecx)

halt

.pos 0x2000

# Should be 9, 9, 9, 9 afterwards
data:
	.long 0
	.long 1
	.long 2
	.long 3
