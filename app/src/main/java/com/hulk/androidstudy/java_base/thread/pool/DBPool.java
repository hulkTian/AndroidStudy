package com.hulk.androidstudy.java_base.thread.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 连接池
 * Created by tzh on 2020/11/23.
 */
class DBPool {
    private final static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(SqlConnImpl.fetchConnection());
            }
        }
    }

    /**
     * 释放链接，通知其他等待连接的线程
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 在mills内无法获取到链接，将会返回null
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            //永不超市
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(future-System.currentTimeMillis());
                    remaining = future-System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }
}
