irmovl 8, %ebx
call   *table(%ebx), %edi
irmovl 30, %edx
call   fun2
irmovl 40, %edx
halt

fun:
	pushl  %edi
	irmovl 20, %ecx
	ret

fun2:
	irmovl 30, %ecx
	ret

.pos 0x2000

table:
# some random stuff that's 8 bytes long
.long 10
.long 20
.long fun

