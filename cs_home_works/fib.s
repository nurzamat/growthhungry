	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 14, 0	sdk_version 14, 4
	.globl	_fib                            ; -- Begin function fib
	.p2align	2
_fib:                                   ; @fib
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
	subs	w8, w8, #2
	cset	w8, ge
	tbnz	w8, #0, LBB0_2
	b	LBB0_1
LBB0_1:
	ldr	w8, [sp, #8]
	stur	w8, [x29, #-4]
	b	LBB0_3
LBB0_2:
	ldr	w8, [sp, #8]
	subs	w0, w8, #1
	bl	_fib
	str	w0, [sp, #4]                    ; 4-byte Folded Spill
	ldr	w8, [sp, #8]
	subs	w0, w8, #2
	bl	_fib
	mov	x8, x0
	ldr	w0, [sp, #4]                    ; 4-byte Folded Reload
	add	w8, w0, w8
	stur	w8, [x29, #-4]
	b	LBB0_3
LBB0_3:
	ldur	w0, [x29, #-4]
	ldp	x29, x30, [sp, #16]             ; 16-byte Folded Reload
	add	sp, sp, #32
	ret
	.cfi_endproc
                                        ; -- End function
.subsections_via_symbols
