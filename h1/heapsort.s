.file    "heapsort.c"
.text
.p2align 4, 15
.globl   heapsort
.type    heapsort, @function

heapsort:
.LFB11:
	.cfi_startproc # Start
	pushq    %rbp # Save base pointer onto stack
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	pushq    %rbx # Save rbx onto stack (since it's used for i/last).
	.cfi_def_cfa_offset 24
	.cfi_offset 3, -24
	movslq   %edi, %rbx # Move edi into rbx (i/last).
	movl     %ebx, %edi # Move i/last into edi (save last)
	subq     $8, %rsp  # allocate 8 bytes on stack
	.cfi_def_cfa_offset 32
	call     heapify_array # heapify_array(last)
	testl    %ebx, %ebx # Test if i < 0
	js       .L1 # Jump to L1 if i < 0
	.p2align 4, 10
	.p2align 3

.L5:
	movl  %ebx, %edi # Move i into edi
	call  extract_max # extract_max(i)
	movl  %eax, heap(, %rbx, 4) # heap[i] = extract_max(i)
	subq  $1, %rbx # Subtract 1 from i
	testl %ebx, %ebx # Check if i < 0
	jns   .L5 # Jump to L5 if i < 0.

.L1:
	addq $8, %rsp # free 8 bytes on stack
	.cfi_def_cfa_offset 24
	popq %rbx # Pop old rbx off stack
	.cfi_def_cfa_offset 16
	popq %rbp # Pop old base pointer off stack
	.cfi_def_cfa_offset 8
	ret  # return (end of function)
.cfi_endproc # End

.LFE11:
	.size    heapsort, .-heapsort
	.ident   "GCC: (GNU) 6.2.1 20160830"
	.section .note.GNU-stack, "", @progbits
