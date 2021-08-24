package com.hulk.androidstudy.java_base.lock;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by tzh on 2020/12/2.
 */
public class UseAQS {

    private static class Mutex implements Lock, Serializable {
        private final Sync sync = new Sync();

        @Override
        public void lock() {
            sync.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            sync.acquireInterruptibly(1);
        }

        @Override
        public boolean tryLock() {
            return sync.tryAcquire(1);
        }

        @Override
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            return sync.tryAcquireNanos(1, unit.toNanos(timeout));
        }

        @Override
        public void unlock() {
            sync.release(1);
        }

        @Override
        public Condition newCondition() {
            return sync.newCondition();
        }

        public boolean isLocked() {
            return sync.isHeldExclusively();
        }

        public boolean hasQueueThreads() {
            return sync.hasQueuedThreads();
        }

        private static class Sync extends AbstractQueuedSynchronizer {
            //报告是否处于被锁状态
            @Override
            protected boolean isHeldExclusively() {
                return getState() == 1;
            }

            //尝试拿锁
            @Override
            protected boolean tryAcquire(int acquires) {
                assert acquires == 1;
                //先判断是否有线程已经拿锁，如果有就直接返回fase取消拿锁,如果没有就将状态改为1，并拿锁。
                //这是一个原子方法
                if (compareAndSetState(0, 1)) {
                    //设置被锁线程
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                } else if (getExclusiveOwnerThread() == Thread.currentThread()) {
                    setState(getState() + 1);
                    return true;
                }
                return false;
            }

            @Override
            protected boolean tryRelease(int releases) {
                assert releases == 1;
                if (getState() == 0) throw new IllegalMonitorStateException();
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }

            Condition newCondition() {
                return new ConditionObject();
            }

            private void readObject(ObjectInputStream s)
                    throws IOException, ClassNotFoundException {
                s.defaultReadObject();
                setState(0); // reset to unlocked state
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Mutex mutex = new Mutex();
        UseAQS useAQS = new UseAQS();
        //创建20000个线程对count变量进行++计算并通过mutex锁对线程进行同步，保证输出结果为20000
        for (int i = 0; i < 20000; i++) {
            new Thread(() -> {
                useAQS.count(mutex);
            }).start();
        }
    }

    private int count;

    /**
     * 通过锁控制方法的同步访问，如果没有成功获得锁就不执行操作
     */
    private void count(Mutex mutex) {
        mutex.lock();
        if (mutex.isLocked()) {
            count++;
            if (count == 20000)
                System.out.println(count);
        }
        mutex.unlock();
    }
}
