package com.hulk.androidstudy.java_base.thread.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by tzh on 2020/11/25.
 */
public class DBPoolSemaphore {
    private final static int POOL_SIZE = 10;
    //两个指示器，分别标识池子还有可用连接和已用连接
    private final Semaphore useful = new Semaphore(POOL_SIZE, true);
    private final Semaphore useless = new Semaphore(0, true);
    //连接容器
    private final static LinkedList<Connection> pool = new LinkedList<>();

    public DBPoolSemaphore() {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    public void returnConnect(Connection connection) throws InterruptedException {
        if (connection != null) {
            System.out.println("当前有" + useful.getQueueLength() + "个线程等待数据库连接！！ " +
                    "可用连接数：" + useful.availablePermits());
            //已用连接数减少
            useless.acquire();
            synchronized (pool) {
                pool.addLast(connection);
            }
            //可用连接数增加
            useful.release();
        }
    }

    public Connection takeConnect() throws InterruptedException {
        Connection connection;
        //可用连接数减少
        useful.acquire();
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        //已用连接数增加
        useless.release();
        return connection;
    }

    private final static DBPoolSemaphore dbPool = new DBPoolSemaphore();

    private static class BusiThread extends Thread {
        @Override
        public void run() {
            Random random = new Random();
            long start = System.currentTimeMillis();
            System.out.println("Thread_" + Thread.currentThread().getId() + "_获取数据库连接共耗时【" + (System.currentTimeMillis() - start) + "】ms");
            try {
                Connection connection = dbPool.takeConnect();
                Thread.sleep(100 + random.nextInt(100));
                System.out.println("查询数据完成，归还连接！");
                dbPool.returnConnect(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
