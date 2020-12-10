package com.hulk.androidstudy.java_base.thread.forkjoin.sort;

import com.hulk.androidstudy.java_base.thread.forkjoin.sum.MakeArray;

import java.util.Arrays;

/**
 * 简单插入排序（升序）
 * Created by tzh on 2020/11/24.
 */
class InsertionSort {
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int currentValue;
        for (int i = 0; i < array.length - 1; i++) {
            int preIndex = i;
            currentValue = array[preIndex + 1];
            while (preIndex > 0 && currentValue < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = array[preIndex];
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println("============================================");
        int[] array = InsertionSort.sort(MakeArray.makeArray());
        System.out.println(Arrays.toString(array));
    }
}
