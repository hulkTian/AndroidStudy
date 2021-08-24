package com.hulk.androidstudy.java_base.thread.forkjoin.sum;

import java.util.Random;

/**
 * Created by tzh on 2020/11/24.
 */
public class MakeArray {
    public static final int ARRAY_LENGTH = 10;
    public final static int THRESHOLD = 47;

    public static int[] makeArray() {
        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            result[i] = r.nextInt(ARRAY_LENGTH);
        }
        return result;
    }
}
