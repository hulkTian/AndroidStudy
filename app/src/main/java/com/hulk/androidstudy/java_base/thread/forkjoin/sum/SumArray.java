package com.hulk.androidstudy.java_base.thread.forkjoin.sum;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by tzh on 2020/11/24.
 */
public class SumArray {
    private static class SumTask extends RecursiveTask<Integer> {
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
        private final int[] src;
        private final int fromIndex;
        private final int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                int count = 0;
                for (int i = fromIndex; i <= toIndex; i++) {
                    count = count + src[i];
                }
                return count;
            } else {
                int mid = (fromIndex + toIndex) / 2;
                SumTask left = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid + 1, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        int[] src = MakeArray.makeArray();
        ForkJoinPool pool = new ForkJoinPool();
        SumTask innerFind = new SumTask(src, 0, src.length-1);
        long start = System.currentTimeMillis();
//        pool.invoke(innerFind);
        pool.submit(innerFind);
        System.out.println("The count is " + innerFind.join() + " spend time：" + (System.currentTimeMillis() - start) + "ms");
    }
}
