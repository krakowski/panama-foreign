/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
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

package com.acme;

import java.foreign.annotations.NativeHeader;
import java.foreign.annotations.NativeLocation;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Pointer;
import java.foreign.memory.Struct;

/**
 * This test is platform dependent, as the C type size may vary on platform.
 * Current value is based on x64 with __LP64__.
 */
@NativeHeader(path="simple.h", declarations =
        "global=i32(get=global$get)(set=global$set)(ptr=global$ptr) " +
        "basics=$(anonymous)(get=basics$get)(set=basics$set)(ptr=basics$ptr) " +
        "unsigned_int=u64(get=unsigned_int$get)(set=unsigned_int$set)(ptr=unsigned_int$ptr):$(_unsigned) " +
        "func=($(anonymous)u64:u8)v"
)
public interface simple {
    @NativeLocation(file="simple.h", line=26, column=5)
    public int global$get();
    public void global$set(int arg);
    public Pointer<Integer> global$ptr();

    @NativeLocation(file="simple.h", line=32, column=8)
    @NativeStruct("[" +
            "u8(get=ch$get)(set=ch$set)(ptr=ch$ptr)" +
            "i8(get=sch$get)(set=sch$set)(ptr=sch$ptr)" +
            "i16(get=s$get)(set=s$set)(ptr=s$ptr)" +
            "i32(get=n$get)(set=n$set)(ptr=n$ptr)" +
            "i64(get=l$get)(set=l$set)(ptr=l$ptr)" +
            "i64(get=ll$get)(set=ll$set)(ptr=ll$ptr)" +
            "f32(get=f$get)(set=f$set)(ptr=f$ptr)" +
            "x32" +
            "f64(get=d$get)(set=d$set)(ptr=d$ptr)" +
            "x64" +
            "f128(get=ld$get)(set=ld$set)(ptr=ld$ptr)" +
            "](anonymous)")
    public static interface anonymous extends Struct<anonymous> {
        @NativeLocation(file="simple.h", line=33, column=10)
        public byte ch$get();
        public void ch$set(byte arg);
        public Pointer<Byte> ch$ptr();

        @NativeLocation(file="simple.h", line=34, column=17)
        public byte sch$get();
        public void sch$set(byte arg);
        public Pointer<Byte> sch$ptr();

        @NativeLocation(file="simple.h", line=35, column=11)
        public short s$get();
        public void s$set(short arg);
        public Pointer<Short> s$ptr();

        @NativeLocation(file="simple.h", line=36, column=9)
        public int n$get();
        public void n$set(int arg);
        public Pointer<Integer> n$ptr();

        @NativeLocation(file="simple.h", line=37, column=10)
        public long l$get();
        public void l$set(long arg);
        public Pointer<Long> l$ptr();

        @NativeLocation(file="simple.h", line=38, column=15)
        public long ll$get();
        public void ll$set(long arg);
        public Pointer<Long> ll$ptr();

        @NativeLocation(file="simple.h", line=39, column=11)
        public float f$get();
        public void f$set(float arg);
        public Pointer<Float> f$ptr();

        @NativeLocation(file="simple.h", line=40, column=12)
        public double d$get();
        public void d$set(double arg);
        public Pointer<Double> d$ptr();

        @NativeLocation(file="simple.h", line=41, column=17)
        public double ld$get();
        public void ld$set(double arg);
        public Pointer<Double> ld$ptr();
    }

    @NativeLocation(file="simple.h", line=42, column=3)
    public anonymous basics$get();
    public void basics$set(anonymous arg);
    public Pointer<anonymous> basics$ptr();

    @NativeLocation(file = "simple.h", line = 45, column = 8)
    @NativeStruct("[" +
            "u8(get=b$get)(set=b$set)(ptr=b$ptr)" +
            "u8(get=ch$get)(set=ch$set)(ptr=ch$ptr)" +
            "u16(get=s$get)(set=s$set)(ptr=s$ptr)" +
            "u32(get=n$get)(set=n$set)(ptr=n$ptr)" +
            "u64(get=l$get)(set=l$set)(ptr=l$ptr)" +
            "u64(get=ll$get)(set=ll$set)(ptr=ll$ptr)" +
            "](_unsigned)")
    public static interface _unsigned extends Struct<_unsigned> {
        @NativeLocation(file="simple.h", line=46, column=11)
        public boolean b$get();
        public void b$set(boolean arg);
        public Pointer<Boolean> b$ptr();

        @NativeLocation(file="simple.h", line=47, column=19)
        public byte ch$get();
        public void ch$set(byte c);
        public Pointer<Byte> ch$ptr();

        @NativeLocation(file="simple.h", line=48, column=20)
        public short s$get();
        public void s$set(short s);
        public Pointer<Short> s$ptr();

        @NativeLocation(file="simple.h", line=49, column=18)
        public int n$get();
        public void n$set(int i);
        public Pointer<Integer> n$ptr();

        @NativeLocation(file="simple.h", line=50, column=19)
        public long l$get();
        public void l$set(long l);
        public Pointer<Long> l$ptr();

        @NativeLocation(file="simple.h", line=51, column=24)
        public long ll$get();
        public void ll$set(long l);
        public Pointer<Long> ll$ptr();
    }

    @NativeLocation(file="simple.h", line=52, column=4)
    public Pointer<_unsigned> unsigned_int$get();
    public void unsigned_int$set(Pointer<_unsigned> arg);
    public Pointer<Pointer<_unsigned>> unsigned_int$ptr();

    @NativeLocation(file = "simple.h", line = 54, column = 6)
    public void func(anonymous s, Pointer<Byte> str);
}