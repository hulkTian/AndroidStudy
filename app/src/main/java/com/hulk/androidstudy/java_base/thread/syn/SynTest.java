package com.hulk.androidstudy.java_base.thread.syn;

/**
 * synchronized关键字的使用方法
 * Created by tzh on 2020/11/23.
 */
class SynTest {
    private long count = 0;
    private Object obj = new Object();//可以作为一个锁

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    /**
     * 用在代码块上
     */
    public void intCount() {
        synchronized(obj) {
            count++;
        }
    }

    /**
     * 用在方法上
     */
    public synchronized void intCount2() {
        count++;
    }

    /**
     * 用在代码块上，但是锁的时当前类的实例对象
     */
    public void intCount3() {
        synchronized (this) {
            count++;
        }
    }

    private static class Count extends Thread {
        private SynTest simpOper;
        public Count(SynTest simpOper) {
            this.simpOper = simpOper;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000;i++) {
//                simpOper.intCount();//count = count+10000
//                simpOper.intCount2();//count = count+10000
                simpOper.intCount3();//count = count+10000
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynTest simpOper = new SynTest();
        //启动两个线程
        Count count1 = new Count(simpOper);
        Count count2 = new Count(simpOper);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println(simpOper.count);//20000
    }
}
