chain:
  irmovl $stack, %esp
  irmovl $0x0, %eax
  irmovl $0x0, %ecx
  irmovl $0x0, %edx
  irmovl $0x0, %ebx
  call cr1
  halt

cr1:
  irmovl $0x1, %eax
  call cr2
  irmovl $0x1, %ecx
  ret
  halt

cr2:
  irmovl $0x1, %edx
  call cr3
  irmovl $0x1, %ebx
  ret
  halt

cr3:
  irmovl $0x2, %eax
  call cr4
  irmovl $0x2, %ecx
  ret
  halt

cr4:
  irmovl $0x2, %edx
  irmovl $0x2, %ebx
  ret
  halt
  

.pos 0x1000
stack:
	.long 0x00000000
