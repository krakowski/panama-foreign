/*
 * Copyright (c) 2017, 2019, Oracle and/or its affiliates. All rights reserved.
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
 * or visit www.oracle.com if you need additional information or have
 * questions.
 */
package jdk.incubator.vector;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntUnaryOperator;

import jdk.internal.misc.Unsafe;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.Stable;

import static jdk.incubator.vector.VectorIntrinsics.*;
import static jdk.incubator.vector.VectorOperators.*;

// -- This file was mechanically generated: Do not edit! -- //

@SuppressWarnings("cast")  // warning: redundant cast
final class Long64Vector extends LongVector {
    static final LongSpecies VSPECIES =
        (LongSpecies) LongVector.SPECIES_64;

    static final VectorShape VSHAPE =
        VSPECIES.vectorShape();

    static final Class<Long64Vector> VCLASS = Long64Vector.class;

    static final int VSIZE = VSPECIES.vectorBitSize();

    static final int VLENGTH = VSPECIES.laneCount();

    static final Class<Long> ETYPE = long.class;

    // The JVM expects to find the state here.
    private final long[] vec; // Don't access directly, use getElements() instead.

    Long64Vector(long[] v) {
        vec = v;
    }

    // For compatibility as Long64Vector::new,
    // stored into species.vectorFactory.
    Long64Vector(Object v) {
        this((long[]) v);
    }

    static final Long64Vector ZERO = new Long64Vector(new long[VLENGTH]);
    static final Long64Vector IOTA = new Long64Vector(VSPECIES.iotaArray());

    static {
        // Warm up a few species caches.
        // If we do this too much we will
        // get NPEs from bootstrap circularity.
        VSPECIES.dummyVector();
        VSPECIES.withLanes(LaneType.BYTE);
    }

    // Specialized extractors

    @ForceInline
    final @Override
    public LongSpecies vspecies() {
        // ISSUE:  This should probably be a @Stable
        // field inside AbstractVector, rather than
        // a megamorphic method.
        return VSPECIES;
    }


    /*package-private*/
    @ForceInline
    final @Override
    long[] getElements() {
        return VectorIntrinsics.maybeRebox(this).vec;
    }

    // Virtualized constructors

    @Override
    @ForceInline
    public final Long64Vector broadcast(long e) {
        return (Long64Vector) super.broadcastTemplate(e);  // specialize
    }


    @Override
    @ForceInline
    Long64Mask maskFromArray(boolean[] bits) {
        return new Long64Mask(bits);
    }

    @Override
    @ForceInline
    Long64Shuffle iotaShuffle() { return Long64Shuffle.IOTA; }

    @ForceInline
    Long64Shuffle iotaShuffle(int start) { 
        return (Long64Shuffle)VectorIntrinsics.shuffleIota(ETYPE, Long64Shuffle.class, VSPECIES, VLENGTH, start, (val, l) -> new Long64Shuffle(i -> (Long64Shuffle.partiallyWrapIndex(i + val, l))));
    }

    @Override
    @ForceInline
    Long64Shuffle shuffleFromBytes(byte[] reorder) { return new Long64Shuffle(reorder); }

    @Override
    @ForceInline
    Long64Shuffle shuffleFromArray(int[] indexes, int i) { return new Long64Shuffle(indexes, i); }

    @Override
    @ForceInline
    Long64Shuffle shuffleFromOp(IntUnaryOperator fn) { return new Long64Shuffle(fn); }

    // Make a vector of the same species but the given elements:
    @ForceInline
    final @Override
    Long64Vector vectorFactory(long[] vec) {
        return new Long64Vector(vec);
    }

    @ForceInline
    final @Override
    Byte64Vector asByteVectorRaw() {
        return (Byte64Vector) super.asByteVectorRawTemplate();  // specialize
    }

    @ForceInline
    final @Override
    AbstractVector<?> asVectorRaw(LaneType laneType) {
        return super.asVectorRawTemplate(laneType);  // specialize
    }

    // Unary operator

