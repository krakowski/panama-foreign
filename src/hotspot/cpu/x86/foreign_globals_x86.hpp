/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

#include "asm/macroAssembler.hpp"

#ifndef CPU_X86_VM_FOREIGN_GLOBALS_X86_HPP
#define CPU_X86_VM_FOREIGN_GLOBALS_X86_HPP

#define __ _masm->

#ifdef _LP64

#define INTEGER_ARGUMENT_REGISTERS_NOOF (const size_t)Argument::n_int_register_parameters_c
#define VECTOR_ARGUMENT_REGISTERS_NOOF (const size_t)Argument::n_float_register_parameters_c
#define INTEGER_RETURN_REGISTERS_NOOF 2
#define VECTOR_RETURN_REGISTERS_NOOF 2

extern Register integer_return_registers[];
extern Register integer_argument_registers[];
extern XMMRegister vector_return_registers[];
extern XMMRegister vector_argument_registers[];

#else // _LP64

#define INTEGER_ARGUMENT_REGISTERS_NOOF 0
#define VECTOR_ARGUMENT_REGISTERS_NOOF 0
#define INTEGER_RETURN_REGISTERS_NOOF 1
#define VECTOR_RETURN_REGISTERS_NOOF 1

extern Register integer_return_registers[];
extern XMMRegister vector_return_registers[];

#endif // _LP64

struct VectorRegister {
  static const size_t VECTOR_MAX_WIDTH_BITS = 512; // AVX-512 (64-byte) vector types
  static const size_t VECTOR_MAX_WIDTH_BYTES = VECTOR_MAX_WIDTH_BITS / 8;
  static const size_t VECTOR_MAX_WIDTH_U64S = VECTOR_MAX_WIDTH_BITS / 64;
  static const size_t VECTOR_MAX_WIDTH_FLOATS = VECTOR_MAX_WIDTH_BITS / 32;
  static const size_t VECTOR_MAX_WIDTH_DOUBLES = VECTOR_MAX_WIDTH_BITS / 64;

  union {
    uint8_t bits[VECTOR_MAX_WIDTH_BYTES];
    uint64_t u64[VECTOR_MAX_WIDTH_U64S];
    float f[VECTOR_MAX_WIDTH_FLOATS];
    double d[VECTOR_MAX_WIDTH_DOUBLES];
  };
};

#endif // CPU_X86_VM_FOREIGN_GLOBALS_X86_HPP
