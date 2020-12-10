package com.hulk.androidstudy.java_base.thread.syn;

/**
 * 错误加锁和原因分析
 * 加锁对象的状态发生变化后锁将失效
 * Created by tzh on 2020/11/23.
 */
class TestIntegerSyn {
    public static void main(String[] args) {
        Worker worker = new Worker(1);
        for (int i = 0; i < 5; i++) {
            new Thread(worker).start();
        }
    }

    private static class Worker implements Runnable {
        private Integer i;
        private Object o = new Object();

        public Worker(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
            synchronized (o) {
//            synchronized (i) {
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName() + "--@" + System.identityHashCode(i));
                i++;
                System.out.println(thread.getName() + "------" + i + "--@" + System.identityHashCode(i));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + "-------" + i + "--@"
                        + System.identityHashCode(i));
            }
        }
    }
}
