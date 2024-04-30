	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 14, 0	sdk_version 14, 4
	.globl	_factorial                      ; -- Begin function factorial
	.p2align	2
_factorial:                             ; @factorial
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #32
	.cfi_def_cfa_offset 32
	stp	x29, x30, [sp, #16]             ; 16-byte Folded Spill
	add	x29, sp, #16
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	str	w0, [sp, #8]
	ldr	w8, [sp, #8]
	subs	w8, w8, #1
	cset	w8, ne
	tbnz	w8, #0, LBB0_2
	b	LBB0_1
LBB0_1:
	mov	w8, #1
	stur	w8, [x29, #-4]
	b	LBB0_3
LBB0_2:
	ldr	w8, [sp, #8]
	str	w8, [sp, #4]                    ; 4-byte Folded Spill
	ldr	w8, [sp, #8]
	subs	w0, w8, #1
	bl	_factorial
	ldr	w8, [sp, #4]                    ; 4-byte Folded Reload
	mul	w8, w8, w0
	stur	w8, [x29, #-4]
	b	LBB0_3
LBB0_3:
	ldur	w0, [x29, #-4]
	ldp	x29, x30, [sp, #16]             ; 16-byte Folded Reload
	add	sp, sp, #32
	ret
	.cfi_endproc
                                        ; -- End function
	.globl	_main                           ; -- Begin function main
	.p2align	2
_main:                                  ; @main
	.cfi_startproc
; %bb.0:
	sub	sp, sp, #64
	.cfi_def_cfa_offset 64
	stp	x29, x30, [sp, #48]             ; 16-byte Folded Spill
	add	x29, sp, #48
	.cfi_def_cfa w29, 16
	.cfi_offset w30, -8
	.cfi_offset w29, -16
	mov	w8, #0
	stur	w8, [x29, #-12]                 ; 4-byte Folded Spill
	stur	wzr, [x29, #-4]
	mov	w8, #5
	stur	w8, [x29, #-8]
	ldur	w9, [x29, #-8]
                                        ; implicit-def: $x8
	mov	x8, x9
	str	x8, [sp, #24]                   ; 8-byte Folded Spill
	ldur	w0, [x29, #-8]
	bl	_factorial
	ldr	x8, [sp, #24]                   ; 8-byte Folded Reload
	mov	x9, sp
	str	x8, [x9]
                                        ; implicit-def: $x8
	mov	x8, x0
	str	x8, [x9, #8]
	adrp	x0, l_.str@PAGE
	add	x0, x0, l_.str@PAGEOFF
	bl	_printf
	ldur	w0, [x29, #-12]                 ; 4-byte Folded Reload
	ldp	x29, x30, [sp, #48]             ; 16-byte Folded Reload
	add	sp, sp, #64
	ret
	.cfi_endproc
                                        ; -- End function
	.section	__TEXT,__cstring,cstring_literals
l_.str:                                 ; @.str
	.asciz	"Factorial of %d is %d"

.subsections_via_symbols
