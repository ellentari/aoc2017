package com.adventofcode.common;

public class Tuple<X, Y, Z> {

    public final X _1;
    public final Y _2;
    public final Z _3;

    public Tuple(X x, Y y) {
        this(x, y, null);
    }

    public Tuple(X x, Y y, Z z) {
        this._1 = x;
        this._2 = y;
        this._3 = z;
    }
}
