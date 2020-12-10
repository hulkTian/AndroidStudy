package com.hulk.androidstudy.java_base.thread.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by tzh on 2020/11/25.
 */
public class DBPoolNoUseless {
    private final static int POOL_SIZE = 10;
    private final Semaphore useful;
    //连接容器
    private static LinkedList<Connection> pool = new LinkedList<>();

    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    public DBPoolNoUseless() {
        this.useful = new Semaphore(POOL_SIZE);
        System.out.println("当前有" + useful.getQueueLength() + "个线程等待数据库连接" +
                "可用连接数：" + useful.availablePermits());
    }

    /**
     * 归还连接
     */
    public void returnConnect(Connection connection) {
        if (connection != null) {
            System.out.println("当前有" + useful.getQueueLength() + "个线程等待数据库连接" +
                    "可用连接数：" + useful.availablePermits());
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();
        }
    }

    /**
     * 从池子拿连接
     */
    public Connection takeConnect() throws InterruptedException {
        //从计数器中获取许可，如果没有就会阻塞
        useful.acquire();
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        return connection;
    }

    private static DBPoolNoUseless dbPoolNoUseless = new DBPoolNoUseless();

    private static class BusiThread extends Thread {
        @Override
        public void run() {
            Random r = new Random();
            long start = System.currentTimeMillis();
            try {
                 Connection connection = dbPoolNoUseless.takeConnect();
                System.out.println("Thread_" + Thread.currentThread().getId() + "_获取数据库共耗时【"
                        + (System.currentTimeMillis() - start)
                        + "】ms");
                //模拟业务
                Thread.sleep(100+r.nextInt(100));
                System.out.println("查询数据完成，归还连接");
                dbPoolNoUseless.returnConnect(connection);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i< 50; i++) {
            Thread thread = new BusiThread();
            thread.start();
        }
    }
}
