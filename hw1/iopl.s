irmovl 2, %eax
iaddl  4, %eax

# %eax should be 6 now

irmovl 10, %ebx
isubl  4, %ebx

# %ebx should be 6 now

irmovl 10, %ecx
ixorl  12, %ecx

# %ecx should be 6 now

irmovl 14, %edx
iandl  7, %edx

# %edx should be 6 now

irmovl 3, %ebp
imull  2, %ebp

# %ebp should be 6 now

irmovl 3, %esp
imull  2, %esp

# %esp should be 6 now

irmovl 24, %edi
idivl  4, %edi

# %edi should be 6 now

irmovl 26, %esi
imodl  10, %esi

# %esi should be 6 now

halt
