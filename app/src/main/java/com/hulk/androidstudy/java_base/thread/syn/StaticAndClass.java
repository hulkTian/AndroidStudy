package com.hulk.androidstudy.java_base.thread.syn;

/**
 * 类锁和锁static变量不同
 * Created by tzh on 2020/11/23.
 */
class StaticAndClass {
    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println(currentThread().getName() + ":SynClass is running...");
            synClass();
        }
    }

    private static class SynStatic extends Thread {
        @Override
        public void run() {
            System.out.println(currentThread().getName() + "SynStatic is running...");
            synStatic();
        }
    }

    private static synchronized void synClass() {
        System.out.println(Thread.currentThread().getName() + "synClass going...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "synClass end");
    }

    private static Object obj = new Object();
    private static void synStatic() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + "synStatic going...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "synStatic going...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new SynClass();
        Thread t2 = new SynClass();
        t2.start();
        Thread.sleep(1000);
        t1.start();
    }
}
