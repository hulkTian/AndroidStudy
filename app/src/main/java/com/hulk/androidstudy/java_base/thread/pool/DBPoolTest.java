package com.hulk.androidstudy.java_base.thread.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tzh on 2020/11/23.
 */
public class DBPoolTest {
    static DBPool pool = new DBPool(10);
    //控制器：控制main线程将会等待所有Worker结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        int count = 20;//每个线程的操作次数
        AtomicInteger got = new AtomicInteger();//计数器：统计可以拿到连接的线程
        AtomicInteger notGot = new AtomicInteger();//计数器：统计没有拿到连接的线程
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, notGot),"worker_"+i);
            thread.start();
        }
        end.await();//main线程开始等待
        System.out.println("总共尝试了：" + (threadCount * count));
        System.out.println("拿到连接的次数：" + got);
        System.out.println("没有拿到连接的次数：" + notGot);
    }

    static class Worker implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger noGot;

        public Worker(int count, AtomicInteger got, AtomicInteger noGot) {
            this.count = count;
            this.got = got;
            this.noGot = noGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    //从线程池中获取连接，如果1000ms内没有获取到，将返回null
                    //分别统计连接获取的数量
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            Thread.sleep(100);
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else  {
                        noGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + "等待超时！");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
