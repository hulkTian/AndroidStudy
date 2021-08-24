package com.hulk.androidstudy.java_base.thread.forkjoin.sort;

import com.hulk.androidstudy.java_base.thread.forkjoin.sum.MakeArray;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * forkJoin实现的归并排序
 * Created by tzh on 2020/11/24.
 */
class FJSort {
    private static class SumTask extends RecursiveTask<int[]> {
        private final static int THERSHOLD = 2;
        private int[] src;

        public SumTask(int[] src) {
            this.src = src;
        }

        @Override
        protected int[] compute() {
            if (src.length <= THERSHOLD) {
                return InsertionSort.sort(src);
            } else {
                int mid = src.length/2;
                SumTask lefTask = new SumTask(Arrays.copyOfRange(src, 0, mid));
                SumTask rightTask = new SumTask(Arrays.copyOfRange(src, mid+1, src.length));
                //递归分解,并对任务排序
                invokeAll(lefTask, rightTask);
                //获取排序结果
                int[] leftResult = lefTask.join();
                int[] rightResult = rightTask.join();
                //合并排序结果
                return MergeSort.merge(leftResult, rightResult);
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();
        System.out.println(Arrays.toString(src));
        SumTask innerFind = new SumTask(src);
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(pool.invoke(innerFind)));
        System.out.println("spend time：" + (System.currentTimeMillis() - start) + "ms");
    }
}
