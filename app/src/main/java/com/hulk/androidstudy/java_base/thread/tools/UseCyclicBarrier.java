package com.hulk.androidstudy.java_base.thread.tools;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * 演示CyclicBarrier用法，共5个线程，他们全部完成工作后，交出自己结果，再被统一
 * 释放去做自己的事情，而交处的结果被另外的线程拿来拼接字符串
 * Created by tzh on 2020/11/25.
 */
public class UseCyclicBarrier {
    private static CyclicBarrier barrier;
    //存放子线程工作结果的容器
    private static ConcurrentHashMap<String, Long> resultMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        barrier = new CyclicBarrier(5, new CollectThread());
        for (int i = 0; i <= 4; i++) {
            Thread thread = new Thread(new SubThread());
            thread.start();
        }
    }

    private static class CollectThread implements Runnable {
        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Long> workResult : resultMap.entrySet()) {
                result.append("[" + workResult.getValue() + "]");
            }
            System.out.println(" the result = " + result);
            System.out.println("do other business.......");
        }

    }

    private static class SubThread implements Runnable {
        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            resultMap.put(Thread.currentThread().getId() + "", id);
            try {
                barrier.await();
                Thread.sleep(1000 + id);
                System.out.println("Thread_" + id + "......do its business");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
