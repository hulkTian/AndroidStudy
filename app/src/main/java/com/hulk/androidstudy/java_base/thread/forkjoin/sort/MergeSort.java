package com.hulk.androidstudy.java_base.thread.forkjoin.sort;

import com.hulk.androidstudy.java_base.thread.forkjoin.sum.MakeArray;

import java.util.Arrays;

/**
 * 归并排序
 * Created by tzh on 2020/11/24.
 */
class MergeSort {

    public static int[] sort(int[] array) {
        //简单的排序直接解决
        if (array.length < MakeArray.THRESHOLD) {
            return InsertionSort.sort(array);
        } else {
            //复杂的问题分成若干小问题解决
            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid + 1, array.length);
            return merge(sort(left), sort(right));
        }
    }

    /**
     * 将两段排好序的数组合并成一个有序数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("============================================");
        long start = System.currentTimeMillis();
        MergeSort.sort(MakeArray.makeArray());
        System.out.println("spend time:" + (System.currentTimeMillis()-start) + "ms");
    }
}
