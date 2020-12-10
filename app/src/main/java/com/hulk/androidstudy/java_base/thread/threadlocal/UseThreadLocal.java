package com.hulk.androidstudy.java_base.thread.threadlocal;

import androidx.annotation.Nullable;

/**
 * Created by tzh on 2020/11/21.
 */
public class UseThreadLocal {
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Nullable
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public void startThreadArray() {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(new TestThread(i));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static class TestThread implements Runnable {
        private final int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Thread-" + id + ":start");
            threadLocal.set(id + threadLocal.get());
            System.out.println("Thread-" + id + ":" + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        UseThreadLocal useThreadLocal = new UseThreadLocal();
        useThreadLocal.startThreadArray();
    }
}
