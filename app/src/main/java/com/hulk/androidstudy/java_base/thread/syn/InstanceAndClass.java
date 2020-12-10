package com.hulk.androidstudy.java_base.thread.syn;

/**
 * 实例锁和类锁，两者可以并行
 * Created by tzh on 2020/11/23.
 */
class InstanceAndClass {

    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("TestClass is Running...");
            try {
                synClass();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class InstanceSyn implements Runnable {
        private final InstanceAndClass instanceAndClass;

        public InstanceSyn(InstanceAndClass instanceAndClass) {
            this.instanceAndClass = instanceAndClass;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..." + instanceAndClass);
            try {
                instanceAndClass.instance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实例对象锁
     */
    private synchronized void instance() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("synInstance is going..." + this.toString());
        Thread.sleep(1000);
        System.out.println("synInstance ended " + this.toString());

    }

    /**
     * 类锁
     */
    private static synchronized void synClass() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("synClass going...");
        Thread.sleep(1000);
        System.out.println("synClass end");
    }

    public static void main(String[] args) throws InterruptedException {
        InstanceAndClass synClassAndInstance = new InstanceAndClass();
        Thread t1 = new SynClass();
        Thread t2 = new Thread(new InstanceSyn(synClassAndInstance));
        t2.start();
        Thread.sleep(1000);
        t1.start();
    }
}
