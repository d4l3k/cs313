earlier:
	irmovl 0x1, %eax
	irmovl 0x1, %ebx
	halt

jne:
	irmovl 0x1, %ecx
	irmovl 0x0, %eax
	irmovl 0x0, %ebx
	irmovl 0x1, %edx
	subl   %ecx, %edx
	jne    earlier
	irmovl 0x2, %ecx
	irmovl 0x2, %edx
	halt
