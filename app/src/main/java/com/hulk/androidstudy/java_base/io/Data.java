package com.hulk.androidstudy.java_base.io;

import java.io.Serializable;

/**
 * Created by tzh on 2020/12/16.
 */
public class Data implements Serializable {
    private int n;
    public Data(int n) { this.n = n;}
    public String toString() { return Integer.toString(n); }
}