    final @Override
    Long64Vector uOp(FUnOp f) {
        return (Long64Vector) super.uOp(f);  // specialize
    }

    @ForceInline
    final @Override
    Long64Vector uOp(VectorMask<Long> m, FUnOp f) {
        return (Long64Vector) super.uOp((Long64Mask)m, f);  // specialize
    }

    // Binary operator

    @ForceInline
    final @Override
    Long64Vector bOp(Vector<Long> v, FBinOp f) {
        return (Long64Vector) super.bOp((Long64Vector)v, f);  // specialize
    }

    @ForceInline
    final @Override
    Long64Vector bOp(Vector<Long> v,
                     VectorMask<Long> m, FBinOp f) {
        return (Long64Vector) super.bOp((Long64Vector)v, (Long64Mask)m,
                                        f);  // specialize
    }

    // Ternary operator

    @ForceInline
    final @Override
    Long64Vector tOp(Vector<Long> v1, Vector<Long> v2, FTriOp f) {
        return (Long64Vector) super.tOp((Long64Vector)v1, (Long64Vector)v2,
                                        f);  // specialize
    }

    @ForceInline
    final @Override
    Long64Vector tOp(Vector<Long> v1, Vector<Long> v2,
                     VectorMask<Long> m, FTriOp f) {
        return (Long64Vector) super.tOp((Long64Vector)v1, (Long64Vector)v2,
                                        (Long64Mask)m, f);  // specialize
    }

