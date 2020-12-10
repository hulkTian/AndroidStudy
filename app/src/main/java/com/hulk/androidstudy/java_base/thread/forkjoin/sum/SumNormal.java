package com.hulk.androidstudy.java_base.thread.forkjoin.sum;

/**
 * Created by tzh on 2020/11/24.
 */
class SumNormal {
    public static void main(String[] args) {
        int count = 0;
        int[]src = MakeArray.makeArray();

        long start = System.currentTimeMillis();
        for (int i = 0; i < src.length; i++) {
            count = count + src[i];
        }
        System.out.println("The count is " + count + " spend time："
                + (System.currentTimeMillis() - start) + "ms");
    }
}
