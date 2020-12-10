package com.hulk.androidstudy.java_base.thread.tools;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tzh on 2020/11/25.
 */
public class UseCountDownLatch {

    static CountDownLatch latch = new CountDownLatch(5);

    /**
     * 消耗一个计数器
     */
    private static class InitThread implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread_" + Thread.currentThread().getId() + "ready init work....");
            latch.countDown();
            for (int i = 0; i < 2; i++) {
                System.out.println("Thread_" + Thread.currentThread().getId() + "....continue do its work");
            }
        }
    }

    /**
     *等待线程，计数器消耗完后会被唤醒
     */
    private static class BusiThread implements Runnable {
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("BusiThread_" + Thread.currentThread().getId() + "do work....");
            }
        }
    }

    public static void main(String[] args) {
        //定义一个线程消耗两个计数器
        new Thread(() -> {
            try {
                Thread.sleep(1);
                System.out.println("Thread_" + Thread.currentThread().getId() + "ready init work step 1st....");
                latch.countDown();
                System.out.println("begin step 2ed.....");
                Thread.sleep(1);
                System.out.println("Thread_" + Thread.currentThread().getId() + "ready init work step 2ed......");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(new BusiThread()).start();
        //创建3个计数器消耗线程
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
        try {
            //主线程等待
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main do its work.....");
    }
}