    @ForceInline
    final @Override
    long rOp(long v, FBinOp f) {
        return super.rOp(v, f);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> convertShape(VectorOperators.Conversion<Long,F> conv,
                           VectorSpecies<F> rsp, int part) {
        return super.convertShapeTemplate(conv, rsp, part);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> reinterpretShape(VectorSpecies<F> toSpecies, int part) {
        return super.reinterpretShapeTemplate(toSpecies, part);  // specialize
    }

    // Specialized algebraic operations:

    // The following definition forces a specialized version of this
    // crucial method into the v-table of this class.  A call to add()
    // will inline to a call to lanewise(ADD,), at which point the JIT
    // intrinsic will have the opcode of ADD, plus all the metadata
    // for this particular class, enabling it to generate precise
    // code.
    //
    // There is probably no benefit to the JIT to specialize the
    // masked or broadcast versions of the lanewise method.

    @Override
    @ForceInline
    public Long64Vector lanewise(Unary op) {
        return (Long64Vector) super.lanewiseTemplate(op);  // specialize
    }

    @Override
    @ForceInline
    public Long64Vector lanewise(Binary op, Vector<Long> v) {
        return (Long64Vector) super.lanewiseTemplate(op, v);  // specialize
    }

    /*package-private*/
    @Override
    @ForceInline Long64Vector
    lanewiseShift(VectorOperators.Binary op, int e) {
        return (Long64Vector) super.lanewiseShiftTemplate(op, e);  // specialize
    }

    /*package-private*/
    @Override
    @ForceInline
    public final
    Long64Vector
    lanewise(VectorOperators.Ternary op, Vector<Long> v1, Vector<Long> v2) {
        return (Long64Vector) super.lanewiseTemplate(op, v1, v2);  // specialize
    }

    @Override
    @ForceInline
    public final
    Long64Vector addIndex(int scale) {
        return (Long64Vector) super.addIndexTemplate(scale);  // specialize
    }

    // Type specific horizontal reductions

    @Override
    @ForceInline
    public final long reduceLanes(VectorOperators.Associative op) {
        return super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanes(VectorOperators.Associative op,
                                    VectorMask<Long> m) {
        return super.reduceLanesTemplate(op, m);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op) {
        return (long) super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op,
                                        VectorMask<Long> m) {
        return (long) super.reduceLanesTemplate(op, m);  // specialized
    }

    @Override
    @ForceInline
    public VectorShuffle<Long> toShuffle() {
        long[] a = toArray();
        int[] sa = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            sa[i] = (int) a[i];
        }
        return VectorShuffle.fromArray(VSPECIES, sa, 0);
    }

    // Specialized comparisons

    @Override
    @ForceInline
    public final Long64Mask compare(Comparison op, Vector<Long> v) {
        return super.compareTemplate(Long64Mask.class, op, v);  // specialize
    }

    @Override
    @ForceInline
    public final Long64Mask compare(Comparison op, long s) {
        return super.compareTemplate(Long64Mask.class, op, s);  // specialize
    }


    @Override
    @ForceInline
    public Long64Vector blend(Vector<Long> v, VectorMask<Long> m) {
        return (Long64Vector)
            super.blendTemplate(Long64Mask.class,
                                (Long64Vector) v,
                                (Long64Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Long64Vector slice(int origin, Vector<Long> v) {
        return (Long64Vector) super.sliceTemplate(origin, v);  // specialize
    }

    @Override
    @ForceInline
    public Long64Vector slice(int origin) {
       if ((origin < 0) || (origin >= VLENGTH)) {
         throw new ArrayIndexOutOfBoundsException("Index " + origin + " out of bounds for vector length " + VLENGTH);
       } else {
         Long64Shuffle Iota = iotaShuffle(origin);
         VectorMask<Long> BlendMask = Iota.toVector().compare(VectorOperators.GE, (broadcast((long)(origin))));
         Iota = iotaShuffle(origin);
         return ZERO.blend(this.rearrange(Iota), BlendMask);
       }
    }

    @Override
    @ForceInline
    public Long64Vector unslice(int origin, Vector<Long> w, int part) {
        return (Long64Vector) super.unsliceTemplate(origin, w, part);  // specialize
    }

    @Override
    @ForceInline
    public Long64Vector unslice(int origin, Vector<Long> w, int part, VectorMask<Long> m) {
        return (Long64Vector)
            super.unsliceTemplate(Long64Mask.class,
                                  origin, w, part,
                                  (Long64Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Long64Vector unslice(int origin) {
       if ((origin < 0) || (origin >= VLENGTH)) {
         throw new ArrayIndexOutOfBoundsException("Index " + origin + " out of bounds for vector length " + VLENGTH);
       } else {
         Long64Shuffle Iota = iotaShuffle(-origin);
         VectorMask<Long> BlendMask = Iota.toVector().compare(VectorOperators.GE, (broadcast((long)(0))));
         Iota = iotaShuffle(-origin);
         return ZERO.blend(this.rearrange(Iota), BlendMask);
       }
    }

    @Override
    @ForceInline
    public Long64Vector rearrange(VectorShuffle<Long> s) {
        return (Long64Vector)
            super.rearrangeTemplate(Long64Shuffle.class,
                                    (Long64Shuffle) s);  // specialize
    }

    @Override
    @ForceInline
    public Long64Vector rearrange(VectorShuffle<Long> shuffle,
                                  VectorMask<Long> m) {
        return (Long64Vector)
            super.rearrangeTemplate(Long64Shuffle.class,
                                    (Long64Shuffle) shuffle,
                                    (Long64Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Long64Vector rearrange(VectorShuffle<Long> s,
                                  Vector<Long> v) {
        return (Long64Vector)
            super.rearrangeTemplate(Long64Shuffle.class,
                                    (Long64Shuffle) s,
                                    (Long64Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Long64Vector selectFrom(Vector<Long> v) {
        return (Long64Vector)
            super.selectFromTemplate((Long64Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Long64Vector selectFrom(Vector<Long> v,
                                   VectorMask<Long> m) {
        return (Long64Vector)
            super.selectFromTemplate((Long64Vector) v,
                                     (Long64Mask) m);  // specialize
    }


    @Override
    public long lane(int i) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        return (long) VectorIntrinsics.extract(
                                VCLASS, ETYPE, VLENGTH,
                                this, i,
                                (vec, ix) -> {
                                    long[] vecarr = vec.getElements();
                                    return (long)vecarr[ix];
                                });
    }

    @Override
    public Long64Vector withLane(int i, long e) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        return VectorIntrinsics.insert(
                                VCLASS, ETYPE, VLENGTH,
                                this, i, (long)e,
                                (v, ix, bits) -> {
                                    long[] res = v.getElements().clone();
                                    res[ix] = (long)bits;
                                    return v.vectorFactory(res);
                                });
    }

    // Mask

    static final class Long64Mask extends AbstractMask<Long> {

        private final boolean[] bits; // Don't access directly, use getBits() instead.

        public Long64Mask(boolean[] bits) {
            this(bits, 0);
        }

        public Long64Mask(boolean[] bits, int offset) {
            boolean[] a = new boolean[vspecies().laneCount()];
            for (int i = 0; i < a.length; i++) {
                a[i] = bits[offset + i];
            }
            this.bits = a;
        }

        public Long64Mask(boolean val) {
            boolean[] bits = new boolean[vspecies().laneCount()];
            Arrays.fill(bits, val);
            this.bits = bits;
        }

        @ForceInline
        final @Override
        public LongSpecies vspecies() {
            // ISSUE:  This should probably be a @Stable
            // field inside AbstractMask, rather than
            // a megamorphic method.
            return VSPECIES;
        }

        boolean[] getBits() {
            return VectorIntrinsics.maybeRebox(this).bits;
        }

        @Override
        Long64Mask uOp(MUnOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i]);
            }
            return new Long64Mask(res);
        }

        @Override
        Long64Mask bOp(VectorMask<Long> m, MBinOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            boolean[] mbits = ((Long64Mask)m).getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i], mbits[i]);
            }
            return new Long64Mask(res);
        }

        @ForceInline
        @Override
        public final
        Long64Vector toVector() {
            return (Long64Vector) super.toVectorTemplate();  // specialize
        }

        @Override
        @ForceInline
        public <E> VectorMask<E> cast(VectorSpecies<E> s) {
            AbstractSpecies<E> species = (AbstractSpecies<E>) s;
            if (length() != species.laneCount())
                throw new IllegalArgumentException("VectorMask length and species length differ");
            boolean[] maskArray = toArray();
            // enum-switches don't optimize properly JDK-8161245
            switch (species.laneType.switchKey) {
            case LaneType.SK_BYTE:
                return new Byte64Vector.Byte64Mask(maskArray).check(species);
            case LaneType.SK_SHORT:
                return new Short64Vector.Short64Mask(maskArray).check(species);
            case LaneType.SK_INT:
                return new Int64Vector.Int64Mask(maskArray).check(species);
            case LaneType.SK_LONG:
                return new Long64Vector.Long64Mask(maskArray).check(species);
            case LaneType.SK_FLOAT:
                return new Float64Vector.Float64Mask(maskArray).check(species);
            case LaneType.SK_DOUBLE:
                return new Double64Vector.Double64Mask(maskArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        // Unary operations

        @Override
        @ForceInline
        public Long64Mask not() {
            return (Long64Mask) VectorIntrinsics.unaryOp(
                                             VECTOR_OP_NOT, Long64Mask.class, long.class, VLENGTH,
                                             this,
                                             (m1) -> m1.uOp((i, a) -> !a));
        }

        // Binary operations

        @Override
        @ForceInline
        public Long64Mask and(VectorMask<Long> mask) {
            Objects.requireNonNull(mask);
            Long64Mask m = (Long64Mask)mask;
            return VectorIntrinsics.binaryOp(VECTOR_OP_AND, Long64Mask.class, long.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a & b));
        }

        @Override
        @ForceInline
        public Long64Mask or(VectorMask<Long> mask) {
            Objects.requireNonNull(mask);
            Long64Mask m = (Long64Mask)mask;
            return VectorIntrinsics.binaryOp(VECTOR_OP_OR, Long64Mask.class, long.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a | b));
        }

        // Reductions

        @Override
        @ForceInline
        public boolean anyTrue() {
            return VectorIntrinsics.test(BT_ne, Long64Mask.class, long.class, VLENGTH,
                                         this, this,
                                         (m, __) -> anyTrueHelper(((Long64Mask)m).getBits()));
        }

        @Override
        @ForceInline
        public boolean allTrue() {
            return VectorIntrinsics.test(BT_overflow, Long64Mask.class, long.class, VLENGTH,
                                         this, vspecies().maskAll(true),
                                         (m, __) -> allTrueHelper(((Long64Mask)m).getBits()));
        }

        /*package-private*/
        static Long64Mask maskAll(boolean bit) {
            return bit ? TRUE_MASK : FALSE_MASK;
        }
        static final Long64Mask TRUE_MASK = new Long64Mask(true);
        static final Long64Mask FALSE_MASK = new Long64Mask(false);
    }

    // Shuffle

    static final class Long64Shuffle extends AbstractShuffle<Long> {
        Long64Shuffle(byte[] reorder) {
            super(reorder);
        }

        public Long64Shuffle(int[] reorder) {
            super(reorder);
        }

        public Long64Shuffle(int[] reorder, int i) {
            super(reorder, i);
        }

        public Long64Shuffle(IntUnaryOperator fn) {
            super(fn);
        }

        @Override
        public LongSpecies vspecies() {
            return VSPECIES;
        }

        static {
            // There must be enough bits in the shuffle lanes to encode
            // VLENGTH valid indexes and VLENGTH exceptional ones.
            assert(VLENGTH < Byte.MAX_VALUE);
            assert(Byte.MIN_VALUE <= -VLENGTH);
        }
        static final Long64Shuffle IOTA = new Long64Shuffle(IDENTITY);

        @Override
        @ForceInline
        public Long64Vector toVector() {
            return VectorIntrinsics.shuffleToVector(VCLASS, ETYPE, Long64Shuffle.class, this, VLENGTH,
                                                    (s) -> ((Long64Vector)(((AbstractShuffle<Long>)(s)).toVectorTemplate())));
        }

        @Override
        @ForceInline
        public <F> VectorShuffle<F> cast(VectorSpecies<F> s) {
            AbstractSpecies<F> species = (AbstractSpecies<F>) s;
            if (length() != species.laneCount())
                throw new AssertionError("NYI: Shuffle length and species length differ");
            int[] shuffleArray = toArray();
            // enum-switches don't optimize properly JDK-8161245
            switch (species.laneType.switchKey) {
            case LaneType.SK_BYTE:
                return new Byte64Vector.Byte64Shuffle(shuffleArray).check(species);
            case LaneType.SK_SHORT:
                return new Short64Vector.Short64Shuffle(shuffleArray).check(species);
            case LaneType.SK_INT:
                return new Int64Vector.Int64Shuffle(shuffleArray).check(species);
            case LaneType.SK_LONG:
                return new Long64Vector.Long64Shuffle(shuffleArray).check(species);
            case LaneType.SK_FLOAT:
                return new Float64Vector.Float64Shuffle(shuffleArray).check(species);
            case LaneType.SK_DOUBLE:
                return new Double64Vector.Double64Shuffle(shuffleArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        @Override
        public Long64Shuffle rearrange(VectorShuffle<Long> shuffle) {
            Long64Shuffle s = (Long64Shuffle) shuffle;
            byte[] r = new byte[reorder.length];
            for (int i = 0; i < reorder.length; i++) {
                int ssi = s.reorder[i];
                r[i] = this.reorder[ssi];  // throws on exceptional index
            }
            return new Long64Shuffle(r);
        }
    }

    // ================================================

    // Specialized low-level memory operations.

    @ForceInline
    @Override
    final
    LongVector fromArray0(long[] a, int offset) {
        return super.fromArray0(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    LongVector fromByteArray0(byte[] a, int offset) {
        return super.fromByteArray0(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    LongVector fromByteBuffer0(ByteBuffer bb, int offset) {
        return super.fromByteBuffer0(bb, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoArray0(long[] a, int offset) {
        super.intoArray0(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoByteArray0(byte[] a, int offset) {
        super.intoByteArray0(a, offset);  // specialize
    }

    // End of specialized low-level memory operations.

    // ================================================

}
